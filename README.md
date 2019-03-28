# MATERA TALENT POOL

This is a code sample for matera talent pool.

### Employee REST Server.

This application provides an embedded database (mongodb), it also ingest an external source of employees, you can change the provided data by editing the `src/main/java/resources/data.json` file. 

This application provides API Documentation through Swagger, you are able to access the `json` documentation on `/v2/api-docs` or access the UI on `/swagger-ui.html`.

This application provides Spring Boot Actuators endpoints. Here are some useful ones:

| Endpoint |  Description | 
| ------ | ------ |  
| /actuator/health | Application health status |
| /actuator/env | Environment details |
| /actuator/info | General information |
| /actuator/httptrace | Last 100 request-response exchanges |


This application also provides authorization through oauth. So, to be able to call any API you need to get an token on `/oauth/token` endpoint and pass it on the Authorization Header of the request as `Authorization: bearer {token}`. This is a cURL snippet to request a token:
```bash
curl -X POST \
  http://localhost:8080/oauth/token \
  -H 'Authorization: Basic dGFsZW50LXBvb2w6bWF0ZXJh' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=user&password=matera'
```
  
### The list of operations provided by the API:
| Method | URL | Description | 
| ------ | ------ |  ------ |
| **GET** | /api/v1/employee/{id} | Retrieve an employee by id |
| **GET** | /api/v1/employee | Retrieve all employees |
| **POST** | /api/v1/employee | Create a new employee |
| **PUT** | /api/v1/employee | Update an existing employee |
| **DELETE** | /api/v1/employee/{id} | Remove an employee based on the id |

### Here is one cURL snippet for each endpoint:
##### **GET** on /api/v1/employee/{id}
```bash
curl -X GET \
  http://localhost:8080/api/v1/employee/e379ada4-d2cf-4338-b7af-07b977e94486 \
  -H 'Authorization: bearer 2106ba78-64a4-40b7-99cd-6fbb990e91e0'
```

##### **GET** on /api/v1/employee (This api supports pagination)
```bash
curl -X GET \
  http://localhost:8080/api/v1/employee?pageNumber=1&pageSize=1 \
  -H 'Authorization: bearer 2106ba78-64a4-40b7-99cd-6fbb990e91e0'
```

##### **POST** on /api/v1/employee  
```bash
curl -X POST \
  http://localhost:8080/api/v1/employee \
  -H 'Authorization: bearer a096eaa6-16bb-48a4-b82a-a2a86453b937' \
  -H 'Content-Type: application/json' \
  -d '{
         "firstName" : "First",
         "middleInitial" : "A",
         "lastName" : "Last",
         "dateOfBirth" : "2019-01-01",
         "dateOfEmployment": "2019-01-01"
       }'
```

##### **PUT** on /api/v1/employee  
```bash
curl -X PUT \
  http://localhost:8080/api/v1/employee \
  -H 'Authorization: bearer a096eaa6-16bb-48a4-b82a-a2a86453b937' \
  -H 'Content-Type: application/json' \
  -d '{
         "id" : "e379ada4-d2cf-4338-b7af-07b977e94486", 
         "firstName" : "First",
         "middleInitial" : "A",
         "lastName" : "Last",
         "dateOfBirth" : "1985-01-01",
         "dateOfEmployment": "2019-01-01",
         "status" : "ACTIVE"
        }'
```

##### **DELETE** on /api/v1/employee/{id}   
```bash
curl -X DELETE \
  http://localhost:8080/api/v1/employee/e379ada4-d2cf-4338-b7af-07b977e94486 \
  -H 'Authorization: bearer a096eaa6-16bb-48a4-b82a-a2a86453b937'
```

## Build, Test and Run
Follow the next steps to properly run the project:  
In the folder `matera-talent` run the commands:

To Test, use `mvn test`  
To Build, use  `mvn clean package` add ` -DskipTests` to avoid the test phase  
To Run, use: `java -jar ./target/matera-talent-0.0.1-SNAPSHOT.jar`  

# Postman Collection
In the `postman-collection` folder you will find a postman collection and environment. This collection contains one request to each API.
