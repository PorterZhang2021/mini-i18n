package io.mini.i18n.spring.beans.factory.support;

import io.mini.i18n.ServiceMessageSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.StringUtils;

public class ServiceMessageSourceBeanLifecyclePostProcessor implements MergedBeanDefinitionPostProcessor {


    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition rootBeanDefinition, Class<?> beanType, String beanName) {
        if (ServiceMessageSource.class.isAssignableFrom(beanType)) {
            setInitMethodName(rootBeanDefinition, beanType);
            setDestroyMethodName(rootBeanDefinition, beanType);
        }
    }

    private void setInitMethodName(RootBeanDefinition beanDefinition, Class<?> beanType) {
        if (InitializingBean.class.isAssignableFrom(beanType)) {
            return;
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StringUtils.isEmpty(initMethodName)) {
            beanDefinition.setInitMethodName("init");
        }
    }

    private void setDestroyMethodName(RootBeanDefinition beanDefinition, Class<?> beanType) {
        if (DisposableBean.class.isAssignableFrom(beanType)) {
            return;
        }
        String destroyMethodName = beanDefinition.getDestroyMethodName();
        if (AbstractBeanDefinition.INFER_METHOD.equals(destroyMethodName)) {
            return;
        }
        if (StringUtils.isEmpty(destroyMethodName)) {
            beanDefinition.setDestroyMethodName("destroy");
        }
    }
}
