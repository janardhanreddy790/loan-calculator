# Loan Request Service

## overview
Lendico we have customers who can apply for  one or more loans. This Microservice handles the requests and processes accordingly based on the request from customer.

## Use case
* A customer can request loan amount   between 500 and 12000.50.
* Customer can request the loan amount multiple times.
* A service needs to be created which calculates the requested loan amount total and summarise the loan requested by the customer in total.
* If the Customer is not present, and he is requesting loan for the first time he will be added as a new customer along with the requested loan amount.
* If the customer is already is existed his new loan wil be recorded under his customerid and one can check the loan of a particular customer in future and retrive them.
* We can retrieve the total loan amount requested by the customer using his cusomer id.
* Please refer the below endpoints and examples to use the requests and to validate the responses.
* If customer is not present or if the customer requested amount exceeds the limit specified then a message will be displayed to the customer on the status.
 

## Prerequisites

JDK 1.8 

Working IDE 

Maven (latest version)

Spring Data JPA understanding

## Working Endpoints
* POST http://localhost:8080/customer/add
* GET  http://localhost:8080/customer/getLoan?customerId=xxxx


## Usage

Run the application in the IDE with main class(LoanCalculatorApplication) as application stating point.


* POST http://localhost:8080/customer/add 

```json

# request object
{
    "customerId":12346,
    "customerName":"Sindhu",
    "loanData":{
        "loanAmount": 500.00
    }
    
}

# Response
Customer Requested amount of : 500.0has been created with loanAccountId: ff19ed56-767d-4e44-b97b-996598ee4576

```


* POST http://localhost:8080/customer/add 

```json

# request object
{
    "customerId":12346,
    "customerName":"Sindhu",
    "loanData":{
        "loanAmount": 499.00
    }
    
}

# Response
Loan Amount should be between 500.00 and 12000.50

```

* POST http://localhost:8080/customer/add 

```json

# request object
{
    "customerId":12346,
    "customerName":"Sindhu",
    "loanData":{
        "loanAmount": 100000.00
    }
    
}

# Response
Loan Amount should be between 500.00 and 12000.50

```



* GET  http://localhost:8080/customer/getLoan?customerId=12346
* If the customer doesn't exist in the repository then

```json

# request object
customerId=12346

# Response
Please check the customerId, Requested customer not found.
```


* GET  http://localhost:8080/customer/getLoan?customerId=12346
* If the customer  exist in the repository then

```json

# request object
customerId=12346

# Response
{
    "customerId": 12346,
    "totalRequestedLoanAmount": 500.0
}
```

* NOW request multiple loan amount of same customer and calculate the total loan amount requested by the customer.


### Request 1
* POST http://localhost:8080/customer/add 

```json

# request object
{
    "customerId":12346,
    "customerName":"Sindhu",
    "loanData":{
        "loanAmount": 499.00
    }
    
}

# Response
Loan Amount should be between 500.00 and 12000.50

```

### Request 1
* POST http://localhost:8080/customer/add 

```json

# request object
{
    "customerId":12346,
    "customerName":"Sindhu",
    "loanData":{
        "loanAmount": 1000.00
    }
    
}

# Response
Customer Requested amount of : 500.0has been created with loanAccountId: ff19ed56-767d-4e44-b97b-996598ee4576

```


### Request 1
* POST http://localhost:8080/customer/add 

```json

# request object
{
    "customerId":12346,
    "customerName":"Sindhu",
    "loanData":{
        "loanAmount": 999.00
    }
    
}

# Response
Customer Requested amount of : 500.0has been created with loanAccountId: ff19ed56-767d-4e44-b97b-996598ee4576

```

### Request 2
* GET  http://localhost:8080/customer/getLoan?customerId=12346
* If the customer  exist in the repository then

```json

# request object
customerId=12346

# Response
{
    "customerId": 12346,
    "totalRequestedLoanAmount": 1999.00
}
```


### Response of the calculated loan amount requested by the customer.
* The loan amount is cumulative of request 1 and request 2  of the user.

# H2 Database Login
* H2 database is a in memory database created to store the data requested by the user during the testing as it is open source.
It is also a in memory database which gives the flexibility to run the app whenever we need.
Since the project is configured with the JPA all the Entities are created during the runtime.

#### credentials to login to H2-console 
* URL: http://localhost:8080/h2-console

* User: sa

* password: password 

* JDBC URL: jdbc:h2:mem:mydb

Please refer the screenshots in the project docs for reference.

###### you can find these properties in the application.yml file for reference.

# LoanCalculator Executable Jar

* Once the project is successfully built "mvn clean install -U" with this command it will generate a "LoanCalculator-0.0.1-SNAPSHOT.jar".
* The above jar can be run on JVM as well with the following command 
```text

# Executable jar 
java -jar LoanCalculator-0.0.1-SNAPSHOT.jar

```

* Access the above endpoints in local environment.

# Design Decisions / Improvements can also done
* This project is created as a POC model to show case the different use cases.
* This project is created on top of JPA repository to make the use of CURD operations easy and can also write custom methods based on our requirements.

## Possible Further Enhancements 
* Code coverage :
    - Code coverage is covered for the business logic and repositories.
    - This can also be improved with the different ways with Integration testing, System Testing, Acceptance testing if this application is deployed in real environment.
    - At present the logic is tested in bits and pieces to make sure the logic is not leaking any sort of errors.
    - Current Code coverage 83% con be improved further with Integration testing as well.
    
* Improve with CI/CD architecture.
    - There is a scope for this project to deploy it in the real environment.
    - In order to deploy to a real environment this can be enhance with the Dockerization and also using CI/CD we can also promote the code to higher environments.
    
* Spring Profiling for promoting to higher environments.
    - We can also provide the spring profiling to deploy this app to the various environment based on the spring profiling.
    
* Cloud Config
    - we can also use cloud configurations to globalise the properties and access them whenever needed.
    - Also protects the usage of security with application secrets.
    - One can configure various data sources based on the environment.
    
* Entity Management
    - One can further enhance the entities with relations (one to many and many to one   etc..) and make a stabilised data base structure.
    
* Monitoring
    - we can enable the actuator to enable the metrics.
    - These metrics can also be promoted to various monitoring tools like prometheus and ELK and Grafana and design the usecases according to our requirements.
    

    

## Notes
Created as a POC.

