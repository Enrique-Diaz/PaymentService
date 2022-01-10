# PaymentService
Service for Payments

Example project of a µPaymentService with the following technologies;
* Java 11
* Spring Boot
* Spring Framework (Web, Data, Actuator)
* H2DB
* Swagger2
* Lombok
* JUnit
* Mockito

# Things to consider
1. To run this application you need to have git and maven installed in the desired machine to work.
2. Once the project is cloned use mvn clean test spring-boot:run (this will clean the project, run tests and finally run the application).
3. You can test the endpoint by curl; curl -X POST "http://localhost:8080/payment/" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"amount\": 100, \"rate\": 1.08, \"term\": 5}"
4. Also is it possible to test the endpoint with swagger; http://localhost:8080/swagger-ui/index.html
5. It's possible to see the DB at http://localhost:8080/h2-console (user:sa | no password set)

# Create Docker Image
docker build -t user/payment-service:v0.1 .

# Run Docker Image
docker run -p 8080:8080 enrique/payment-service:v0.1