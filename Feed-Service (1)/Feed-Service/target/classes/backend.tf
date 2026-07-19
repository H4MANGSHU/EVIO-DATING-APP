resource "aws_s3_bucket" "s3" {
  bucket = local.bucket_name

  tags = {
    Name = local.app_name
    Environment = local.env
  }
}
