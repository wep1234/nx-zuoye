package com.nx.customIoc.bean.definitor;

/**
 * @author: wep
 * @Since: 2021/5/10 20:35
 */
public class TypedStringValue {

    private String value;

    private Class<?> targetType;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }
}
