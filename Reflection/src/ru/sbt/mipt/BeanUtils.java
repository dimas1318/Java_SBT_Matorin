package ru.sbt.mipt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Map<String, Method> getters = new HashMap<>();
        List<Method> setters = new ArrayList<>();

        for (Method method : from.getClass().getDeclaredMethods()) {
            if (isGetterMethod(method)) {
                getters.put(method.getName().substring(3), method);
            }
        }

        for (Method method : to.getClass().getDeclaredMethods()) {
            if (isSetterMethod(method)) {
                setters.add(method);
            }
        }

        for (Method setter : setters) {
            String setterName = setter.getName().substring(3);
            if (getters.get(setterName) != null &&
                    (getters.get(setterName).getReturnType().equals(setter.getParameterTypes()[0]) ||
                     getters.get(setterName).getReturnType().isAssignableFrom(setter.getParameterTypes()[0]))) {
                try {
                    setter.invoke(to, getters.get(setterName).invoke(from));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isGetterMethod(Method method){
        if (!method.getName().startsWith("get") ||
                method.getParameterTypes().length != 0 ||
                void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    public static boolean isSetterMethod(Method method){
        if (!method.getName().startsWith("set") ||
                method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }
}
