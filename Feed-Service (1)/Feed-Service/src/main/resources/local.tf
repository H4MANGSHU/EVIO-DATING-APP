locals {
  app_name    = "dating-app"
  env         = "dev"
  owner       = "himangshu"
  managedBy   = "himangshu"
  bucket_name = "media-service"

  availability_zone = "ap-south-1a"
  subnets = ["10.0.0.0/16"]

  private = ["10.0.0.0/16"]

  }

