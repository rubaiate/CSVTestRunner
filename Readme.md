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

We are going to test addition function using the test framework.

###### 1,Add,1,5,6

So we are validating Add function which should return 6 after adding 1 and 5.

#### Using intellij
The project can be implemented without using and an IDE. But I prefer using an IDE since visual aspects provide more insight into development.
I am a fan of Intellij and the IDE integrates really well with TestNG. So Intellij is my default choice in this project.

[Create maven project in intellij](https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project)

#### @Test
@Test is the basic annotation for defining test case in TestNG(and in junit). 
You can annotate function with @Test. 

```java
public class BasicTest {
    @Test
    public void hello(){
        System.out.println("Hello World");
    }
}
```
Intellij will show green play button next to 'hello' function. Just click it. 

![Run button](images/BasicTest.PNG)

#### Basic Param Test Array
Going bit forward we will parameterize BasicTest as BasicParamTest and make array of them
```java
public class BasicParamTest {
    private int testId;
    public BasicParamTest(int testId){
        this.testId = testId;
    }
    @Test
    public void hello(){
        System.out.println("Hello Test " + testId);
    }
}
```
Ok. We have parameterized test.  
So how can we run array of them?  
The answer is testNg **@Factory** annotation.

```java
public class BasicParamTestArray {
    @Factory
    public Object[] testArray(){
        int testCount = 5;
        BasicParamTest[] tests = new BasicParamTest[testCount];
        for(int testId = 0; testId < testCount; testId++){
            tests[testId] = new BasicParamTest(testId);
        }

        return tests;
    }
}
```

![Basic Param Test Factory Run](images/BasicParamTestFactoryRun.PNG)  
You may see some peculiarities. We will address them later. For now we ran **"Test Array"**.

#### Add Test Array
Next we introduce new test class called AddTest and corresponding AddTestArray class. You can use that to verify add functionality.

```java
public class AddTest {
    private int param1;
    private int param2;
    private int result;

    public AddTest(int param1, int param2, int result){
        this.param1 = param1;
        this.param2 = param2;
        this.result = result;
    }

    @Test
    public void test(){
        Assert.assertEquals(result, param1 + param2);
    }
}
```

```java
public class AddTestArray {
    @Factory
    public Object[] testArray(){
        AddTest[] tests = new AddTest[3];
        tests[0] = new AddTest(3, 4, 7);
        tests[1] = new AddTest(24, 5, 29);
        tests[2] = new AddTest(67, 3, 70);

        return tests;
    }
}
```

### Loading Tests from CSV
Now it is possible to load tests from a csv.  
It is possible try out few test cases even with failure scenarios.

```java
public class AddTestArrayCSV {
    @Factory
    public Object[] testArray() throws IOException {
        String filePath = "src/main/resources/AddTestBasic.csv";
        try(FileReader fileReader = new FileReader(filePath)){
            CSVReader csvReader = new CSVReader(fileReader);
            
            List<String[]> dataRow = csvReader.readAll();
            
            Object[] addTests = dataRow.stream().map(this::createTest).toArray();
            return addTests;
        }
    }

    private AddTest createTest(String... params){
        int param1 = Integer.parseInt(params[0]);
        int param2 = Integer.parseInt(params[1]);
        int result = Integer.parseInt(params[2]);
        return new AddTest(param1, param2, result);
    }
}
```

Following items are covered in this tutorial
1. Running basic testng test
2. Running tests from an array
3. Loading tests from csv

The error handling logic is intentionally not followed in this tutorial to maintain clarity. Those items will be discussed in next tutorials.

You are running first TestNG test case.
Next step is running given test case programmatically. Now it is bit advance. 
When function is annotated with @Factories, TestNG expects the function to return Test class array

Now test can be executed using green fast forward button. So it is possible to run test case programmatically;
So implementation is working fine. But in IDE you can see test name is ugly 

Provide test case name
The add test class should be derived from ITest interface and getTestName should be implemented. Now it is possible to pass custom test name;

passing test data from csv file is very easy. 


Parsing test file name
@Parameter annotation is introduced to configure csv file name. Now when you run using green button you will encounter following error.
"Parameter 'filePath' is required by @Test on method createTests but has not been marked @Optional or defined"
You can edit the filePath using run/debug configuration menu.
1.Open configuration menu
2.Switch to parameters tab
3.Add filePath parameter

You can also create following xml file within test folder. Then update filePath parameter. Now you can right click on AddTest.xml and run test case.

Handle loadup errors
The framework will not properly show if exceptions are thrown within @Factory decorated createTests function. Then no test cases will be executed. 
But test run will be successfull which is not the expected behaviour. 
So any execption thrown while loadup will be stored and rethrown in @BeforeSuite decorated validateLoadup function.

Now most of the TestNG api related functionalities are completed. Time for light polish on framework.

Lets introduce generic action class. It will provide generic functionalities needed by an Action.




