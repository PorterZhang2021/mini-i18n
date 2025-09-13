package io.mini.i18n.spring;

import io.mini.i18n.spring.context.ResourceServiceMessageSourceChangedEvent;

/**
 * <p>
 * {@link io.mini.i18n.spring.beans.factory.ServiceMessageSourceFactoryBean}这个类主要继承
 * {@link io.mini.i18n.CompositeServiceMessageSource}, 其主要是一个聚合消息源实现，详细内容可以参考
 * {@link io.mini.i18n.core._13_Feat_Class_CompositeServiceMessageSource}
 * </p>
 * <p>
 * 其实现的接口比较多，这里需要实现的接口如下:
 *     <ul>
 *         <li>
 *             {@link io.mini.i18n.ReloadableResourceServiceMessageSource}: 主要是用来重新进行加载的
 *             具体参考{@link io.mini.i18n.core._12_Feat_Interface_ReloadableResourceServiceMessageSource}
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.InitializingBean}: 初始化bean接口，用来进行Bean的初始化
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.DisposableBean}: 销毁bean接口，用来进行Bean的销毁
 *         </li>
 *         <li>
 *             {@link org.springframework.context.EnvironmentAware}: 读取配置属性（默认语言 microsphere.i18n.default-locale、支持语言集合等）
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.BeanClassLoaderAware}: 获取类加载器,获取当前 Bean 使用的 ClassLoader
 *             ，用于通过 SpringFactoriesLoader 按 SPI 方式加载所有 AbstractServiceMessageSource 实现类名并反射实例化。
 *         </li>
 *         <li>
 *             {@link org.springframework.context.ApplicationContextAware}: 拿到 ApplicationContext，
 *             再调用内部每个子消息源的 Aware 接口（invokeAwareInterfaces），使其获得上下文能力（发布事件、取其他 Bean 等）。
 *         </li>
 *         <li>
 *             {@link org.springframework.beans.factory.FactoryBean}: 让该 Bean 成为一个“工厂”角色；
 *             对外暴露的实际 Bean 类型是 ReloadableResourceServiceMessageSource。
 *             此类本身实现了该接口，getObject() 返回自身，实现“自引用式”工厂包装（屏蔽内部复杂初始化细节）。
 *         </li>
 *         <li>
 *             {@link org.springframework.context.ApplicationListener}: 监听资源变更事件；收到后遍历所有可重载子消息源，
 *             判断 canReload 并执行 reload，实现增量热更新。
 *         </li>
 *         <li>
 *             {@link  org.springframework.core.Ordered}: 该接口主要是用来进行排序的, 默认是最低优先级
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     方法部分实现主要需要关注的是两个方法，其他方法都是默认需要的实现
 *     <ul>
 *         <li>
 *             {@link io.mini.i18n.spring.beans.factory.ServiceMessageSourceFactoryBean#onApplicationEvent(ResourceServiceMessageSourceChangedEvent)}
 *             : 监听资源变更事件；收到后遍历所有可重载子消息源，判断 canReload 并执行 reload，实现增量热更新。
 *         </li>
 *         <li>
 *             {@link io.mini.i18n.spring.beans.factory.ServiceMessageSourceFactoryBean#afterPropertiesSet()}
 *             : 该方法主要是用来进行初始化的, 主要是调用了 init() 方法，init() 方法主要是用来进行初始化子消息源的
 *             主要是通过 SPI 方式加载所有 AbstractServiceMessageSource 实现类名并反射实例化, 其最终会进行排序。
 *             具体流程如下:
 *             <p>
 *                 1. 通过 SpringFactoriesLoader 按 SPI 读取 META-INF/spring.factories 里声明的 AbstractServiceMessageSource 实现类全限定名。
 *             </p>
 *             <p>
 *                  2. 解析默认 Locale 与支持的 Locale 列表（从 Environment 配置属性里获取，缺省走父类默认值）
 *             </p>
 *             <p>
 *                 3. 为每个实现类：
 *                      反射获取带 String source 参数的构造器并实例化。
 *                      调用 invokeAwareInterfaces 注入 ApplicationContext、Environment 等（实现其 Aware 接口的自动回调）。
 *                      设置统一的默认 Locale、支持 Locale 集合。
 *                      调用其 init() 做自身资源加载/缓存预热等初始化。
 *              </p>
 *              <p>
 *                  4.对实例列表排序（实现了 Ordered/Comparable），保证后续聚合查询的调用顺序（优先级）。
 *              </p>
 *             <p>
 *                  5.返回排序后的不可排序列表，随后被 setServiceMessageSources(...) 注入到组合消息源中，实现链式解析。
 *             </p>
 *         </li>
 *     </ul>
 * </p>
 */
public class _6_Feat_Class_ServiceMessageSourceFactoryBean {
}
