# Rules microservice for Retail Banking System

## Description :

- Functional Requirements
  - The Rules microservice will be responsible for interacting with a rules engine to evaluate certain rules that is applicable prior to performing transactions 
- Rules defined to accept or reject a transaction :
  - if account type "savings", minimum balance is 0 EUR
  - if account type "current", minimum balance is -50 EUR
- Rules defined for service charges : Fixed to 10 EUR

- Developer
  - Solofo RABONARIJAONA

## Development progress
| Task | Status | Date |
|---------|--------|------|
| Understand requirement and design solution | ✅ | 17/02 |
| Set up project structure | ✅ | 18/02 |
| Write unit test (using Mockito) and code for BusinessRulesEngineImpl class | ✅ | 18/02 |
| Write unit test (using Mockito and MockMvc) and code for RulesRestController class  | ✅ | 18/02 |
| Add swagger 2 | ✅ | 18/02 |
| Add logging using sl4j and zipkin | ✅ | 18/02 |
| Dockerize the microservice | ✅ | 18/02 |
| PMD check | 🚧 | 18/02 |
| Refactor code | 🚧 | NA |
| Set up CI/CD | 🚧 | NA |
| Deploy on AWS ECS Fargate | 🚧 | NA |

## Installation guide :
### Requirement :
- Java >= 8
- Docker (optional)

## Get started using IDE
- Open /rules in your favorite IDE (STS/Eclipse/Inteliji)
- Start as spring boot
- Open swagger in browser http://localhost:8083/swagger-ui.html

## Get started using Docker
- Build the project :
  - Open command line inside /customer
  - Execute `./mvnw install`
- Build and Run docker image
  - Execute `docker build -t rules-microservice:latest .`
  - Execute `docker run -p 8083:8083 rules-microservice`
- Open swagger in browser http://localhost:8083/swagger-ui.html

## Project structure design :
![class diagram](https://github.com/meniman98/Retail_Banking/blob/rules/Rule/class_diagram_v1.png)
