package io.mini.i18n;

import java.util.Set;

public interface ReloadableResourceServiceMessageSource extends  ResourceServiceMessageSource {

    default void reload(String changedResource) {
        initializeResource(changedResource);
    }

    default void reload(Iterable<String> changedResources) {
        initializeResources(changedResources);
    }

    default boolean canReload(String changedResource) {
        Set<String> resources = getInitializeResources();
        return resources.contains(changedResource);
    }

    default boolean canReload(Iterable<String> changedResources) {
        Set<String> resources = getInitializeResources();
        boolean reloadable = false;
        for (String changedResource : changedResources) {
            if (resources.contains(changedResource)) {
                reloadable = true;
                break;
            }
        }
        return reloadable;
    }


}
