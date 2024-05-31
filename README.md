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
./gradlew build jibDockerBuild
./gradlew --exclude-task test build jibDockerBuild
```

## Docker hub 주의사항
```
이미지 이름은 해당 포멧을 지켜야한다. (안지키면 안올라감)
포멧: {계정명}/{이미지이름}
```

## 디비 띄우기
```
docker-compose  -f .docker/db/docker-compose.yml --env-file .docker/db/.env up
```