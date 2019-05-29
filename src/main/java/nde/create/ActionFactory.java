package nde.create;


import org.reflections.Reflections;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ActionFactory {
    private Map<String, Action> actions = new HashMap<>();

    public void init() throws InstantiationException, IllegalAccessException{
        Reflections reflections = new Reflections();

        for (Class<? extends Action> clazz : reflections.getSubTypesOf(Action.class)) {
            Action action = clazz.newInstance();
            actions.put(clazz.getSimpleName(), action);
        }
    }

    public Action createAction(String[] actionData){
        String actionName = actionData[1];
        if(actions.containsKey(actionName)){
            return actions.get(actionName).createAction(actionData);
        }
        return null;
    }
}
