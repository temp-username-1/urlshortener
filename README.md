# Url Shortener API

## How to run

  - first clone the github project by cloning the current project
  - cd to the project's directory
  - type `mvn spring-boot:run`

### Documentation
Upon initiating the project, you can navigate to `localhost:8080/swagger-ui.html` and perform the supported actions. 

For a quick overview, keep reeding.

#### POST /urls

To create/insert a new url :
```sh
$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "author": "van der Aalst",
  "title": "Process Mining",
  "type": "BOOK"
}' 'http://localhost:8080/documents'
```

will return the following response:
```
{
  "ticket": 2,
  "type": "BOOK",
  "title": "Process Mining",
  "author": "van der Aalst"
}
```

#### GET /documents/{id}
To retrieve a document by using the ticket number:
```sh
$ curl -X GET --header 'Accept: application/json' 'http://localhost:8080/documents/2'
```
