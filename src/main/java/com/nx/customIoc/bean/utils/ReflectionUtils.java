package com.nx.customIoc.bean.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author: wep
 * @Since: 2021/5/10 21:08
 */
public class ReflectionUtils {

    public static Object createObject(Class<?> cls,Object ...args) {
        try {
            Constructor<?> ctor = cls.getConstructor();
            return ctor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setProperty(Object singleton, String name, Object valueToUse) {

        try {
           Field field = singleton.getClass().getDeclaredField(name);
           field.setAccessible(true);
           field.set(singleton,valueToUse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getTypeByFieldName(String className, String name) {
        try {
            Class<?> aClass = Class.forName(className);
            Field field = aClass.getDeclaredField(name);
            return field.getType();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
