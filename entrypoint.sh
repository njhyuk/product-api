#!/bin/sh

echo "Elasticsearch가 뜰 때까지 대기 중..."
until curl -s http://elasticsearch:9200 >/dev/null; do
  sleep 1
done

echo "인덱스 생성 시도..."
curl -X PUT 'http://elasticsearch:9200/products' -H 'Content-Type: application/json' -d '{
  "mappings": {
    "properties": {
      "name": { "type": "text", "analyzer": "nori" },
      "category": { "type": "text", "analyzer": "nori" },
      "brand": { "type": "text", "analyzer": "nori" }
    }
  }
}'

echo "애플리케이션 시작!"
exec java -jar app.jar
