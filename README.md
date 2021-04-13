## Software Development Methods ##

This repository includes assignments of *Software Development Methods* course at University of Helsinki, plus a small Java application.
The repository is for study purpose only.

Progress of the application is being tracked in [spent hours](https://github.com/solasuo/SoftwareDevelopmentMethods/blob/main/Documentation/hours.md)

### Terminal commands ###

Run the program
```
mvn compile exec:java -Dexec.mainClass=bloodecode.BloodecodeApplication
```
Run tests
```
mvn test
```
Generate test coverage report
```
mvn jacoco:report
```
Generate jar
```
mvn package
```
