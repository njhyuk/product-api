# codi-api

## 프로젝트 환경 정보

```
Java 17 (17.0.4-zulu)
Gradle
Spring Boot 3.3.0
```

## 코드 빌드 및 실행

```sh
./gradlew clean build
```

```sh
java -jar build/libs/codi-0.0.1-SNAPSHOT.jar
```

## API 문서 (Swagger)

http://localhost:8080/swagger-ui/index.html

## 구현 범위에 대한 설명

### 구현 1

* 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
* `/api/v1/categories/lowest-prices`

![img_1.png](README/img_1.png)

### 구현 2

* 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
* `/api/v1/brands/lowest-prices`

![img_3.png](README/img_3.png)

### 구현 4

* 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
* `/api/v1/categories/prices`

![img_2.png](README/img_2.png)

### 구현 4

* 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
* `/internal/v1/products`

![img.png](README/img.png)
