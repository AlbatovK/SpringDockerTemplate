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
* `Docker` - для удобного развертывания на локальных или удаленных хостингах [(ссылка)](https://springdockertemplate.onrender.com/swagger-ui/index.html). В данный момент используется Render, так как исппользуется Free-tier, сервер засыпает после бездействия, необходимо подождать от 3 до 5 минут после первого запроса, после обновить страницу.
* `Spring WebSocket` - для будущей возможной интеграции с `ISO8583`
* `Detekt` и `Ktlint` - статические анализаторы `Kotlin`-кода, которые ускоряли и упрощали процесс разработки и отлаживания сервиса
* `Beeceptor` - создание удаленного `Mock Api` для тестирования приложения и имитации загруженности системы

## Структура проекта
### Взаимодействие между компонентами
![](https://github.com/AlbatovK/SpringDockerTemplate/blob/master/svg_diagram.drawio.svg?raw=true)
### Структура веб-сервиса
![](https://github.com/AlbatovK/SpringDockerTemplate/blob/master/layers_svg.drawio.svg?raw=true)
