# Проект команды "Котята фон Неймана"
## Участники команды
* Албатов Константин
* Подворный Артем
* Суслов Илья
* Горлов Артем

## Стэк технологий
* `Spring` - основной веб фреймворк
* `Redis` - использование внешней базы данных для кэширования с интеграцией `Spring Cache`
* `Postgres` - база данных для хранения данных о форматах и полях кэширования партнеров
* `Swagger` - создание автоматической [документации](https://springdockertemplate.onrender.com/swagger-ui/index.html) для упрощения тестирования и демонстрации веб-приложения
* `Docker` - для удобного развертывания на локальных или удаленных хостингах [(ссылка)](https://springdockertemplate.onrender.com). В данный момент используется Render, так как исппользуется Free-tier, сервер засыпает после бездействия, необходимо подождать от 3 до 5 минут после первого запроса, после обновить страницу.
* `Spring WebSocket` - для будущей возможной интеграции с `ISO8583`
* `Detekt` и `Ktlint` - статические анализаторы `Kotlin`-кода, которые ускоряли и упрощали процесс разработки и отлаживания сервиса
* `Beeceptor` - создание удаленного `Mock Api` для тестирования приложения и имитации загруженности системы

## Структура проекта
### Взаимодействие между компонентами
![](https://github.com/AlbatovK/SpringDockerTemplate/blob/master/svg_diagram.drawio.svg?raw=true)
### Структура веб-сервиса
![](https://github.com/AlbatovK/SpringDockerTemplate/blob/master/layers_svg.drawio.svg?raw=true)

## Видео-демонстрация функционала
Ссылка ня [яндекс-диск](https://disk.yandex.ru/i/RycUOM7ntfPiJQ)

## Список эндпоинтов
* [/visa - принимает Json, ровно по форме /visa из коллекции Postman из ТЗ](https://springdockertemplate.onrender.com/visa)
* [/master, принимает Xml, ровно по форме /master из коллекции Postman из ТЗ](https://springdockertemplate.onrender.com/master)
* [/test - может принимать как Json, так и Xml, парсит поля и кэширует по возможным согласно описанию функционала полям, выдает ответ в том же формате, в котором написан запрос](https://springdockertemplate.onrender.com/test)

### Коллекция Postman для тестирования API
```json
{
  "info": {
    "_postman_id": "b299db74-7851-44c3-8655-668972a35c53",
    "name": "Qiwi-Hackathon",
    "schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
    "_exporter_id": "20739883"
  },
  "item": [
    {
      "name": "Visa Json Payment",
      "request": {
        "method": "PUT",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "type": "text"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n \"method\": \"payment\",\n \"params\": {\n    \"card_holder_name\": \"CARDHOLDER NAME\",\n    \"card_number\": \"4278011111275400\",\n    \"card_expire\": \"2702\",\n    \"card_cvc\": \"067\",\n    \"amount\": \"1000\",\n    \"description\": \"Month subscription\",\n    \"redirect_url\": \"https://shop.merchant.com/order/23\"\n  },\n  \"id\": \"{{$randomUUID}}\"\n}"
        },
        "url": "https://qiwi-hackathon.free.beeceptor.com/visa"
      },
      "response": []
    },
    {
      "name": "Master xml Payment",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/xml",
            "type": "text"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n  <request>\n\t<pg_card_number>5266886676134311</pg_card_number>\n\t<pg_description>Оплата брони</pg_description>\n\t<pg_cardholder>Ivan Ivanov</pg_cardholder>\n    <pg_expire_date>0128</pg_expire_date>\n    <pg_cvv>555</pg_cvv>\n  </request>"
        },
        "url": "https://qiwi-hackathon.free.beeceptor.com/master/{{$randomUUID}}"
      },
      "response": []
    },
    {
      "name": "Master Xml Payment",
      "request": {
        "method": "DELETE",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": ""
        },
        "url": "https://fonneiymankittens.free.beeceptor.com/"
      },
      "response": []
    },
    {
      "name": "Random Structure Test",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Accept",
            "value": "application/xml",
            "type": "text"
          },
          {
            "key": "Content-Type",
            "value": "application/json",
            "type": "text"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "// Проверка метода с любой структурой тестовых запросов, в данном случае определяется по заголовком Accept и Content-type\r\n\r\n{\r\n    \"a\": {\r\n        \"b\": {\r\n            \"c\": {\r\n\r\n            }\r\n        }\r\n    },\r\n    \"method\": \"payment\",\r\n    \"params\": {\r\n        \"card_holder_name\": \"CARDHOLDER NAME\",\r\n        \"card_number\": \"4278011111275400\",\r\n        \"card_expire\": \"2702\",\r\n        \"description\": \"Month subscription\",\r\n        \"redirect_url\": \"https://shop.merchant.com/order/23\"\r\n    },\r\n    \"description\": {\r\n        \"b\": null\r\n    },\r\n    \"id\": \"4490d1a0-fd21-44b6-84cc-a72d17b1c962\"\r\n}"
        },
        "url": "https://springdockertemplate.onrender.com/test"
      },
      "response": []
    },
    {
      "name": "Random Structure Test Copy",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Accept",
            "value": "application/xml",
            "type": "text"
          },
          {
            "key": "Content-Type",
            "value": "application/json",
            "type": "text"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\r\n    \"method\": \"payment\",\r\n    \"params\": {\r\n        \"card_holder_name\": \"CARDHOLDER NAME\",\r\n        \"card_number\": \"4278011111275400\",\r\n        \"card_expire\": \"2702\",\r\n        \"card_cvc\": \"067\",\r\n        \"amount\": \"1000\",\r\n        \"description\": \"Month subscription\",\r\n        \"redirect_url\": \"https://shop.merchant.com/order/23\"\r\n    },\r\n    \"id\": \"4490d1a0-fd21-44b6-84cc-a72d17b1c962\"\r\n}"
        },
        "url": "https://springdockertemplate.onrender.com/test"
      },
      "response": []
    }
  ]
}
```
