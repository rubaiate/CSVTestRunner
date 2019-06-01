
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


