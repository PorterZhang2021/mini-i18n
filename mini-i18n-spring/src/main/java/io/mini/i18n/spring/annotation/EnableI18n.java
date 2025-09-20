package io.mini.i18n.spring.annotation;

import io.mini.i18n.ServiceMessageSource;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Documented
@Inherited
@Import(I18nImportBeanDefinitionRegister.class)
public @interface EnableI18n {

    String[] sources() default {ServiceMessageSource.COMMON_SOURCE};

    boolean exposeMessageSource() default true;
}
