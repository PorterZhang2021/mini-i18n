package io.mini.i18n;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public interface ResourceServiceMessageSource extends ServiceMessageSource {

    void initializeResource(String resource);

    default void initializeResources(Iterable<String> resources) {
        resources.forEach(this::initializeResource);
    }

    Set<String> getInitializeResources();

    default Charset getEncoding() {
        return StandardCharsets.UTF_8;
    }
}
