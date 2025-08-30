package io.mini.i18n;

import io.microsphere.text.FormatUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class DefaultServiceMessageSource extends PropertiesResourceServiceMessageSource{

    protected static final String RESOURCE_LOCATION_PATTERN = "META-INF/i18n/{}/{}";

    public DefaultServiceMessageSource(String source) {
        super(source);
    }

    @Override
    protected String getResource(String resourceName) {
        return FormatUtils.format(RESOURCE_LOCATION_PATTERN, source, resourceName);
    }

    @Override
    protected List<Reader> loadAllPropertiesResources(String resource) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Enumeration<URL> resources = classLoader.getResources(resource);
        LinkedList<Reader> propertiesResources = new LinkedList<>();
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            propertiesResources.add(new InputStreamReader(url.openStream(), getEncoding()));
        }
        return propertiesResources;
    }
}
