package nde.create;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTest extends Action {

    public AddTest(){
        super();
    }

    public AddTest(String[] actionData) {
        super(actionData);
    }

    @Override
    public Action createAction(String[] actionData) {
        return new AddTest(actionData);
    }

    @Test
    public void test(){
        Assert.assertEquals(getIntParam(2) , getIntParam(0) + getIntParam(1) );
    }

}
