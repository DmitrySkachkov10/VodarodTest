# Скачков Дмитрий - Тестовое задание для Vodarod 

## Инструкция по запуску
Установите свой логин и пароль в файле application.properties.
## API Документация

### `GET /api/currency/rates/on-date`

Запрос для загрузки данных о курсах валют за указанную дату через API НБРБ в разработанную систему.

#### Параметры

- `date` (обязательный) - дата в формате `YYYY-MM-DD`.

#### Ответы

- `200 OK`: Успешный ответ

### `GET /api/currency/rates/{code}`

Запрос выдает информацию о курсе валюты за указанную дату.

#### Параметры

- `code` (обязательный) - код валюты (целое 3-значное число).
- `date` (обязательный) - дата в формате `YYYY-MM-DD`.

#### Ответы

- `200 OK`: Успешный ответ, содержащий данные о курсе валюты за указанную дату.

# Взаимодействие с API НБРБ 

### `GET /exrates/currencies`

Метод у Feign Client для получения списка всех доступных валют, с которыми работает НБ РБ. Вызывается один раз при старте приложения.

#### Ответы

- `200 OK`: Успешный ответ, содержащий список всех валют.

### `GET /exrates/rates`

Метод у Feign Client для загрузки данных о курсе всех валют за указанную дату.

#### Ответы

- `200 OK`: Успешный ответ, содержащий данные о курсе валюты за указанную дату.

## OpenAPI Спецификация
[Ссылка на OpenAPI файл](openapi.yml)

##### Дополнительная информация
Для создания начальных таблиц используется ddl-auto=create. Быстрый способ создания таблиц, не вижу необходимости в использовании DML и DDL для данного тестового задания. 
Для взаимодействия со сторонним API был использован OpenFeign. Для загрузки информации о всех валютах в БД при старте приложения используется аннотация @PostConstruct.

