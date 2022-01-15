package com.nx.customIoc.bean.factory.support;

import com.nx.customIoc.bean.definitor.BeanDefinition;
import com.nx.customIoc.bean.factory.BeanFactory;
import com.nx.customIoc.bean.factory.registry.support.DefaultSingletonBeanRegistry;

/**
 * @author: wep
 * @Since: 2021/5/10 20:59
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        Object singleton = getSingleton(beanName);

        if(singleton != null){
            return singleton;
        }

        BeanDefinition bd = getBeanDefinition(beanName);
        
        if(bd.isSingleton()){
            singleton = createBean(bd);
            //单例的话放到缓存中
            registorySingleton(beanName,singleton);
        }else if(bd.isProtoType()){
            singleton = createBean(bd);
        }
        return singleton;
    }

    protected abstract Object createBean(BeanDefinition bd);

    protected abstract BeanDefinition getBeanDefinition(String beanName);
}
