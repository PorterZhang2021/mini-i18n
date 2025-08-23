package io.mini.i18n;

import io.microsphere.lang.Prioritized;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ServiceMessageSource extends Prioritized {
    String COMMON_SOURCE = "common";

    void init();

    void destroy();

    @Nullable
    String getMessage(String code, Locale locale, Object... args);

    default String getMessage(String code, Object... args) {
        return getMessage(code, getLocale(), args);
    }

    @Nonnull
    Locale getLocale();

    @Nonnull
    default Locale getDefaultLocale() {
        return Locale.getDefault();
    }

    @Nonnull
    default List<Locale> getSupportedLocales() {
        return Arrays.asList(getDefaultLocale(), Locale.ENGLISH);
    }

    default String getSource() {
        return COMMON_SOURCE;
    }
}
