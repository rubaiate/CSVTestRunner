package nde.basic.add;

import org.testng.Assert;
import org.testng.annotations.Test;

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
