package nde.create;

import org.testng.ITest;

public abstract class Action implements ITest {
    private String id;
    private String[] actionData;

    public Action(){}

    private String actionName;
    public Action(String[] actionData){
        this.id = actionData[0];
        this.actionName = actionData[1];
        this.actionData = actionData;
    }

    public String getId() {
        return id;
    }

    public String getStringParam(int index){
        return actionData[index + 2];
    }

    public int getIntParam(int index){
        return Integer.parseInt(getStringParam(index));
    }

    @Override
    public String getTestName() {
        return id;
    }

    public String getActionName() {
        System.out.println("GetActionName");
        return actionName;
    }

    public abstract Action createAction(String[] actionData);
}
