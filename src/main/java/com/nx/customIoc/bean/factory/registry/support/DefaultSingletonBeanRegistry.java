package com.nx.customIoc.bean.factory.registry.support;

import com.nx.customIoc.bean.factory.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wep
 * @Since: 2021/5/10 20:56
 * 缓存单例bean
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {


    private Map<String,Object> singletions = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletions.get(name);
    }

    @Override
    public void registorySingleton(String name, Object bean) {
        singletions.put(name,bean);
    }
}
