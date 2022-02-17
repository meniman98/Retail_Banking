# Customer microservice for Retail Banking System

## Description :

- Functional Requirements
  1. Creating a customer profile based on the details provided by the customer
  2. Displaying customer's profile


- Developper
  - Solofo RABONARIJAONA

## Development progress
| Task | Status | Date |
|---------|--------|------|
| Set up project structure | ✅ | 15/02 |
| Set up local MysQL Database  | ✅ | 15/02 |
| Test App and Mysql database connection | ✅ | 15/02 |
| Create entities | ✅ | 15/02 |
| Validate Customer entity attributes | ✔️ | 15/02 |
| Update Customer entity based on feedback | ✅ | 15/02 |
| Write customer repository | ✅ | 15/02 |
| Which classes should have unit test to fullfil 100% test coverage requirement ? | ✔️ | 15/02 |
| Write unit test (with Mockito) and code for customer service class implementation | ✅ | 15/02 - 16/02 |
| Write unit test (with Mockito and MockMvc) and code for customer restController class | ✅ | 16/02 |
| Add swagger 2 | ✅ | 16/02 |
| Add logging using sl4j and zipkin | ✅ | 16/02 |
| Add H2 support | ✅ | 17/02 |
| Dockerize the microservice | 🚧 | 17/02 |
| Integration test with Account Microservice | 🚧 | NA |
| Add security layer using JWT | 🚧 | NA |

## Installation guide :
### Requirement :
- Java >= 8
- Mysql database

### local database set up :
- Connect to mysql 
- Execute sql script in /database.sql

### Update mysql configuration
- Open application.properties (/customer/src/main/resources/) 
- Change spring.datasource.username and spring.datasource.password

### Update H2 configuration
If you prefer using In memory database follow this step
- Open application.properties (/customer/src/main/resources/)
- Uncomment H2 config properties
- Comment MySQL config properties 

## Get started
- Open /customer in your favorite IDE (STS/Eclipse/Inteliji)
- Start as spring boot
- Open swagger in browser http://localhost:8080/swagger-ui.html

## Project structure design :
![class diagram](https://github.com/meniman98/Retail_Banking/blob/customer/Customer/class_diagram_v1.png)
