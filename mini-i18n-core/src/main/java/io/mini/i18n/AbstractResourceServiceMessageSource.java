package io.mini.i18n;

import io.microsphere.collection.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static io.microsphere.text.FormatUtils.format;

public abstract class AbstractResourceServiceMessageSource extends AbstractServiceMessageSource implements ResourceServiceMessageSource {

    public static final String DEFAULT_RESOURCE_NAME_PREFIX = "i18n_messages_";
    private static final String DEFAULT_RESOURCE_NAME_SUFFIX = ".properties";

    private volatile Map<String, Map<String, String>> localizedResourceMessages = Collections.emptyMap();

    public AbstractResourceServiceMessageSource(String source) {
        super(source);
    }

    @Override
    public void init() {
        Objects.requireNonNull(this.source, "'source' must not be null");
        initialize();
    }

    @Override
    public void destroy() {
        clearAllMessages();
    }

    protected final void initialize() {
        List<Locale> supportedLocales = super.getSupportedLocales();
        assertSupportedLocales(supportedLocales);
        Map<String, Map<String, String>> localizedResourceMessages = new HashMap<>(supportedLocales.size());
        for (Locale resolveLocale : supportedLocales) {
            String resource = getResource(resolveLocale);
            initializeResource(resource, localizedResourceMessages);
        }
        this.localizedResourceMessages = localizedResourceMessages;
        logger.debug("Source '{}' Initialization is completed , localizedResourceMessages : {}", source, localizedResourceMessages);
    }

    private void initializeResource(Iterable<String> resources, Map<String, Map<String, String>> localizedResourceMessages) {
        for (String resource : resources) {
            initializeResource(resource, localizedResourceMessages);
        }
    }

    private void initializeResource(String resource, Map<String, Map<String, String>> localizedResourceMessages) {
        Map<String, String> messages = loadMessages(resource);
        logger.debug("Source '{}' loads the resource['{}'] messages : {}", source, resource, messages);

        if (messages == null) {
            return;
        }

        validateMessages(messages, resource);
        localizedResourceMessages.put(resource, messages);
    }

    private void validateMessages(Map<String, String> messages, String resourceName) {
        messages.forEach((code, message) -> validateMessageCode(code, resourceName));
    }

    protected void validateMessageCode(String code, String resourceName) {
        validateMessageCodePrefix(code, resourceName);
    }

    private void validateMessageCodePrefix(String code, String resourceName) {
        if (!code.startsWith(codePrefix)) {
            throw new IllegalStateException(format("Source '{}' Message Resource[name : '{}'] code '{}' must start with '{}'",
                    source, resourceName, code, codePrefix));
        }
    }

    private void assertSupportedLocales(List<Locale> supportedLocales) {
        if (CollectionUtils.isEmpty(supportedLocales)) {
            throw new IllegalStateException(format("{}.getSupportedLocales() Methods cannot return an empty list of locales!", this.getClass()));
        }
    }

    public String getResource(Locale locale) {
        String resourceName = buildResourceName(locale);
        return getResource(resourceName);
    }

    protected String buildResourceName(Locale locale) {
        return DEFAULT_RESOURCE_NAME_PREFIX + locale + DEFAULT_RESOURCE_NAME_SUFFIX;
    }

    protected abstract String getResource(String resourceName);

    @Nullable
    protected abstract Map<String, String> loadMessages(String resource);

    @Override
    protected String getInternalMessage(String code, String resolvedCode, Locale locale, Locale resolvedLocale, Object... args) {
        String message = null;
        Map<String, String> messages = getMessages(resolvedLocale);
        if (messages != null) {
            String messagePattern = messages.get(resolvedCode);
            if (messagePattern != null) {
                message = resolveMessage(messagePattern, args);
                logMessage(code, resolvedCode, locale, resolvedLocale, args, messagePattern, message);
            }
        }
        return message;
    }

    @Nullable
    public final Map<String, String> getMessages(Locale locale) {
        String resource = getResource(locale);
        return localizedResourceMessages.get(resource);
    }

    @Override
    public void initializeResource(String resource) {
        initializeResources(Collections.singleton(resource));
    }

    @Override
    public void initializeResources(Iterable<String> resources) {
        synchronized (this) {
            HashMap<String, Map<String, String>> localizedResourceMessages = new HashMap<>(this.localizedResourceMessages);
            initializeResource(resources, localizedResourceMessages);
            this.localizedResourceMessages = localizedResourceMessages;
        }
    }

    protected void logMessage(String code, String resolvedCode, Locale locale, Locale resolvedLocale, Object[] args,
                              String messagePattern, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("Source '{}' gets Message[code : '{}' , resolvedCode : '{}' , locale : '{}' , resolvedLocale : '{}', args : '{}' , pattern : '{}'] : '{}'",
                    source, code, resolvedCode, locale, resolvedLocale, ArrayUtils.toString(args), messagePattern, message);
        }
    }

    @Override
    public Set<String> getInitializeResources() {
        return localizedResourceMessages.keySet();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName())
                .append("{source='").append(source).append('\'')
                .append(", defaultLocale=").append(getDefaultLocale())
                .append(", supportedLocales=").append(getSupportedLocales())
                .append(", localizedResourceMessages=").append(localizedResourceMessages)
                .append('}');
        return sb.toString();
    }

    protected final void clearAllMessages() {
        this.localizedResourceMessages.clear();
        this.localizedResourceMessages = null;
    }
}
