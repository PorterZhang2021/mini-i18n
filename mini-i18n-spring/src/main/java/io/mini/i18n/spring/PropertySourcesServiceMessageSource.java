package io.mini.i18n.spring;

import io.mini.i18n.PropertiesResourceServiceMessageSource;
import io.mini.i18n.ReloadableResourceServiceMessageSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class PropertySourcesServiceMessageSource extends PropertiesResourceServiceMessageSource implements ReloadableResourceServiceMessageSource, EnvironmentAware {
    private Environment environment;

    public PropertySourcesServiceMessageSource(String source) {
        super(source);
    }

    @Override
    protected String getResource(String resourceName) {
        return getSource() + "." + resourceName;
    }

    @Override
    protected Locale getInternalLocale() {
        return LocaleContextHolder.getLocale();
    }


    @Override
    protected List<Reader> loadAllPropertiesResources(String resource) throws IOException {
        String propertiesContent = getPropertiesContent(resource);
        return StringUtils.hasText(propertiesContent) ? Arrays.asList(new StringReader(propertiesContent)) : Collections.emptyList();
    }

    protected String getPropertiesContent(String resource) {
        String propertyName = getPropertyName(resource);
        return environment.getProperty(propertyName);
    }

    public String getPropertyName(Locale locale) {
        String resource = getResource(locale);
        return getPropertyName(resource);
    }

    public String getPropertyName(String resource) {
        return resource;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
