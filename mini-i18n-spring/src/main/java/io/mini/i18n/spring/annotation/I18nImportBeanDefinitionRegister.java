package io.mini.i18n.spring.annotation;

import io.microsphere.spring.util.BeanRegistrar;
import io.microsphere.util.ArrayUtils;
import io.mini.i18n.spring.DelegatingServiceMessageSource;
import io.mini.i18n.spring.beans.factory.ServiceMessageSourceFactoryBean;
import io.mini.i18n.spring.beans.factory.config.I18nBeanPostProcessor;
import io.mini.i18n.spring.beans.factory.support.ServiceMessageSourceBeanLifecyclePostProcessor;
import io.mini.i18n.spring.constants.I18nConstants;
import io.mini.i18n.spring.context.I18nApplicationListener;
import io.mini.i18n.spring.context.MessageSourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

public class I18nImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = EnableI18n.class;

    private static Logger logger = LoggerFactory.getLogger(ANNOTATION_TYPE);

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (isEnabled()) {
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(ANNOTATION_TYPE.getName()));
            registerServiceMessageSourceBeanDefinitions(attributes, registry);
            registerMessageSourceAdapterBeanDefinition(attributes, registry);
            registerI18nApplicationListenerBeanDefinition(registry);
            registerBeanPostProcessorBeanDefinitions(registry);
        }
    }

    private void registerServiceMessageSourceBeanDefinitions(AnnotationAttributes attributes, BeanDefinitionRegistry registry) {
        Set<String> sources = resolveSources(attributes);

        for (String source : sources) {
            String beanName = source + "ServiceMessageSource";
            BeanRegistrar.registerBeanDefinition(registry, beanName, ServiceMessageSourceFactoryBean.class, source);
        }

        AbstractBeanDefinition primaryBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(DelegatingServiceMessageSource.class)
                .setPrimary(true)
                .getBeanDefinition();

        registry
                .registerBeanDefinition(I18nConstants.SERVICE_MESSAGE_SOURCE_BEAN_NAME, primaryBeanDefinition);
    }

    private Set<String> resolveSources(AnnotationAttributes attributes) {
        Set<String> sources = new LinkedHashSet<>();
        initSources(sources, () -> environment.getProperty(I18nConstants.SOURCES_PROPERTY_NAME, String[].class, ArrayUtils.EMPTY_STRING_ARRAY));
        initSources(sources, () -> attributes.getStringArray("sources"));
        return sources;
    }

    private void initSources(Set<String> sources, Supplier<String[]> sourcesSupplier) {
        for (String source : sourcesSupplier.get()) {
            sources.add(environment.resolvePlaceholders(source));
        }
    }


    private void registerMessageSourceAdapterBeanDefinition(AnnotationAttributes attributes, BeanDefinitionRegistry registry) {
        boolean exposeMessageSource = attributes.getBoolean("exposeMessageSource");
        if (exposeMessageSource) {
            BeanRegistrar.registerBeanDefinition(registry, AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSourceAdapter.class);
        }
    }

    private void registerI18nApplicationListenerBeanDefinition(BeanDefinitionRegistry registry) {
        BeanRegistrar.registerBeanDefinition(registry, I18nApplicationListener.class);
    }

    private void registerBeanPostProcessorBeanDefinitions(BeanDefinitionRegistry registry) {
        BeanRegistrar.registerBeanDefinition(registry, I18nBeanPostProcessor.class);
        BeanRegistrar.registerBeanDefinition(registry, ServiceMessageSourceBeanLifecyclePostProcessor.class);
    }


    private boolean isEnabled() {
        String propertyName = I18nConstants.ENABLED_PROPERTY_NAME;
        boolean enabled = environment.getProperty(propertyName, boolean.class, I18nConstants.DEFAULT_ENABLED);
        logger.debug("Microsphere i18n is {} , cased by the Spring property[name : '{}', default value : '{}']",
                enabled ? "enabled" : "disabled",
                propertyName,
                I18nConstants.DEFAULT_ENABLED);
        return enabled;
    }
}
