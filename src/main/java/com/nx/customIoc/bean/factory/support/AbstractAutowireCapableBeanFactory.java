package com.nx.customIoc.bean.factory.support;

import com.nx.customIoc.bean.definitor.BeanDefinition;
import com.nx.customIoc.bean.definitor.PropertyValue;
import com.nx.customIoc.bean.definitor.RuntimeBeanReference;
import com.nx.customIoc.bean.definitor.TypedStringValue;
import com.nx.customIoc.bean.factory.AutowireCapableBeanFactory;
import com.nx.customIoc.bean.utils.ReflectionUtils;

import java.util.List;

/**
 * @author: wep
 * @Since: 2021/5/10 21:03
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    @Override
    protected Object createBean(BeanDefinition bd) {
        String className = bd.getClassName();

        //获取对应的class
        Class<?> cls = resoleClassName(className);

        if(cls == null){
            return null;
        }
        //实例化
        Object singleton = createBeanInstance(cls);

        //属性填充
        populateBean(singleton,bd);

        //初始化方法调用init-method


        return singleton;
    }

    private void populateBean(Object singleton, BeanDefinition bd) {
       List<PropertyValue> propertyValueList = bd.getPropertyValueList();

       for(PropertyValue pv : propertyValueList){
           String name = pv.getName();
           Object value = pv.getValue();
           //真正的值
           Object valueToUse = null;
           if(value instanceof RuntimeBeanReference){
               RuntimeBeanReference ref = (RuntimeBeanReference) value;
               valueToUse = getBean(ref.getRef());
           }else if(value instanceof TypedStringValue){
               TypedStringValue tsv = (TypedStringValue) value;
               String val = tsv.getValue();
               Class<?> targetType = tsv.getTargetType();
               if(targetType == Integer.class){
                   valueToUse = Integer.parseInt(val);
               }else if(targetType == String.class){
                   valueToUse = val;
               }else if(targetType == Long.class){
                   valueToUse = Long.parseLong(val);
               }else{
                   //8种基本类型
               }
           }
           ReflectionUtils.setProperty(singleton,name,valueToUse);
       }
    }

    private Object createBeanInstance(Class<?> cls) {
        return ReflectionUtils.createObject(cls);
    }

    private Class<?> resoleClassName(String className) {

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Object createBean(Class<?> type) {
        return null;
    }




}
