package DTOS;

import java.time.Instant;

public record UPLOADDTO( String UploadId,
         String AuthorId,
         String MediaUrl,
         Instant PostedAt,
         String Description) {
}
