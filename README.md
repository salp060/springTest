## Commands
* RUN<br/>
gradlew bootRun

* TEST<br/>
gradlew test

## CRUD Operations (Postman collection is included)
* Get all employees<br/>
`GET http://localhost:8085/employee`

* Get all positions<br/>
`GET http://localhost:8085/position`

* Get employee by name<br/>
`GET http://localhost:8085/employee?name=Test`

* Get employee by position<br/>
`GET http://localhost:8085/employee?position=Manager`

* Get employee by position and name<br/>
`GET http://localhost:8085/employee?name=test&position=Manager`

* Create new employee<br/>
`POST http://localhost:8085/employee`

* Update an employee<br/>
`PUT http://localhost:8085/employee/{id}`

* Delete an employee<br/>
`DELETE http://localhost:8085/employee/{id}`
