# CSV loader (based on Spring stack).
# Guide to using.

# **1) Настройка сервиса перед началом работы.** 
#   **1.1) Подготовка файлов.**
        1.1.1) Клонировать даный проект по соответвующей ссылке из GitHub и открыть в своей среде разработки.
        1.1.2) В случае отсутсвия Docker - установить для работы приложения из контейнера.
        1.1.3) Необходимые файлы для загрузки и последующей работы поместить в папку "resources" по пути "src/main/resources".
        1.1.4) В файле application.properties:
                1.1.4.1) Указать точные имена загружаемых фалов в блоке "#file names".
                1.1.4.2) Проверить дефолтные разделители данных в загружаемых файлах (блок #separators), в случае отличий изменить на сответствующие.
                1.1.4.3) Проверить дефолтные имена столбцов для загружамых файло в блоках "#logins.csv default column definitions" и "#postings.csv default column definitions".
                         В случае отличий от дефолтных значений, указать актуальные имена столбцов в загружаемых файлах.
                1.1.4.4) В строке "spring.datasource.url", необходимо указать IP и порт на котором находится ваш сервер MySQL, а так же внести изменения в поля "spring.datasource.username"
                         и "spring.datasource.password", если ваш сервер требует аутентификации, из файла "docker-compose.yml" необходимо удалить строки с инициализацией "my_sql:"
                         . P.S. Параметр "createDatabaseIfNotExist=true отвечает за автоматическое создание базы данных в случае ее отсутствия на сервере,
                         "spring.jpa.hibernate.ddl-auto=update" - автоматическое создание всех необходимых для работы приложения таблиц.
                         P.S.S. Если же вам нет нет необхдимости в собственном сервере MySQL, каких-либо измененний, указанных в данном пункте не требуется,
                         требуется только запуск файла "docker-compose.yml" для контейнеризаци приложения из его работы из контейнера Docker.
#   **1.2) Запуск приложения.**
          1.2.1) После настройки указанной в предыдущих пунктах ваши файлы, находящиеся в папке "resources", будут автоматически загружены в указанный вами сервер MySQL.
          1.2.2) Для запуска приложения необходимо запустить файл "docker-compose.yml" - приложение будет собрано в запущено в контейнере Docker
          1.2.3) Перед началом работы при
               
