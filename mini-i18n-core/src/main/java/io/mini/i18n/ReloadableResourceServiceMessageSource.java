package io.mini.i18n;

import com.sun.tools.javac.util.List;

import java.util.Set;

public interface ReloadableResourceServiceMessageSource extends  ResourceServiceMessageSource {

    default void reload(String changedResource) {
        reload(List.of(changedResource));
    }

    default void reload(Iterable<String> changedResources) {
        initializeResources(changedResources);
    }

    default boolean canReload(String changedResource) {
       return canReload(List.of(changedResource));
    }

    default boolean canReload(Iterable<String> changedResources) {
        Set<String> resources = getInitializeResources();
        boolean reloadable = false;
        for (String changedResource : changedResources) {
            if (reloadable == resources.contains(changedResource)) {
                break;
            }
        }
        return reloadable;
    }


}
