package com.nx.customIoc.bean.definitor;

/**
 * @author: wep
 * @Since: 2021/5/10 20:38
 */
public class PropertyValue {

    private String name;

    //不知道是typeedStringValue还是RuntimeBeanReference
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
