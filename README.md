# nhstech
#People Skills Management API

POST localhost:8080/people/
This endpoint creates the data (people with skills).
Example json data in /src/test/resources/people.json.

GET localhost:8080/people/{id}
This endpoint returns Single person skills  by given Id.

GET localhost:8080/people/ 
This endpoint will return list of people with the skills.

PUT localhost:8080/people/{id}
This endpoint will update the people data for given identifier.

DELETE localhost:8080/people{id}
This endpoint will delete the person data for given id.

#Skills Management API

POST localhost:8080/skills/
This endpoint creates the skills data. Example json data in 
/src/test/resources/skills.json.

GET localhost:8080/people/{id}
This endpoint returns Single person skills  by given Id.

GET localhost:8080/people/ 
This endpoint will return list of people with the skills.

PUT localhost:8080/people/{id}
This endpoint will update the people data for given identifier.

DELETE localhost:8080/people{id}
This endpoint will delete the person data for given id.