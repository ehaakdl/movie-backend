### ssh 접속
ssh ubuntu@ec2-16-16-77-121.eu-north-1.compute.amazonaws.com -i ~/.ssh/awsKey.pem

### scp 명령어
scp -ri ~/.ssh/awsKey.pem .docker ubuntu@ec2-16-16-77-121.eu-north-1.compute.amazonaws.com:~/


### 컨테이너에서 docker compose 실행방법
docker compose  -f .docker/docker-compose.yml --env-file .docker/.env up
