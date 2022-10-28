package me.lord.plugin.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NMS {

    private Class<?> currentClass;
    private Object parent;

    public NMS(String string) {
        this.currentClass = Utils.getNMSClass(string);
    }

    public NMS(Object parent) {
        this.parent = parent;
        this.currentClass = parent.getClass();
    }

    public Constructor<?> getConstructor(Class<?>... parameterTypes) {
        try {
            return this.currentClass.getConstructor(parameterTypes);
        } catch (SecurityException | NoSuchMethodException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public Object getMethod(String methodName) {
        try {
            Method method = this.currentClass.getMethod(methodName);
            return method.invoke(this.parent);
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IllegalAccessException var3) {
            var3.printStackTrace();
            return null;
        }
    }

}
