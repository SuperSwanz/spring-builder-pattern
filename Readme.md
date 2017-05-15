# spring-builder-pattern

This sample app in spring boot showcase the use of builder pattern along with embedded H2 database.

## Getting Started

Git clone the project on your local machine and add it to your workspace

### Prerequisites

For runnning this, you will need
- Java 1.8
- Gradle support - In Eclipse editor, goto help -> eclipse marketplace -> search for buildship (buildship gradle integration) and install it.

## Brief

Builder pattern is used here to send and receive the Json. Jackson is used for serialization/deserialization.
--UserBuilder      -> Receives the json from the UI/api and converts that to the object.
--ErrorBuilder     -> Builds Error object if there is any server side error.
--ResponseBuilder  -> Wraps the response to be send back as a generic response for all methods.
--GreyResponseEntityExceptionHandler -> This extends ** ResponseEntityExceptionHandler ** to throw error/s from Spring controllers. This shows the good way to handle errors in Spring controller.
--GreyErrorController -> This implements ** ErrorController ** which is used to override Spring's default error implementation which throws "Whitelabel error page". The implementaion will return Json in our case.

## Running the app

For running the app,
- Open application.properties file and set the "Server Config", "H2 Config", "Datasource Config" as per your choice. No need to change the "Security Config" as spring security is not implemented.
- Once, changes are done in application.properties, open "CommonDesignPatternsApplication.Java" file and select run/debug.
- If app starts successfull, goto ** http://localhost:8080/designs **. Error json will be served as response.

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management
