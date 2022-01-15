package com.nx.customIoc.bean.factory.support;

import com.nx.customIoc.bean.definitor.BeanDefinition;
import com.nx.customIoc.bean.factory.registry.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wep
 * @Since: 2021/5/10 21:18
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    @Override
    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
    }
}
