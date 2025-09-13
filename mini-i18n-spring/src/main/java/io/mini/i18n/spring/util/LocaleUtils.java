package io.mini.i18n.spring.util;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.Locale;

public abstract class LocaleUtils {
    @Nullable
    public static Locale getLocaleFromLocaleContext() {
        LocaleContext localeContext = LocaleContextHolder.getLocaleContext();
        return localeContext == null ? null : localeContext.getLocale();
    }
}
