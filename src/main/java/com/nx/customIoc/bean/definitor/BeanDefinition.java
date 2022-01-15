package com.nx.customIoc.bean.definitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wep
 * @Since: 2021/5/10 20:39
 */
public class BeanDefinition {

    private String beanName;

    private String className;

    private String scope;

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    private static final String SCOPE_SINGLETON = "singleton";

    private static final String SCOPE_PROTOTYPE = "prototype";

    public BeanDefinition(String beanName, String className) {
        this.beanName = beanName;
        this.className = className;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public boolean isSingleton(){
        return SCOPE_SINGLETON.equals(this.scope);
    }

    public boolean isProtoType(){
        return SCOPE_PROTOTYPE.equals(this.scope);
    }
}
