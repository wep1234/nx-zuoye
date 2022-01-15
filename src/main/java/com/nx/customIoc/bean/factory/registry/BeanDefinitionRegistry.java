package com.nx.customIoc.bean.factory.registry;

import com.nx.customIoc.bean.definitor.BeanDefinition;

/**
 * @author: wep
 * @Since: 2021/5/10 20:53
 */
public interface BeanDefinitionRegistry {

     BeanDefinition getBeanDefinition(String name);

     void registryBeanDefinition(String name,BeanDefinition beanDefinition);
}
