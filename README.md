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
  "url": "http://www.google.com/"
}' 'http://localhost:8080/urls'
```

will return a response like the following:
```
{
  "url": "http://www.google.com/",
  "retrievalKey": "zAcad",
  "redirections": 0
}
```

#### GET /urls/{id}
To retrieve the stats of a url (redirects) by using the retrieval key:
```sh
$ curl -X GET --header 'Accept: application/json' 'http://localhost:8080/urls/zAcad'

```

will return a response like the following:
```
{
  "url": "http://www.google.com/",
  "retrievalKey": "zAcad",
  "redirections": 0
}
```

To be redirected to the website of your choice by using the shortener:
1) Open your browser
2) Input the base url of the server where the app is deployed (default : http://localhost:8080/)
3) Append the redirection controller's path: /rdr/{retrievalKey}'
```
e.g. http://localhost:8080/rdr/zAcad
```
