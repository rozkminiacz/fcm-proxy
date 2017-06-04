# FCM Java Spark Module

# Features

  - send fcm notification to device or topic
  - store notifications in mongoDB

## Run
```sh
$ mongod --dbpath /path/to/data (default port 2701)
$ ./gradlew run
```

## Requests

### Send notification

#### Request
```http
POST /device
{
	"to":
		"/topics/cracow"
	,
	"data":{
		"title":"Zamów jedno, odbierz dwa!",
		"desc":"Niesamowita promocja tylko przez godzinę w AFK",
		"type":"PROMOTION"
	}
}
```

#### Response

```http
{
  "message_id": 5184135381228801228
}
```

### List already sent notifications
#### Request
```http
GET /notifications
}  
```

#### Response
```http
{
   {
    "title": "Zamów jedno, odbierz dwa!",
    "desc": "Niesamowita promocja tylko przez godzinę w AFK",
    "createdAt": "2017-04-04T21:46:14.223Z"
  },
  {
    "title": "Zamów jedno, odbierz dwa!",
    "desc": "Niesamowita promocja tylko przez godzinę w AFK",
    "duration": "90",
    "createdAt": "2017-04-04T21:47:02.950Z"
  }
}  
```

### Todos

 - query existning notifications
 - add sender ID
 - open Spark and Mongo on different ports
