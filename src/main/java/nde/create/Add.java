package nde.create;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Add extends Action {

    public Add(){
        super();
    }

    public Add(String[] actionData) {
        super(actionData);
    }

    @Override
    public Action createAction(String[] actionData) {
        return new Add(actionData);
    }

    @Test
    public void test(){
        Assert.assertEquals(getIntParam(2) , getIntParam(0) + getIntParam(1) );
    }

}
