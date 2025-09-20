package io.mini.i18n.spring;

import io.mini.i18n.spring.context.ResourceServiceMessageSourceChangedEvent;

/**
 * <p>
 * {@link io.mini.i18n.spring.context.ResourceServiceMessageSourceChangedEvent} 这个类是一个事件类，
 * 用于表示资源服务消息源发生变化的事件。它继承自Spring框架中的{@link org.springframework.context.event.ApplicationContextEvent}类。
 * </p>
 * <p>
 * {@link org.springframework.context.event.ApplicationContextEvent}类，这个类是Spring框架中的一个事件类，
 * 用于表示与应用程序上下文相关的事件。它继承自更通用的{@link org.springframework.context.ApplicationEvent}类。
 * 通过这个类，我们可以创建与应用程序上下文相关的自定义事件， 并且这些事件可以在应用程序上下文中发布和监听。
 * </p>
 * <p>
 * {@link io.mini.i18n.spring.context.ResourceServiceMessageSourceChangedEvent}类有一个构造方法，
 * 接受两个参数：一个是应用程序上下文（source），另一个是一个可迭代的字符串集合（changedResources），
 * 用于表示发生变化的资源。其主要是在{@link io.mini.i18n.spring.beans.factory.ServiceMessageSourceFactoryBean#onApplicationEvent(ResourceServiceMessageSourceChangedEvent)}
 * 方法中被使用，当监听到资源服务消息源发生变化的事件时，会调用该方法进行处理。
 * </p>
 */
public class _12_Feat_Class_ResourceServiceMessageSourceChangedEvent {
}
