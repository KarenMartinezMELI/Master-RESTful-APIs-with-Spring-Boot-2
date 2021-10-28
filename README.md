# master-restful-apis-with-spring-boot-2

----------------------------------------------------------------------------------------------
Step-00: Introduction to Exception Hanlding using ResponseStatusException.

----------------------------------------------------------------------------------------------
Step-00: Create new git branch in local git repo and remote github repo
    - Verify we are in master branch    
        - git status
    - Create new branch
        - git checkout -b 04-ExceptionHandling-ResponseStatusCodes
    - Create new branch in remote github and setup upstream   
        - git push --set-upstream origin 04-ExceptionHandling-ResponseStatusCodes
    - Verify new branch in remote github & IDE GIT Perspective             
        - https://github.com/stacksimplify/springboot-buildingblocks          

----------------------------------------------------------------------------------------------
Step-01: Implement "ResponseStatusException" for getUserById
    - Custom Exceptions Layer: 
        - Create "UserNotFoundException" which extends Exception
    - Service Layer:
        - Update the getUserById method with throws Exception
        - Check for user and if not exists throw exception. 
    - Controller Layer: 
        - Update the getUserById method with try catch block.
        - In catch block, implement "ResponseStatusException".
    - Test using postman. 
        - Method: GET 
        - URI: http://localhost:8080/users/1001
    - Remove Trace in Exception.
        - When using DevTools, "server.error.include-stacktrace" will be set to always
        - change to never or on-trace-param
    - Verify the response again.      
        - Exception Message
        - HTTP Status Code           

----------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------
Step-02: Implement "ResponseStatusException" for updateUserById
    - Service Layer:
        - Update the updateUserById method with throws Exception
        - Check for user and if not exists throw exception. 
    - Controller Layer: 
        - Update the updateUserById method with try catch block.
        - In catch block, implement "ResponseStatusException".
    - Test using postman. 
        - Method: PUT 
        - URI: http://localhost:8080/users/1001
        - Request Body: 
        - Verify the response
            - Exception Message
            - HTTP Status Code   

--------------------------------------------------------------------------------------
Step-03: Implement "ResponseStatusException" for deleteUserById at Service Layer only
    - Service Layer:
        - Check for user and if not exists throw ResponseStatusException. 
    - Controller Layer: 
        - No changes
    - Test using postman. 
        - Method: DELETE 
        - URI: http://localhost:8080/users/1001
        - Verify the response
            - Exception Message
            - HTTP Status Code  

--------------------------------------------------------------------------------------
Step-04: Implement "ResponseStatusException" for createUser 
- Response Status Exception
    - Exception Layer
        - Create "UserExistsException" class
    - Service Layer
        - Update the createUser method with throws Exception
        - Verify if userexists based on username (Username is unique constraint)
        - If not null, throw exception.
    - Controller Layer
        - Update the createUser method with try catch block
        - In catch block, implement "ResponseStatusException".
    - Test using Postman
        - Method: POST 
        - URI: http://localhost:8080/users
        - Request Body: 
        - Verify the response
            - Exception Message
            - HTTP Status Code             

--------------------------------------------------------------------------------------
Step-05: For createUser Method, implement  HTTP Status code 201 & Location Header path
- HTTP Status Code 201 & Location Header with User URI path  
    - Controller Layer:
        - Implement Service to return HTTP Status code 201
        - Implement Service to return Location Header as user path 
    - Test using Postman
        - Method: POST 
        - URI: http://localhost:8080/users
        - Request Body: 
        - Verify the response
            - HTTP Status code
            - Location Header in Response Headers

--------------------------------------------------------------------------------------
Step-06: GIT Commit, Push, Merge to Master & Push

--------------------------------------------------------------------------------------