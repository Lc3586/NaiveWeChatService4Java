#!/bin/bash

docker build -f Dockerfile -t lctr/naive-wechat-service .
docker run -v app_storage_area:/app-data \
           -p 5004:80 \
           --name naive-wechat-service \
           --restart always \
           lctr/naive-wechat-service