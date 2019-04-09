## Постановка задачи
Разработать веб-приложение по управлению новостной лентой на сайте. 
Каждая новость состоит из названия, содержания, даты публикации и категории, к которой относится новость. 
Каждая категория содержит название, и к ней может быть привязано несколько новостей. 
Приложение должно предоставлять следующие возможности по работе с новостями: 
- просматривать список новостей 
- поиск новости по категории (возможность выбрать из существующих категорий), названию и содержанию 
- создание и редактирование новости 
- удаление новости 
## Описание
Новостной сервис, реализованный с помощью **SpringBoot**, **Hibernate**, **Bootstrap**.
Реализованы следующие возможности:
- Просмотр списка новостей;
- Редактирование новости;
- Создание новости;
- Удаление новости;
- Поиск по категориям;
- Полнотекстовый поиск по заголовку и содержанию;

## Архитектура
### Работа с базой данный
Классы `NewsDao` и `CategoryDao` реализуют доступ к базе данных. Например метод `public News getNewsById(long newsId)` ищет в базе 
данных новость с указанным идентификатором и возвращает объект типа `News`.
###Обработка запросов с сайта
Запросы с сайта обрабатываются в следующих классах:
 - `MainController` - обрабатывает запросы связанные с главной страницей;
 - `NewsEditorController` - обрабатывает запросы связанные с созданием и редактированием новостей;
 - `SerachController` - обрабатывает запросы связанные с поиском новостей;
## Запуск приложения
### Запуск с момощью Maven
```
$ mvn clean package
$ java -jar target/test-assignment-1.0.0.jar
```
### Запуск с помощью Docker
Чтобы запустить приложение с помощью `Docker` нужно выполнить в командной строке:
~~~
docker-compose up
~~~
Приложение запускается вместе с базой данных и будет доступно на порту `8080`.


