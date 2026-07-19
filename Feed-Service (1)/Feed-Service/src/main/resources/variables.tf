variable "instance_type" {
  type        = string                     # The type of the variable, in this case a string
  default     = "t2.micro"                 # Default value for the variable
  description = "The type of EC2 instance" # Description of what this variable represents
}
variable "ami_id" {
  type = string
}
variable "vpc" {
  type = string
}
variable "cidr_blocks" {
  type = string
}
