package com.x_s.s2shop.common.utils;

import com.x_s.s2shop.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtilsCustom {

    /**
     * 获取对象为空的字段名称
     */
    public static List<String> getNullPropertiesList(Object object){
        Class<?> clazz = object.getClass();
        PropertyDescriptor[] descriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(clazz);
        return Arrays.stream(descriptors).map(d -> {
            try {
                if (d.getReadMethod().invoke(object) != null)
                    return null;
            } catch (Exception ignored) {

            }
            return d.getName();
        }).filter(StringUtils::hasText).collect(Collectors.toList());
    }

    /**
     * 获取对象不为空的字段名称
     */
    public static List<String> getNotNullPropertiesList(Object object){
        Class<?> clazz = object.getClass();
        PropertyDescriptor[] descriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(clazz);
        return Arrays.stream(descriptors).map(d -> {
            Method readMethod = d.getReadMethod();
            Object value = null;
            try {
                value = readMethod.invoke(object);
            } catch (Exception ignored) {

            }

            if (value != null)
                return d.getName();
            return null;
        }).filter(s -> !StringUtils.isEmpty(s) && !s.equals("class")).collect(Collectors.toList());
    }
    /**
     * 获取对象不为空的字段名称
     */
    public static String[] getNotNullProperties(Object object){
        List<String> list = getNotNullPropertiesList(object);
        return list.toArray(new String[list.size()]);
    }

    /**
     * 获取对象为空的字段名称
     */
    public static String[] getNullProperties(Object object){
        List<String> list = getNullPropertiesList(object);
        return list.toArray(new String[list.size()]);
    }

    /**
     * 将原对象不为空的字段赋值给目标对象
     */
    public static void copyWithNonNull(Object src, Object dest){
        BeanUtils.copyProperties(src, dest, BeanUtilsCustom.getNullProperties(src));
    }

    /**
     * 为对象的某个字段赋值
     */
    public static void setProperty(Object object, String key, Object... args){
        Class<?> objectClass = object.getClass();
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(objectClass, key);
        if (descriptor == null){
            throw new ServiceException(objectClass + " 没有 " + key + " 字段");
        }
        Method writeMethod = descriptor.getWriteMethod();
        if (writeMethod == null){
            throw new ServiceException(objectClass + " 的 " + key + " 没有 set 方法");
        }
        try {
            writeMethod.invoke(object, args);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 获取对象的某个字段的值
     */
    public static Object getProperty(Object object, String key, Object... args){
        Class<?> objectClass = object.getClass();
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(objectClass, key);
        if (descriptor == null){
            throw new ServiceException(objectClass + " 没有 " + key + " 字段");
        }
        Method readMethod = descriptor.getReadMethod();
        if (readMethod == null){
            throw new ServiceException(objectClass + " 的 " + key + " 没有 get 方法");
        }
        Object value;
        try {

            value = readMethod.invoke(object, args);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return value;
    }
}
