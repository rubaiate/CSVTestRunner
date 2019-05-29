package nde.basic;

import org.testng.annotations.Factory;

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
