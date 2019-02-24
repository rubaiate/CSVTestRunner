package nde.create;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTest extends Action {

    public AddTest(String[] actionData) {
        super(actionData);
    }

    @Test
    public void test(){
        Assert.assertEquals(getIntParam(2) , getIntParam(0) + getIntParam(1) );
    }

}
