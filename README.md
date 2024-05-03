## 빌드 파일 제거

```./gradlew clean```

## 모듈 Docker 이미지 제거

```docker rmi movie-backend:latest movie-backend:1.0.0```

## 모듈 빌드 및 Docker 이미지 생성

```./gradlew build jibDockerBuild```
