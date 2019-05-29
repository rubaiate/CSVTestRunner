package nde.basic;

import org.testng.annotations.Test;

public class BasicParamTest {
    private int testId;
    BasicParamTest(int testId){
        this.testId = testId;
    }
    @Test
    public void hello(){
        System.out.println("Hello Test " + testId);
    }
}
