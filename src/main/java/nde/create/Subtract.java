package nde.create;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Subtract extends Action {

    public Subtract(){
        super();
    }

    public Subtract(String[] actionData) {
        super(actionData);
    }

    @Override
    public Action createAction(String[] actionData) {
        return new Subtract(actionData);
    }

    @Test
    public void test(){
        Assert.assertEquals(getIntParam(2) , getIntParam(0) - getIntParam(1) );
    }

}
