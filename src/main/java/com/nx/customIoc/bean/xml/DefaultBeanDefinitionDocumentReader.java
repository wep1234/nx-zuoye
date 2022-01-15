package com.nx.customIoc.bean.xml;

import com.nx.customIoc.bean.definitor.BeanDefinition;
import com.nx.customIoc.bean.definitor.PropertyValue;
import com.nx.customIoc.bean.definitor.RuntimeBeanReference;
import com.nx.customIoc.bean.definitor.TypedStringValue;
import com.nx.customIoc.bean.factory.registry.BeanDefinitionRegistry;
import com.nx.customIoc.bean.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.List;

/**
 * @author: wep
 * @Since: 2021/5/10 21:21
 */
public class DefaultBeanDefinitionDocumentReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public DefaultBeanDefinitionDocumentReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void registerBeanDefinitions(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for(Element element : elements){
            String name = element.getName();
            if(name.equals("bean")){
                parseDefaultElement(element);
            }else {
                parseCustomElement(element);
            }
        }
    }

    private void parseCustomElement(Element element) {
    }

    private void parseDefaultElement(Element element) {
        try {
            if(element == null){
                return;
            }
            String id = element.attributeValue("id");
            String name = element.attributeValue("name");
            String className = element.attributeValue("class");
    
            if (StringUtils.isBlank(className)) {
                return;
            }
    
            
            Class<?> aclass = Class.forName(className);
        
            String scope = element.attributeValue("scope");
            if (StringUtils.isBlank(scope)) {
                scope = "singleton";
            }
    
            String beanName = StringUtils.isBlank(id) ? name : id;
            if (StringUtils.isBlank(beanName)) {
                beanName = aclass.getSimpleName();
            }
            BeanDefinition beanDefinition = new BeanDefinition(beanName, className);
            beanDefinition.setScope(scope);

            List<Element> propertyElements = element.elements();
            for(Element propertyElement : propertyElements){
                parsePropertyElement(beanDefinition,propertyElement);
            }
            beanDefinitionRegistry.registryBeanDefinition(beanName,beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
        if(beanDefinition == null || propertyElement == null){
            return;
        }
        //获取name属性
        String name = propertyElement.attributeValue("name");
        //获取value属性
        String value = propertyElement.attributeValue("value");
        //获取ref属性
        String ref = propertyElement.attributeValue("ref");

        if(StringUtils.isNotBlank(value) && StringUtils.isNotBlank(ref)){
            System.err.println("value和ref不能同时有值");
            return;
        }
        if(StringUtils.isNotBlank(value)){
            TypedStringValue typedStringValue = new TypedStringValue(value);

            Class<?> targetType = ReflectionUtils.getTypeByFieldName(beanDefinition.getClassName(),name);

            typedStringValue.setTargetType(targetType);
            PropertyValue propertyValue = new PropertyValue(name, typedStringValue);
            beanDefinition.addPropertyValue(propertyValue);
        }else if(StringUtils.isNotBlank(ref)){
            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            PropertyValue propertyValue = new PropertyValue(name,reference);
            beanDefinition.addPropertyValue(propertyValue);
        }else{
            System.err.println("value和ref不能同时为空");
            return;
        }
    }
}
