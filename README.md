# master-restful-apis-with-spring-boot-2
---------------------------------------------------------------------------
Step-00: Introduction

---------------------------------------------------------------------------
Step-01: New GIT branch (usign IDE)
    - git Branch name: 10-01-SpringBoot-DTOS-ModelMapper
    - Create new local branch

---------------------------------------------------------------------------
Step-02: Update pom.xml with ModelMapper dependency
    - Update pom.xml
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>2.3.5</version>
        </dependency>        

---------------------------------------------------------------------------        
Step-03: Define ModelMapper Bean
    - Config Layer
        - Create a config package
        - Create AppConfig class
        - Define ModelMapper bean in configuration class

---------------------------------------------------------------------------        
Step-04: Create a DTO class with name as UserMmDto    
    - DTO Layer
        - Create UserMmDto with fields userid, username

---------------------------------------------------------------------------        
Step-05: Create getUserDtoById method with Entity to DTO Conversion logic
    - Controller Layer
        - Create new controller named UserModelMapperController
        - Annotate with @RestController
        - Annoteate with @RequestMapping("/modelmapper/users")
        - Create getUserDtoById method 
            - GET /{id}    
        - Implement ModelMapper converion logic            
    - Test using Postman          

---------------------------------------------------------------------------
Step-06: Commit Code (using IDE)
    - Commit code
    - Push branch to Remote repo

---------------------------------------------------------------------------

