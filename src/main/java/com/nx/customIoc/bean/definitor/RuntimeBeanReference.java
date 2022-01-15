package com.nx.customIoc.bean.definitor;

/**
 * @author: wep
 * @Since: 2021/5/10 20:36
 */
public class RuntimeBeanReference {

    //引用
    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public RuntimeBeanReference(String ref) {
        this.ref = ref;
    }
}
