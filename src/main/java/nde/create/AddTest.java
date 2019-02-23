package nde.create;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.Test;

public class AddTest implements ITest {
    private int param1;
    private int param2;
    private int result;
    private String testName;

    public AddTest(String testName, int param1, int param2, int result){
        this.testName = testName;
        this.param1 = param1;
        this.param2 = param2;
        this.result = result;
    }

    @Test
    public void test(){
        Assert.assertEquals(result, param1 + param2);
    }

    @Override
    public String getTestName() {
        return testName;
    }
}
