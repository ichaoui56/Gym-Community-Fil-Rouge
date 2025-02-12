package org.filrouge.gymcommunity.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {
    public static <T> String getClassName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }


    public static Object getConstructor(Class<?> clazz, Object... args) throws NoSuchMethodException {
        return clazz.getConstructor(getParameterTypes(args));
    }

    public static Object newInstance(Constructor<?> constructor, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return constructor.newInstance(args);
    }

    public static Class<?>[] getParameterTypes(Object... args) {
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }
}
