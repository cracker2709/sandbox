**MY SANDBOX**
=======================================================

1 - Utils
-----------------------------
This module contains samples of helpers for regex, date and json testing.

It also contains a part of SSL utilities such as instanciation of a SSL context for mutual authentification according to client keystores and truststores.
A class is also implemented to extract X509 informations with its junit tests.

Sample class to manipulate Generics T, S, K, V

To generate XML from XSD
Since util directory

`$ xjc -d src/main/java -p jaxb src/main/resources/test.xsd`


2 - Spring boot Sandbox
----------------------
- Rest API with mongoDB data
- Use of Junit5 with embedded mongo (collection named myDbEmbedded)
- Use of Reactive API
- Launch mongo docker file to execute springboot App

Requirements
    * Java SDK 11
    * Maven 3
    * Docker 18.09+

First of all build project with maven 

`$ mvn clean install`

Then launch docker mongoDB container in order to have a server instance which contains the NOSQL data

`$ cd docker`

`$ docker-compose -f ./mongo-compose.yml up`

This will launch the container listening on localhost port 27017 with a sample collection named "myDb"

To connect on it simply launch

`$ mongo --host localhost --port 27017 myDb`

Or use NoSQLBooster4Mongo which is an excellent means to interact with mongo servers (no username / password required)

Once container is up
launch the app via spring-boot-sample module

`$ cd spring-boot-sample`

`$ mvn spring-boot:run`

This app will listen on localhost port 8080
For the moment, only this REST API is available

`$ curl -X GET http://localhost:8080/table/all`

This will return the following collection json formatted

```
[{
    "code": "2",
    "name": "OP01",
    "email": "test1@op02.fr",
    "creationDate": "2017-07-14 02:59:59",
    "address": "ARRAS"
}, {
    "code": "4",
    "name": "OP02",
    "email": "test1@op02.fr",
    "creationDate": "2017-09-02 02:59:59",
    "address": "LENS"
}, {
    "code": "1",
    "name": "OP01",
    "email": "test1@op01.fr",
    "creationDate": "2016-01-05 02:59:59",
    "address": "LILLE"
}, {
    "code": "3",
    "name": "OP02",
    "email": "test1@op02.fr",
    "creationDate": "2018-12-17 02:59:59",
    "address": "DOUAI"
}, {
    "code": "5",
    "name": "OP03",
    "email": "test1@op03.fr",
    "creationDate": "2017-07-31 02:59:59",
    "address": "VALENCIENNES"
}, {
    "code": "8",
    "name": "OP04",
    "email": "test1@op04.be",
    "creationDate": "2019-04-19 02:59:59",
    "address": "TOURNAI"
}, {
    "code": "7",
    "name": "OP03",
    "email": "test1@op03.fr",
    "creationDate": "2019-02-09 02:59:59",
    "address": "HENIN-BEAUMONT"
}, {
    "code": "6",
    "name": "OP03",
    "email": "test1@op03.fr",
    "creationDate": "2018-04-10 02:59:59",
    "address": "BETHUNE"
}, {
    "code": "9",
    "name": "OP05",
    "email": "test1@op05.fr",
    "creationDate": "2019-10-23 17:37:12",
    "address": "VILLENEUVE D'ASCQ"
}]
```
