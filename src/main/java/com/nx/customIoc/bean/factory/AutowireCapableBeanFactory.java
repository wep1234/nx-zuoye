package com.nx.customIoc.bean.factory;

/**
 * @author: wep
 * @Since: 2021/5/10 20:50
 */
public interface AutowireCapableBeanFactory {

    Object createBean(Class<?> type);
}
