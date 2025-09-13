package io.mini.i18n.spring.context;

import org.springframework.context.ApplicationEvent;

public class ResourceServiceMessageSourceChangedEvent extends ApplicationEvent {
    public ResourceServiceMessageSourceChangedEvent(Object source) {
        super(source);
    }

    public Iterable<String> getChangedResources() {
        return null;
    }
}
