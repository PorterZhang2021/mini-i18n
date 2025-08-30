package io.mini.i18n;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.microsphere.collection.ListUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

public class CompositeServiceMessageSource implements ReloadableResourceServiceMessageSource {

    private static final Logger logger = LoggerFactory.getLogger(CompositeServiceMessageSource.class);

    private List<? extends ServiceMessageSource> serviceMessageSources;

    public CompositeServiceMessageSource() {
        this.serviceMessageSources = Collections.emptyList();
    }

    public CompositeServiceMessageSource(List<? extends ServiceMessageSource> serviceMessageSources) {
        setServiceMessageSources(serviceMessageSources);
    }


    public void setServiceMessageSources(List<? extends ServiceMessageSource> serviceMessageSources) {
        List<? extends ServiceMessageSource> oldServiceMessageSources = this.serviceMessageSources;
        List<ServiceMessageSource> newServiceMessageSources = new ArrayList<>(serviceMessageSources);
        Collections.sort(newServiceMessageSources);
        if (oldServiceMessageSources != null) {
            oldServiceMessageSources.clear();
        }
        this.serviceMessageSources = newServiceMessageSources;
        logger.debug("Source '{}' sets ServiceMessageSource list, sorted : {}", serviceMessageSources, newServiceMessageSources);
    }

    @Override
    public void initializeResource(String resource) {
        initializeResources(Collections.singletonList(resource));
    }

    @Override
    public void initializeResources(Iterable<String> resources) {
        iterate(ReloadableResourceServiceMessageSource.class, reloadableResourceServiceMessageSource -> {
            reloadableResourceServiceMessageSource.initializeResources(resources);
        });
    }

    @Override
    public Set<String> getInitializeResources() {
        Set<String> resources = new LinkedHashSet<>();
        iterate(ResourceServiceMessageSource.class, resourceServiceMessageSource -> {
            resources.addAll(resourceServiceMessageSource.getInitializeResources());
        });
        return Collections.unmodifiableSet(resources);
    }

    @Override
    public void init() {
        ListUtils.forEach(this.serviceMessageSources, ServiceMessageSource::init);
    }

    @Override
    public void destroy() {
        List<? extends ServiceMessageSource> serviceMessageSources = this.serviceMessageSources;
        ListUtils.forEach(this.serviceMessageSources, ServiceMessageSource::destroy);
        serviceMessageSources.clear();
    }

    @Nullable
    @Override
    public String getMessage(String code, Locale locale, Object... args) {
        String message = null;
        for (ServiceMessageSource serviceMessageSource : serviceMessageSources) {
            message = serviceMessageSource.getMessage(code, locale, args);
            if (message != null) {
                break;
            }
        }
        return message;
    }


    @Nonnull
    @Override
    public Locale getLocale() {
        ServiceMessageSource serviceMessageSource = getFirstServiceMessageSource();
        return serviceMessageSource == null
                ? getDefaultLocale()
                : serviceMessageSource.getLocale();
    }

    @Nonnull
    @Override
    public Locale getDefaultLocale() {
        ServiceMessageSource serviceMessageSource = getFirstServiceMessageSource();
        return serviceMessageSource == null
                ? ReloadableResourceServiceMessageSource.super.getDefaultLocale()
                : serviceMessageSource.getLocale();
    }

    @Nonnull
    @Override
    public List<Locale> getSupportedLocales() {
        LinkedList<Locale> supportedLocales = new LinkedList<>();
        iterate(serviceMessageSource -> {
            for (Locale locale : serviceMessageSource.getSupportedLocales()) {
                if (!supportedLocales.contains(locale)) {
                    supportedLocales.add(locale);
                }
            }
        });

        return supportedLocales.isEmpty()
                ? getDefaultSupportedLocales()
                : Collections.unmodifiableList(supportedLocales);
    }

    public List<Locale> getDefaultSupportedLocales() {
        return ReloadableResourceServiceMessageSource.super.getSupportedLocales();
    }

    @Override
    public String getSource() {
        return ReloadableResourceServiceMessageSource.super.getSource();
    }

    @Override
    public void reload(Iterable<String> changedResources) {
        iterate(ReloadableResourceServiceMessageSource.class, reloadableResourceServiceMessageSource -> {
            if (reloadableResourceServiceMessageSource.canReload(changedResources)) {
                reloadableResourceServiceMessageSource.reload(changedResources);
            }
        });
    }

    @Override
    public boolean canReload(Iterable<String> changedResources) {
        return true;
    }


    private ServiceMessageSource getFirstServiceMessageSource() {
        return this.serviceMessageSources.isEmpty() ? null : this.serviceMessageSources.get(0);
    }

    @Override
    public String toString() {
        return "CompositeServiceMessageSource{" +
                "serviceMessageSources=" + serviceMessageSources +
                '}';
    }

    private <T> void iterate(Class<T> serviceMessageSourceType, Consumer<T> consumer) {
        this.serviceMessageSources
                .stream()
                .filter(serviceMessageSourceType::isInstance)
                .map(serviceMessageSourceType::cast)
                .forEach(consumer);
    }

    private <T> void iterate(Consumer<ServiceMessageSource> consumer) {
        this.serviceMessageSources.forEach(consumer);
    }
}
