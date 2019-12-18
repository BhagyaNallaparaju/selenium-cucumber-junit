## selenium-cucumber-java-junit
Cucumber and Selenium are two popular technologies.
Most of the organizations use Selenium for functional testing and it is also portable framework for
testing web applications. 
Organizations which are using Selenium want to integrate Cucumber with selenium as Cucumber makes it easy to read and to understand the application flow.
Cucumber also benefits the client to understand the application code as it uses Gherkin language which is in Plain Text. 
Anyone in the organization can understand the behavior of the software. The syntax's of Gherkin is in simple text which is readable and understandable.
Cucumber tool is based on the Behavior Driven Development framework that acts as the bridge between the following people:
- Software Engineer and Business Analyst
- Manual Tester and Automation Tester
- Manual Tester and Developers

***

## Usage of config.properties 

You can set the values in config.properties(src/main/resources/configfiles) before running
To run in multiple env's like qa/dev/uat/prod, create a file with _config.Properties extension
(example: qa_config_Properties) and add all the keys from config_Properties and corresponding
 details of values respectively
All the component urls, reports and browser related details are added in config.Properties

## Usage of locator.properties 
Selenium xpath locators are placed under this folder(src/main/resources/configfiles)

## Usage of data.properties 
All the input data information is added to this folder(src/main/resources/configfiles)

## Usage of .feature file
All the cucumber scenarios are added in features/demo/{featureName}.feature 
Each functionality is executed in seperate feature file

 ## Usage of steps.java file
     src/test/java/stepDefinition/Steps.java
Steps generated from feature files are added in here 

***

## Test  Parameters should pass for Maven
    browser = "firefox" || "chrome" || "ie" || "safari"
Set browser value to any of the above in config_Properties file

***

# Different ways to run the test framework
You can run the tests by either executing mvn test or from run option of your IDE

## Running tests from terminal
    mvn clean install || mvn verify || mvn test (To run in default env i.e config_Properties file)
    mvn test -Dcucumber.options="--tags @selectmenu" (To run single feature using tags)
    mvn test -Dcucumber.options="--tags @TEST-Widgets-01" (To run single testcase or scenario using
     tags)
    mvn test -Denv=it (To run env specific by changing to it,dev env)
    mvn test -Dbrowser=chrome (To run features on specific browser
    
 ##Running tests from IDE 
    runner.java (User can execute this script from Test runner as well)
 
 ***
 ## Path to check reports after test execution
     Test_Execution_Report/HTML_report/TestReport
 Navigate to the above path and check the reports with latest timestamp
 ***