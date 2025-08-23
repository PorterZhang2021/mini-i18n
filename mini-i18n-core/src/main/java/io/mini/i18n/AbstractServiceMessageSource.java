package io.mini.i18n;

import io.microsphere.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class AbstractServiceMessageSource implements ServiceMessageSource {

    protected static final String SOURCE_SEPARATOR = ".";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final String source;

    protected final String codePrefix;

    private List<Locale> supportedLocales;

    private Locale defaultLocale;

    public AbstractServiceMessageSource(String source) {
        Objects.requireNonNull(source, "'source' argument must not be null");
        this.source = source;
        this.codePrefix = source + SOURCE_SEPARATOR;
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
        String message = null;
        if (code != null) {
            String resolvedCode = resolveMessageCode(code);
            if (resolvedCode != null) {
                Locale resolvedLocale = resolveLocale(locale);
                message = getInternalMessage(code, resolvedCode, locale, resolvedLocale, args);
            }
        }
        return message;
    }

    protected abstract String getInternalMessage(String code, String resolvedCode, Locale locale, Locale resolvedLocale, Object... args);

    protected Locale resolveLocale(Locale locale) {
        return locale;
    }

    protected String resolveMessageCode(String code) {
        return code;
    }

    @Nonnull
    @Override
    public Locale getLocale() {
        Locale locale = getInternalLocale();
        return locale == null ? getDefaultLocale() : locale;
    }

    @Nullable
    protected Locale getInternalLocale() {
        return null;
    }

    @Nonnull
    @Override
    public Locale getDefaultLocale() {
        if (defaultLocale != null) {
            return defaultLocale;
        }
        return ServiceMessageSource.super.getDefaultLocale();
    }

    @Nonnull
    @Override
    public List<Locale> getSupportedLocales() {
        if (supportedLocales != null) {
            return supportedLocales;
        }
        return ServiceMessageSource.super.getSupportedLocales();
    }

    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
        logger.debug("Source '{}' sets the default Locale : '{}'", source, defaultLocale);
    }


    public void setSupportedLocales(List<Locale> supportedLocales) {
        this.supportedLocales = resolveLocales(supportedLocales);
        logger.debug("Source '{}' sets the supported Locales : {}", source, supportedLocales);
    }

    protected static List<Locale> resolveLocales(List<Locale> supportedLocales) {
        List<Locale> resolvedLocales = new ArrayList<>();
        for (Locale supportedLocale : supportedLocales) {
            addLocale(resolvedLocales, supportedLocale);
            for (Locale deriveLocale : resolveDeriveLocales(supportedLocale)) {
                addLocale(resolvedLocales, deriveLocale);
            }
        }
        return Collections.unmodifiableList(resolvedLocales);
    }

    protected static void addLocale(List<Locale> locales, Locale locale) {
        if (!locales.contains(locale)) {
            locales.add(locale);
        }
    }

    protected static List<Locale> resolveDeriveLocales(Locale locale) {
        String language = locale.getLanguage();
        String region = locale.getCountry();
        String variant = locale.getVariant();

        /// 这里的StringUtils来自于microsphere-util，做了一层封装
        /// 这里的启示就是对于第三方的工具类，可以做一层封装，方便后续的维护
        boolean hasRegion = StringUtils.isNotBlank(region);
        boolean hasVariant = StringUtils.isNotBlank(variant);

        if (!hasRegion && !hasVariant) {
            return Collections.emptyList();
        }

        LinkedList<Locale> derivedLocales = new LinkedList<>();

        /// 这里可以发现先对 variant 进行处理，然后再对 region 进行处理
        ///  因为本身就是要实现能够对所有的取到，所以这里用这个顺序，能够更加
        ///  方便的剔除不需要的变量。
        if (hasVariant) {
            derivedLocales.add(new Locale(language, region));
        }

        if (hasRegion) {
            derivedLocales.add(new Locale(language));
        }

        return derivedLocales;
    }
}
