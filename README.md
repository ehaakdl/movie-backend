## 빌드 파일 제거

```
./gradlew clean
```

## Docker 이미지 제거

```
docker rmi movie-backend:latest movie-backend:1.0.0
```

## Docker 이미지 생성

```
./gradlew --exclude-task test build jibDockerBuild
```

## Docker hub 주의사항
```
이미지 이름은 해당 포멧을 지켜야한다. (안지키면 안올라감)
포멧: {계정명}/{이미지이름}
```

## aws docker compose
```
docker-compose  -f .docker/prod-docker-compose.yml up -d
```

## local docker compose

```
docker-compose  -f .docker/local-docker-compose.yml up -d
```

## DB ERD
![movie_erd](https://github.com/ehaakdl/movie-backend/assets/6407466/cc292087-a32a-4c65-9891-0cf396e18c6d)


