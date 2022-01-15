package com.nx.customIoc.bean.factory.registry;

/**
 * @author: wep
 * @Since: 2021/5/10 20:51
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);

    void registorySingleton(String name,Object bean);
}
