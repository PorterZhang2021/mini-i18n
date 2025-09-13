package io.mini.i18n.spring.constants;

import io.mini.i18n.ServiceMessageSource;

public interface I18nConstants {

    String PROPERTY_NAME_PREFIX = "microsphere.i18n.";

    /**
     * Enabled Configuration Name
     */
    String ENABLED_PROPERTY_NAME = PROPERTY_NAME_PREFIX + "enabled";

    /**
     * Enabled By Default
     */
    boolean DEFAULT_ENABLED = true;

    /**
     * The property name of the {@link ServiceMessageSource#getSource() sources} of {@link ServiceMessageSource}
     *
     * @see ServiceMessageSource#getSource()
     */
    String SOURCES_PROPERTY_NAME = PROPERTY_NAME_PREFIX + "sources";

    /**
     * The Primary {@link ServiceMessageSource} Bean Bean
     */
    String SERVICE_MESSAGE_SOURCE_BEAN_NAME = "serviceMessageSource";
}
