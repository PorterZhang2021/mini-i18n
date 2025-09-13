package io.mini.i18n.spring;

/**
 * <p>
 * 接下来我们实现{@link DelegatingServiceMessageSource},其主要有如下几个接口
 *     <ul>
 *         <li>
 *             {@link io.mini.i18n.ReloadableResourceServiceMessageSource}: 这个接口主要是用来实现重新加载的功能，
 *             具体详细的内容可以参考{@link io.mini.i18n.core._12_Feat_Interface_ReloadableResourceServiceMessageSource}
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.InitializingBean}: 这个接口主要是Spring的初始化Bean的接口，用来进行
 *             Bean的初始化，相关内容在后需进行补充学习。
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.DisposableBean}: 这个接口主要是Spring的销毁Bean的接口，用来进行
 *             Bean的销毁，相关内容在后需进行补充学习。
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     这个类的主要作用是将Spring容器中所有的{@link io.mini.i18n.ServiceMessageSource}类型的Bean进行整合，
 *     然后将其委托给一个{@link io.mini.i18n.CompositeServiceMessageSource}类型的实例来进行处理，
 *     这样就实现了对多个ServiceMessageSource的支持，屏蔽多消息源实例差异，便于对外暴露一个统一的国际化访问点。
 * </p>
 * <p>
 *     这个部分主要就是通过{@link DelegatingServiceMessageSource#init()}方法来实现的，
 *     通过{@link DelegatingServiceMessageSource#serviceMessageSourcesProvider}
 *     来将不同的ServiceMessageSource实例获取到，然后将其设置到CompositeServiceMessageSource中。
 *     这样后续我们在使用的时候就可以通过DelegatingServiceMessageSource来进行国际化信息的获取，而不需要关心具体
 *     使用的是哪个ServiceMessageSource实例, 这里在加载的时候，会进行ServiceMessageSource实例的排序，排序规则
 *     是基于{@link org.springframework.core.Ordered}接口的，如果没有实现该接口，则默认排序值为
 *     {@link org.springframework.core.Ordered#LOWEST_PRECEDENCE}
 * </p>
 */
public class _4_Feat_Class_DelegatingServiceMessageSource {
    // todo: 学习InitializingBean以及DisposableBean接口的内容，并补充对应的学习笔记
}
