package io.mini.i18n.spring.beans.factory;

import io.mini.i18n.AbstractServiceMessageSource;
import io.mini.i18n.CompositeServiceMessageSource;
import io.mini.i18n.ReloadableResourceServiceMessageSource;
import io.mini.i18n.ServiceMessageSource;
import io.mini.i18n.spring.constants.I18nConstants;
import io.mini.i18n.spring.context.ResourceServiceMessageSourceChangedEvent;
import io.mini.i18n.spring.util.LocaleUtils;
import io.mini.i18n.util.I18nUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import static io.microsphere.spring.util.BeanUtils.invokeAwareInterfaces;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.KotlinDetector;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ServiceMessageSourceFactoryBean extends CompositeServiceMessageSource
        implements ReloadableResourceServiceMessageSource,
        InitializingBean, DisposableBean,
        EnvironmentAware, BeanClassLoaderAware, ApplicationContextAware,
        FactoryBean<ReloadableResourceServiceMessageSource>, ApplicationListener<ResourceServiceMessageSourceChangedEvent>,
        Ordered {
    private static final Logger logger = LoggerFactory.getLogger(ServiceMessageSourceFactoryBean.class);

    private final String source;

    private ApplicationContext context;

    private ClassLoader classLoader;

    private ConfigurableEnvironment environment;


    private int order;

    public ServiceMessageSourceFactoryBean(String source) {
        this(source, Ordered.LOWEST_PRECEDENCE);
    }

    public ServiceMessageSourceFactoryBean(String source, int order) {
        this.source = source;
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String getSource() {
        return source;
    }

    @NonNull
    @Override
    public Locale getLocale() {
        Locale locale = LocaleUtils.getLocaleFromLocaleContext();
        if (locale == null) {
            locale = super.getLocale();
        }
        return locale;
    }

    @Override
    public String toString() {
        return "ServiceMessageSourceFactoryBean{" +
                "serviceMessageSources = " + getServiceMessageSources() +
                ", order=" + order +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void init() {
        this.setServiceMessageSources(initServiceMessageSources());
    }

    private List<AbstractServiceMessageSource> initServiceMessageSources() {
        List<String> factoryNames = SpringFactoriesLoader.loadFactoryNames(AbstractServiceMessageSource.class, classLoader);

        Locale defaultLocale = resolveDefaultLocale(environment);
        List<Locale> supportedLocales = resolveSupportedLocales(environment);

        ArrayList<AbstractServiceMessageSource> serviceMessageSources = new ArrayList<>(factoryNames.size());

        for (String factoryName : factoryNames) {
            Class<?> factoryClass = ClassUtils.resolveClassName(factoryName, classLoader);
            Constructor<?> constructor = ClassUtils.getConstructorIfAvailable(factoryClass, String.class);
            AbstractServiceMessageSource serviceMessageSource = (AbstractServiceMessageSource) BeanUtils.instantiateClass(constructor, source);
            serviceMessageSources.add(serviceMessageSource);

            invokeAwareInterfaces(serviceMessageSource, context);

            serviceMessageSource.setDefaultLocale(defaultLocale);
            serviceMessageSource.setSupportedLocales(supportedLocales);
            serviceMessageSource.init();
        }

        Collections.sort(serviceMessageSources);

        return serviceMessageSources;
    }

    private List<Locale> resolveSupportedLocales(ConfigurableEnvironment environment) {
      final List<Locale> supportedLocales;
        String propertyName = I18nConstants.SUPPORTED_LOCALES_PROPERTY_NAME;
        List<String> locales = environment.getProperty(propertyName, List.class, Collections.emptyList());
        if (locales.isEmpty()) {
            supportedLocales = getSupportedLocales();
            logger.debug("Support Locale list configuration property [name : '{}'] not found, use default value: {}", propertyName, supportedLocales);
        } else {
            supportedLocales = locales.stream().map(StringUtils::parseLocale).collect(Collectors.toList());
            logger.debug("List of supported Locales parsed by configuration property [name : '{}']: {}", propertyName, supportedLocales);
        }
        return Collections.unmodifiableList(supportedLocales);
    }

    private Locale resolveDefaultLocale(ConfigurableEnvironment environment) {
        String propertyName = I18nConstants.DEFAULT_LOCALE_PROPERTY_NAME;
        String localeValue = environment.getProperty(propertyName);
        final Locale locale;
        if(!StringUtils.hasText(localeValue)) {
            locale = getDefaultLocale();
            logger.debug("Default Locale configuration property [name : '{}'] not found, use default value: '{}'", propertyName, locale);
        } else {
            locale = StringUtils.parseLocale(localeValue);
            logger.debug("Default Locale : '{}' parsed by configuration properties [name : '{}']", propertyName, locale);
        }
        return locale;
    }


    @Override
    public void setEnvironment(Environment environment) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, environment, "The 'environment' parameter must be of type ConfigurableEnvironment");
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public ReloadableResourceServiceMessageSource getObject() throws Exception {
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return ReloadableResourceServiceMessageSource.class;
    }

    @Override
    public void onApplicationEvent(ResourceServiceMessageSourceChangedEvent event) {
        Iterable<String> changedResources = event.getChangedResources();
        logger.debug("Receive event change resource: {}", changedResources);
        for (ServiceMessageSource serviceMessageSource : getAllServiceMessageSources()) {
            if (serviceMessageSource instanceof ReloadableResourceServiceMessageSource) {
                ReloadableResourceServiceMessageSource reloadableResourceServiceMessageSource =
                        (ReloadableResourceServiceMessageSource) serviceMessageSource;
                if (reloadableResourceServiceMessageSource.canReload(changedResources)) {
                    reloadableResourceServiceMessageSource.reload(changedResources);
                    logger.debug("change resource [{}] activate {} reloaded", changedResources, reloadableResourceServiceMessageSource);
                }
            }
        }
    }

    public List<ServiceMessageSource> getAllServiceMessageSources() {
        return I18nUtils.findAllServiceMessageSource(this);
    }
}
