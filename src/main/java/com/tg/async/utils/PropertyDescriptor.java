package com.tg.async.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.util.Locale.ENGLISH;

/**
 * Created by twogoods on 2018/5/2.
 */
public class PropertyDescriptor {
    private Class clazz;
    private String name;
    private Class type;
    private Method setter;

    public PropertyDescriptor(Class clazz, String name) {
        this.clazz = clazz;
        this.name = name;
        try {
            type = clazz.getDeclaredField(name).getType();
            setter = clazz.getDeclaredMethod(getSetterName(), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setvalue(Object object, Object value) throws Exception {
        setter.invoke(object, new Object[]{value});
    }


    private String getSetterName() {
        if (name == null || name.length() == 0) {
            return name;
        }
        return "set" + name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);

    }
}
