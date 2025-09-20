package io.mini.i18n.spring.context;


import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class ResourceServiceMessageSourceChangedEvent extends ApplicationContextEvent {

    private final Iterable<String> changedResources;

    public ResourceServiceMessageSourceChangedEvent(ApplicationContext source, Iterable<String> changedResources) {
        super(source);
        this.changedResources = changedResources;
    }

    public Iterable<String> getChangedResources() {
        return changedResources;
    }
}
