# CSV Test Runner
## Creating custom Test Runner based on TestNG

In java domain TestNG provides one of the most popular testing framework. You can find hundreads of documents which tutor you about using TestNG.
But if you can model your test scenarios and results other than java programming it will 
be more convenient specially if quality analyzing or testing team is not more into programming.

TestNG provides several hooks and programmable API for extendability. 
Here I am going to show you how to implement very simple custom test runner utilizing such features in TestNG.
 
### CSV Test Runner
The basic concept of this test runner is implementing actions and calling them with many combination of parameters and validating results. 
Ex
We can validate "Add" functionality with many test cases - for different inputs and results. So same test code can be used in several test cases.

#### Specification
In this project test cases are defined in a csv file.

###### Format of test csv
ID|ACTION|PARAM-1|PARAM-2....|PARAM-N

| ID               | unique id for the test |
|:---              |:-----------------------|
| ACTION           | name of the test       |
| PARAM-1..PARAM-N | parameters             |

###### 1,Add,1,5,6

So we are validating Add function which should return 6 after adding 1 and 5.

#### By following this tutorial you would know,
1. How to run testNg tests
1. How to run testNg tests programmatically
1. How to load dynamic tests from other sources and run them

### Chapters
1. [TestNG Test Runner Basic](docs/TestNGCSVBasicRunner.md)

You are running first TestNG test case.
Next step is running given test case programmatically. Now it is bit advance. 
When function is annotated with @Factories, TestNG expects the function to return Test class array

Now test can be executed using green fast forward button. So it is possible to run test case programmatically;
So implementation is working fine. But in IDE you can see test name is ugly 

Provide test case name
The add test class should be derived from ITest interface and getTestName should be implemented. Now it is possible to pass custom test name;

passing test data from csv file is very easy. 




