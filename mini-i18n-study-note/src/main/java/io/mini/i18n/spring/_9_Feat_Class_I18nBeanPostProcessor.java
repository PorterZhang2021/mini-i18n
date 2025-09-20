package io.mini.i18n.spring;

/**
 * <p>
 * {@link io.mini.i18n.spring.beans.factory.config.I18nBeanPostProcessor}主要实现了{@link org.springframework.beans.factory.config.BeanPostProcessor}
 * 接口，用于在Spring Bean初始化前后进行自定义处理。
 * </p>
 * <p>
 * 该类的主要功能是检查Spring容器中的Bean，如果发现某个Bean是{@link org.springframework.validation.beanvalidation.LocalValidatorFactoryBean}类型，
 * 则将其与国际化消息源{@link io.mini.i18n.spring.context.MessageSourceAdapter}关联起来。
 * </p>
 * <p>
 * {@link org.springframework.validation.beanvalidation.LocalValidatorFactoryBean}是Spring提供的一个用于集成Bean Validation（JSR-303/JSR-380）的工厂Bean。
 * 通过这个类，可以方便地在Spring应用中使用Bean Validation进行数据校验。这里主要是会将Spring的Message的消息适配器设定成{@link io.mini.i18n.spring.context.MessageSourceAdapter}, ，使校验消息可使用 Spring 的国际化资源。
 * </p>
 */
public class _9_Feat_Class_I18nBeanPostProcessor {
}
