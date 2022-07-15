## Software Development Methods ##

This repository includes assignments of *Software Development Methods* course at University of Helsinki, plus a small Java application.
The repository is for study purpose only. 

### Bloodecode application ###

Blood test results can be complicated to interpret. With Bloodecode application users can "decode" the meaning of most common blood test abbreviations
(note: these may vary by country, the application currently interprets abbreviations used in Finland). A user can also search for reasons for abnormally
high or low values in blood test results. A user can also monitor their critical values and habits affecting them by adding notes to selfmonitor database.

The app is a Spring Boot project with Java 11, Maven and H2 database, developed on a Linux device.
The app is unlikely to run on a Windows device or earlier versions of Java.

#### Documentation ####

[Requirements specification](https://github.com/solatar/SoftwareDevelopmentMethods/blob/main/Documentation/requirementSpecification.md)

[Architecture](https://github.com/solatar/SoftwareDevelopmentMethods/blob/main/Documentation/architecture.md)

[Testing report](https://github.com/solatar/SoftwareDevelopmentMethods/blob/main/Documentation/testingReport.md)

[User manual](https://github.com/solatar/SoftwareDevelopmentMethods/blob/main/Documentation/userManual.md)

[Spent hours](https://github.com/solatar/SoftwareDevelopmentMethods/blob/main/Documentation/hours.md)

#### Terminal commands ####

Run the program
```
mvn spring-boot:run
```
Run tests
```
mvn test
```
Generate test coverage report
```
mvn jacoco:report
```
Generate Checkstyle report
```
mvn jxr:jxr checkstyle:checkstyle
```
Generate Javadoc
```
mvn javadoc:javadoc
```
Generate jar
```
mvn package
```
Note! Running the jar requires [codefile.csv](https://github.com/solasuo/SoftwareDevelopmentMethods/blob/main/Bloodecode/codefile.csv) located in the same folder. 

Run the jar
```
java -jar jarname.jar
```
