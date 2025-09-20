package io.mini.i18n.spring;

import io.mini.i18n.spring.annotation.EnableI18n;

/**
 * <p>
 * {@link io.mini.i18n.spring.annotation.EnableI18n}这个注解主要用来开启构建的I18n国际化操作功能，
 * 通过{@link org.springframework.context.annotation.Import}来注册{@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister}
 * 这个类。
 * </p>
 * <p>
 * {@link EnableI18n#sources()}用来声明要注册哪些消息源，默认是{@link io.mini.i18n.ServiceMessageSource#COMMON_SOURCE}, 其同时
 * 会合并来自Spring属性"mini.i18n.sources"的值。
 * </p>
 * <p>
 * {@link EnableI18n#exposeMessageSource()}默认为true， 会将合并后的消息源注册为Spring容器中的
 * {@link org.springframework.context.MessageSource}类型的Bean, 其Bean名称为"messageSource"，
 * 此时等于接管了Spring的国际化消息处理功能，如果为false，则依然会注册合并后的消息源，但是不会注册为
 * "messageSource"这个名称的Bean，接管Spring的国际化消息处理功能。
 * </p>
 */
public class _11_Feat_Annotation_EnableI18n {
}
