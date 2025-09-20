package io.mini.i18n.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.Locale;

/**
 * <p>
 * {@link io.mini.i18n.spring.context.MessageSourceAdapter}需要实现的接口有两个：
 *     <ul>
 *         <li>
 *             {@link org.springframework.context.MessageSource}: 这个接口是Spring的国际化消息解析接口，要作用是根据消息码（code）、
 *             参数（args）和指定的 Locale，返回对应的本地化消息文本。常用于多语言支持，比如根据用户的语言环境显示不同的提示信息。
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.SmartInitializingSingleton}: 这个接口是Spring提供的一个扩展点，允许在所有单例 Bean 初始化完成后执行一些自定义逻辑。
 *             它的主要方法是 afterSingletonsInstantiated()， 当 Spring 容器中的所有单例 Bean 都被创建并初始化后，这个方法会被调用。
 *             这个接口的作用是让开发者可以在 Spring 容器启动完成后执行一些额外的初始化操作，比如注册监听器、预加载数据等。
 *             <p>
 *                 作为比较：{@link InitializingBean#afterPropertiesSet()}的调用时机是在单个 Bean 的属性设置完成后，而不是在所有单例 Bean 初始化完成后。
 *                 也就是说，{@link InitializingBean#afterPropertiesSet()} 是针对单个 Bean 的初始化钩子，
 *                 而 SmartInitializingSingleton 则是在整个 Spring 容器的单例 Bean 都初始化完成之后才调用。
 *             </p>
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     在具体实现的时候，我们可以发现，我们是将我们之前实现的{@link io.mini.i18n.ServiceMessageSource}以及
 *     对应的{@link org.springframework.context.MessageSource}拿到，最后对{@link org.springframework.context.MessageSource}
 *     接口的getMessage方法进行实现, 其主要是先从{@link io.mini.i18n.ServiceMessageSource}中获取消息，
 *     如果获取不到，再从默认的MessageSource中获取消息，
 *     也就是{@link io.mini.i18n.spring.context.MessageSourceAdapter#getDefaultMessage(String, Object[], String, Locale)},
 *     这样就实现了对Spring原生MessageSource的一个适配，从而实现了自定义的国际化消息解析逻辑。
 * </p>
 * <p>
 *     {@link SmartInitializingSingleton#afterSingletonsInstantiated()}也就是
 *     为了获取在{@link io.mini.i18n.spring.context.MessageSourceAdapter#messageSourceProvider}中默认的
 *     {@link io.mini.i18n.spring.context.MessageSourceAdapter#defaultMessageSource}, 从而在{@link io.mini.i18n.ServiceMessageSource}
 *     没有办法工作时，使用Spring默认的进行工作。
 * </p>
 */
public class _7_Feat_Class_MessageSourceAdapter {
}
