resource "aws_vpc" "vpc" {
  cidr_block = var.cidr_blocks
  tags = {
    name ="my-vpc"
  }
}
resource "aws_subnet" "public" {
  vpc_id = aws_vpc.vpc.id
  cidr_block = local.subnets[0]
  availability_zone = local.availability_zone
}
resource "aws_subnet" "pvt" {
  vpc_id = aws_vpc.vpc.id
  cidr_block = local.private[0]
  availability_zone = local.availability_zone
}
resource "aws_internet_gateway" "gateway" {
  vpc_id = aws_vpc.vpc.id
}
resource "aws_route_table" "route_table" {
  vpc_id = aws_vpc.vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gateway.id

  }
}
resource "aws_route_table_association" "public" {
  subnet_id      = aws_subnet.public.id
  route_table_id = aws_route_table.route_table.id
}
