# Complete Microservices
This repo contains employee and department service which will worked based on microservice patterns. There would be following service to complete the service.
  ##### 1. Registry Service
  ##### 2. Gateway Service
  ##### 3. Employee Service
  ##### 4. Department Service
 ## 1. Registry Service
 
 This Service will register all service to the common place. and we can call the downstream service with name using feign client.
 ## 2. Gateway Service

 This service will allow single entry point to access the application example (Employee, department). It will also authenticate and authorized the user before invoking the employee and department service. 
  ## 3. Employee Service
  This service will perforn CRUD Operation with valid request and return the valid json response.

  ## Swagger Link
      With Service 
        http://localhost:8081/employee/swagger-ui/index.html

        Using api Gateway
        http://localhost:8080/employee/swagger-ui/index.html

        
   ## Swagger Api-docs Link
      With Service 
        http://localhost:8081/employee/v3/api-docs

        Using api Gateway
        http://localhost:8080/employee/v3/api-docs 
    
<details>
  <summary>Click here to see all endpoints to Test API without gateway </summary>
  
    1. http://localhost:8081/employee/add
    2. http://localhost:8081/employee/getAll
    3. http://localhost:8081/employee/getEmployeeById/1
    4. http://localhost:8081/employee/update/1
    5. http://localhost:8081/employee/delete/1
   
</details>

<details>
  <summary>Click here to see all endpoints to Test API with gateway </summary>
  
  1. http://localhost:8080/employee/add
  2. http://localhost:8080/employee/getAll
  3. http://localhost:8080/employee/getEmployeeById/1
  4. http://localhost:8080/employee/update/1
  5. http://localhost:8080/employee/delete/1
   
</details>

## Payload

````
  {
      "firstName":"Avinash",
      "lastName":"Tiwari",
      "primaryEmail":"avi@gmail.com",
      "secondaryEmail":"optional@gmail.com",
      "primaryPhone":"9999999999",
      "secondaryPhone":"1111111111",
      "departmentRequest":{
                            "deptName":"A&D",
                            "deptCode":"AD",
                            "deptlocation":"Ground Flower"
                          }
    
  }
  ````

## 4. Department Service
  This service will perforn CRUD Operation with valid request and return the valid json response.

 ## Swagger Link
      With Service 
        http://localhost:8082/api/v1/department/swagger-ui/index.html

        Using api Gateway
        http://localhost:8080/api/v1/department/swagger-ui/index.html

        
   ## Swagger Api-docs Link
      With Service 
        http://localhost:8082/api/v1/department/v3/api-docs

        Using api Gateway
        http://localhost:8080/api/v1/department/v3/api-docs  
<details>
  <summary>Click here to see all endpoints to Test API without gateway </summary>
  
  1. http://localhost:8082/api/v1/department/add
  2. http://localhost:8082/api/v1/department/getAll
  3. http://localhost:8082/api/v1/department/getDepartmentById/1
  4. http://localhost:8082/api/v1/department/update/1
  5. http://localhost:8082/api/v1/department/delete/1
   
</details>

<details>
  <summary>Click here to see all endpoints to Test API with gateway </summary>
  
  1. http://localhost:8080/api/v1/department/add
  2. http://localhost:8080/api/v1/department/getAll
  3. http://localhost:8080/api/v1/department/getDepartmentById/1
  4. http://localhost:8080/api/v1/department/update/1
  5. http://localhost:8080/api/v1/department/delete/1
   
</details>

## Payload

````
  {
     "deptName":"A&D",
     "deptCode":"AD",
     "deptlocation":"Ground Flower"  
  }
  ````
