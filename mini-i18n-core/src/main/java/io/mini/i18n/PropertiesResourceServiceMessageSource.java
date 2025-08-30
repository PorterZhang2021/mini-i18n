package io.mini.i18n;

import io.microsphere.collection.CollectionUtils;
import io.microsphere.collection.MapUtils;
import io.microsphere.text.FormatUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.*;


public abstract class PropertiesResourceServiceMessageSource extends AbstractResourceServiceMessageSource {


    public PropertiesResourceServiceMessageSource(String source) {
        super(source);
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Map<String, String> loadMessages(String resource) {
        Map<String, String> messages = null;
        try {
            Properties properties = loadAllProperties(resource);
            if (!MapUtils.isEmpty(properties)) {
                messages = new HashMap<>(properties.size());
                messages.putAll((Map) properties);
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    FormatUtils.format("Source '{}' Messages Properties Resource[name : {}] loading is failed"
                            , source
                            , resource)
                    , e);
        }

        return messages == null ? Collections.emptyMap() : Collections.unmodifiableMap(messages);
    }


    public Properties loadAllProperties(String resource) throws IOException {
        List<Reader> propertiesResources = loadAllPropertiesResources(resource);
        logger.debug("Source '{}' loads {} Properties Resources['{}']", source, propertiesResources.size(), resource);
        if (CollectionUtils.isEmpty(propertiesResources)) {
            return null;
        }
        Properties properties = new Properties();
        for (Reader propertiesResource : propertiesResources) {
            try (Reader reader = propertiesResource) {
                properties.load(reader);
            }
        }
        logger.debug("Source '{}' loads all Properties Resources[name :{}] : {}", source, resource, properties);
        return properties;
    }

    @Override
    protected abstract String getResource(String resourceName);

    protected abstract List<Reader> loadAllPropertiesResources(String resource) throws IOException;
}
