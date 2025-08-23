package io.mini.i18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class EmptyServiceMessageSource implements ServiceMessageSource {

    public static final EmptyServiceMessageSource INSTANCE = new EmptyServiceMessageSource();

    private EmptyServiceMessageSource() {
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Nullable
    @Override
    public String getMessage(String code, Locale locale, Object... args) {
        return null;
    }

    @Nonnull
    @Override
    public Locale getLocale() {
        return getDefaultLocale();
    }

    @Override
    public String getSource() {
        return "Empty";
    }
}
