package UPLOAD_SERVICE;


import DATABASES.FeedRepository;
import DTOS.AwsCred;
import DTOS.UPLOADDTO;
import DTOS.UploadWhat;
import Entites.Subscription;
import Entites.Upload;
import Subs.Validate;
import jakarta.ws.rs.NotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;



@Service
public  class mediaUpload {
    private final Set<String> Allow_Format = Set.of("image/jpeg",
            "image/png",
            "image/webp");
    private final S3Client s3Client;
    private final AwsCred awsCred;
    private final S3Presigner s3Presigner;
    private  Validate validate;
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB




    public mediaUpload(S3Client s3Client, AwsCred awsCred, S3Presigner s3Presigner, FeedRepository feedRepository) {
        this.s3Client = s3Client;
        this.awsCred = awsCred;
        this.s3Presigner = s3Presigner;
    }

    public UploadWhat UploadPhotos(MultipartFile file, UPLOADDTO uploaddto,Subscription subscription) throws Exception {
        var FileName = file.getOriginalFilename();
        var ContentType = file.getContentType();
        if (file.isEmpty() || FileName == null) {
            throw new NotFoundException("File Not Found");
        }
        if (!Allow_Format.contains(ContentType)) {
            throw new UnsupportedMediaTypeStatusException("Not Supported " + file.getOriginalFilename());

        }
        if (!validate.IsSubscribed(subscription)) {
            if (file.getSize() > MAX_FILE_SIZE) {
                throw new RuntimeException(
                        "Free users can upload files up to 5 MB. Uploaded file size: "
                                + (file.getSize() / (1024 * 1024.0)) + " MB"
                );
            }
                String key = "/users" + uploaddto.UploadId() + "/photos" +
                        UUID.randomUUID() + ".jpg";
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(awsCred.getBucketName())
                        .key(key)
                        .contentType(ContentType)
                        .build();


                PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(10))
                        .putObjectRequest(putObjectRequest)
                        .build();
                PresignedPutObjectRequest presignedRequest =
                        s3Presigner.presignPutObject(putObjectPresignRequest);
                String Url = presignedRequest.url().toString();


            Upload upload = Upload.builder()
                    .UploadId(uploaddto.UploadId())
                    .AuthorId(uploaddto.AuthorId())
                    .Description(uploaddto.Description())
                    .MediaUrl(key)
                    .PostedAt(Instant.now())
                    .build();

            return new UploadWhat(Url, ContentType, FileName);
            }
        return new UploadWhat(uploaddto.UploadId(), ContentType, FileName);
    }

    @org.springframework.retry.annotation.Retryable(
            retryFor = {
                    SdkClientException.class,
                    IOException.class
            },
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )

    public String GetPhoto(String UploadId) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(awsCred.getBucketName())
                .key(UploadId)
                .build();

        GetObjectPresignRequest presignRequest =
                GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(15))
                        .getObjectRequest(getObjectRequest)
                        .build();

        PresignedGetObjectRequest presignedRequest =
                s3Presigner.presignGetObject(presignRequest);
        return s3Presigner
                .presignGetObject(presignRequest)
                .url()
                .toString();
    }

    public void DeleteFromS3(String UploadId) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(awsCred.getBucketName())
                .key(UploadId)
                .build();

        s3Client.deleteObject(deleteObjectRequest);

    }

    @Recover
    private String GetPhotoFall(Exception e){
        throw new RuntimeException("please try again Later !"+e.getMessage());
    }
}
