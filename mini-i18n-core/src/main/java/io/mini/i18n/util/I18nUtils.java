package io.mini.i18n.util;

import io.mini.i18n.CompositeServiceMessageSource;
import io.mini.i18n.ServiceMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class I18nUtils {

    private static final Logger logger = LoggerFactory.getLogger(I18nUtils.class);

    private static volatile ServiceMessageSource serviceMessageSource;

    public static List<ServiceMessageSource> findAllServiceMessageSource(ServiceMessageSource serviceMessageSource) {
        LinkedList<ServiceMessageSource> serviceMessageSources = new LinkedList<>();
        initServiceMessageSource(serviceMessageSource, serviceMessageSources);
        return Collections.unmodifiableList(serviceMessageSources);
    }

    public static void initServiceMessageSource(ServiceMessageSource serviceMessageSource,
                                                List<ServiceMessageSource> serviceMessageSources) {
        if (serviceMessageSources instanceof CompositeServiceMessageSource) {
            CompositeServiceMessageSource compositeServiceMessageSource = (CompositeServiceMessageSource) serviceMessageSource;
            for (ServiceMessageSource subServiceMessageSource : compositeServiceMessageSource.getServiceMessageSources()) {
                initServiceMessageSource(subServiceMessageSource, serviceMessageSources);
            }
        } else {
            serviceMessageSources.add(serviceMessageSource);
        }
    }

    public static void destroyServiceMessageSource() {
        serviceMessageSource = null;
        logger.debug("serviceMessageSource is destroyed");
    }

    public static void setServiceMessageSource(ServiceMessageSource serviceMessageSource) {
        I18nUtils.serviceMessageSource = serviceMessageSource;
        logger.debug("I18nUtils.serviceMessageSource is initialized : {}", serviceMessageSource);
    }

}
