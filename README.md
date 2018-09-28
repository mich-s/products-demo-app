
## Products App

_products-demo-app_ is a sample Spring Boot project that allows you to perform CRUD operations on products and categories.

### Prerequisites
Basic software you'll need:
```
Java 8, Maven 3, MySQL 5
```
### Technologies and tools
```
Spring Boot (Spring MVC, Spring Data JPA), Maven, MySQL, JUnit 4, Thymeleaf
```
### Setup
1. Clone the repo
	```
	git clone https://github.com/mich-s/products-demo-app.git
	```
2. Configure database - update credentials in _src/main/resources/application.properties_
	```
	spring.datasource.url=jdbc:mysql://localhost:3306/products?useSSL=false&createDatabaseIfNotExist=true
	spring.datasource.username=
	spring.datasource.password=
	```
3. Build and run the app
	- _option 1: using Spring Boot Maven plugin_
	```
	mvn spring-boot:run
	```
	- _option 2: run a jar file_ 
	```
	mvn clean package
	java -jar target/products-0.0.1-SNAPSHOT.jar
	```	 
	- _option 3: run the project from Eclipse as a Java application_
4. Start your browser and go to: 
	```
	http://localhost:8080/products
	```
### Screenshots
![Alt](
https://github.com/mich-s/products-demo-app/blob/master/src/main/webapp/resources/img/p1.PNG)
![Alt](
https://github.com/mich-s/products-demo-app/blob/master/src/main/webapp/resources/img/p2.PNG)