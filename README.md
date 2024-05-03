## 빌드 파일 제거

```./gradlew clean```

## Docker 이미지 제거

```docker rmi movie-backend:latest movie-backend:1.0.0```

## Docker 이미지 생성

```./gradlew build jibDockerBuild```



## 디비 띄우기
```docker-compose  -f .docker/db/docker-compose.yml --env-file .docker/db/.env```