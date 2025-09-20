package io.mini.i18n.spring.beans.factory.config;

import com.sun.org.apache.xml.internal.security.utils.I18n;
import io.microsphere.spring.util.BeanUtils;
import io.microsphere.util.ClassLoaderUtils;
import io.mini.i18n.spring.context.MessageSourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class I18nBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(I18nBeanPostProcessor.class);

    private static final ClassLoader classLoader = I18nBeanPostProcessor.class.getClassLoader();

    private static final Class<?> VALIDATOR_FACTORY_CLASS = ClassLoaderUtils.resolveClass("javax.validation.ValidatorFactory", classLoader);

    private static final Class<?> LOCAL_VALIDATOR_FACTORY_BEAN_CLASS = ClassLoaderUtils.resolveClass("org.springframework.validation.beanvalidation.LocalValidatorFactoryBean", classLoader);

    private final ConfigurableApplicationContext context;

    public I18nBeanPostProcessor(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (VALIDATOR_FACTORY_CLASS == null || LOCAL_VALIDATOR_FACTORY_BEAN_CLASS == null) {
            return bean;
        }

        Class<?> beanType = AopUtils.getTargetClass(bean);
        if (LOCAL_VALIDATOR_FACTORY_BEAN_CLASS.equals(beanType)) {
            MessageSourceAdapter messageSourceAdapter = BeanUtils.getOptionalBean(this.context, MessageSourceAdapter.class);
            if (messageSourceAdapter == null) {
                logger.warn("No MessageSourceAdapter BeanDefinition was found!");
            } else {
                LocalValidatorFactoryBean localValidatorFactoryBean = (LocalValidatorFactoryBean) bean;
                localValidatorFactoryBean.setValidationMessageSource(messageSourceAdapter);
                logger.debug("LocalValidatorFactoryBean[name : '{}'] is associated with MessageSource : {}", beanName, messageSourceAdapter);
            }
        }
        return bean;
    }
}
