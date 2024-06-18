# Application Coding Challenge

This Spring Boot application demonstrates a customer registration form and success page using Vaadin for the front end.

## Personal setup

1. Clone the repository:

```bash
git clone https://github.com/Gabriel-Wright/aeroParkChallenge.git
```

2. Within src/main/resources/application.properties

  ```
  #My SQL Server Info
  spring.datasource.url= #FILL IN URL OR REFERENCE PATH
  spring.datasource.username= #FILL IN ROOT USER OR REFERENCE CLASSPATH
  spring.datasource.password= #FILL IN PASSWORD OR REFERENCE PASSWORD
  spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
  spring.jpa.database = mysql
  ```

3. Build the project for production

  ```
  mvn clean
  mvn package -Pproduction
  ```

4. Run the jar file

  ```
  java -jar .\target\ApplicationCodingChallenge-0.0.1-SNAPSHOT.jar
  ```

## Project notes
Using DataJPA, this project is setup to create and drop the table upon running of the software. This would not be appropriate in
a production environment, but for the sake of this coding challenge I thought it was appropriate. 

This setting can be toggled here:
```
spring.jpa.hibernate.ddl-auto=create-drop
```

If you want a persistent table, change the setting to:
```
spring.jpa.hibernate.ddl-auto=update
```
I created my own personal website using Springboot + Vaadin, it can be viewed here:
https://gabrielwright.xyz/
