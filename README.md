# nhstech
#People Skills Management API

POST localhost:8080/people/
This endpoint creates the data for people with skills.
Example json data in /src/main/resources/people.json.

GET localhost:8080/people/{id}
This endpoint returns single person skills  by given Id.

GET localhost:8080/people/ 
This endpoint will return list of people with the skills.

PUT localhost:8080/people/{id}
This endpoint will update the people skills data for given identifier.

DELETE localhost:8080/people{id}
This endpoint will delete the people skills data for given identifier.

## In-Memory H2 Database can be accessed on the UI

http://localhost:8080/h2-console/login.jsp

Enter JDBC URL as jdbc:h2:mem:nhstech is configured in application.properties file and then click connect.



