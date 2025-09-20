package io.mini.i18n.spring.context;

import io.microsphere.spring.util.BeanUtils;
import io.microsphere.util.ClassLoaderUtils;
import io.mini.i18n.ServiceMessageSource;
import io.mini.i18n.spring.constants.I18nConstants;
import io.mini.i18n.util.I18nUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;


public class I18nApplicationListener implements SmartApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(I18nApplicationListener.class);

    private static final String ACCEPT_HEADER_LOCALE_RESOLVER_CLASS_NAME = "org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver";

    private static final Class<?> ACCEPT_HEADER_LOCALE_RESOLVER_CLASS = ClassLoaderUtils.resolveClass(ACCEPT_HEADER_LOCALE_RESOLVER_CLASS_NAME);

    private static final Class<?>[] SUPPORTED_EVENT_TYPES = {
            ContextRefreshedEvent.class,
            ContextClosedEvent.class
    };

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ObjectUtils.containsElement(SUPPORTED_EVENT_TYPES, eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            onContextRefreshedEvent((ContextRefreshedEvent) event);
        } else if (event instanceof ContextClosedEvent) {
            onContextClosedEvent((ContextClosedEvent) event);
        }
    }

    private void onContextRefreshedEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();

        initializeServiceMessageSource(context);

        initializeAcceptHeaderLocaleResolver(context);
    }

    private void initializeServiceMessageSource(ApplicationContext context) {
        ServiceMessageSource serviceMessageSource = context.getBean(I18nConstants.SERVICE_MESSAGE_SOURCE_BEAN_NAME, ServiceMessageSource.class);
        I18nUtils.setServiceMessageSource(serviceMessageSource);
    }

    @SuppressWarnings("unchecked")
    private void initializeAcceptHeaderLocaleResolver(ApplicationContext context) {
        if (ACCEPT_HEADER_LOCALE_RESOLVER_CLASS == null) {
            logger.debug("The class '{}' was not found!", ACCEPT_HEADER_LOCALE_RESOLVER_CLASS_NAME);
            return;
        }

        Class<AcceptHeaderLocaleResolver> beanClass = (Class<AcceptHeaderLocaleResolver>) ACCEPT_HEADER_LOCALE_RESOLVER_CLASS;
        List<AcceptHeaderLocaleResolver> acceptHeaderLocaleResolvers = BeanUtils.getSortedBeans(context, beanClass);

        if (acceptHeaderLocaleResolvers.isEmpty()) {
            logger.debug("The '{}' Spring Bean was not found!", ACCEPT_HEADER_LOCALE_RESOLVER_CLASS_NAME);
            return;
        }

        ServiceMessageSource serviceMessageSource = BeanUtils.getOptionalBean(context, ServiceMessageSource.class);

        for (AcceptHeaderLocaleResolver acceptHeaderLocaleResolver : acceptHeaderLocaleResolvers) {
            Locale defaultLocale = serviceMessageSource.getDefaultLocale();
            List<Locale> supportedLocales = serviceMessageSource.getSupportedLocales();
            acceptHeaderLocaleResolver.setDefaultLocale(defaultLocale);
            acceptHeaderLocaleResolver.setSupportedLocales(supportedLocales);
            logger.debug("AcceptHeaderLocaleResolver Bean associated with default Locale : '{}' , list of supported Locales : {}", defaultLocale, supportedLocales);
        }

    }

    private void onContextClosedEvent(ContextClosedEvent event) {
        I18nUtils.destroyServiceMessageSource();
    }
}
