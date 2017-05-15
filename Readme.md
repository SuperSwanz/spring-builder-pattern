# spring-builder-pattern

This sample app in spring boot showcase the use of builder pattern along with embedded H2 database.

## Getting Started

Git clone the project on your local machine and add it to your workspace

### Prerequisites

For runnning this, you will need
- Java 1.8
- Gradle support - In Eclipse editor, goto help -> eclipse marketplace -> search for buildship (buildship gradle integration) and install it.

## Running the app

For running the app,
- Open application.properties file and set the "Server Config", "H2 Config", "Datasource Config" as per your choice. No need to change the "Security Config" as spring security is not implemented.
- Once, changes are done in application.properties, open "CommonDesignPatternsApplication.Java" file and select run/debug.
- If app starts successfull, goto ** http://localhost:8080/designs **. Error json will be served as response.


## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management
