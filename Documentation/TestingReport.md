## Testing report ##

With no previous experience in testing a Spring Boot project, testing turned out to be much
more complicated than anticipated. Issues related to testing took about 60 % of the time 
spent on Bloodecode project.

The application has two classes -- Solver and -- MonitoredItem -- which are not Spring
components and could, therefore, be tested with normal JUnit tests.

Problems arose with classes with autowired dependencies and especially when trying to test
database operations. Tests annotated with @SpringBootTest were tried but it didn't seem 
sensible to load the whole application for testing a single class. Basically, all available
testing approaches were tried.

The final application has unit tests running with MockitoJunitRunner and using mocks for
injecting dependencies. This eliminates the need for manipulating database during testing. 
Since mocks are not real classes, each mock needs to be told what it is supposed to do and
this made writing tests quite slow.

TextUi class is excluded from the test coverage report. However, TextUi does some error 
checking of its own by validating commands. 
