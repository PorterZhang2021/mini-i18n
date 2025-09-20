package io.mini.i18n.spring;

import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * <p>
 * {@link io.mini.i18n.spring.beans.factory.support.ServiceMessageSourceBeanLifecyclePostProcessor}这个类实现了
 * {@link org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor}接口，这个接口是Spring框架中的一个扩展点，
 * 用于在Bean定义合并后对其进行处理。其继承了{@link org.springframework.beans.factory.config.BeanPostProcessor}, 主要是将
 * 容器中的父/子定义合并成最终的RootBeanDefinition 后，会调用此方法
 * {@link org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition(org.springframework.beans.factory.support.RootBeanDefinition, Class, String)}
 * </p>
 * <p>
 * 其和{@link org.springframework.beans.factory.config.BeanPostProcessor}的区别在于: {@link org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor}
 * 处理的是“类/定义元数据层面(创建实例之前)", 而{@link org.springframework.beans.factory.config.BeanPostProcessor}是在实例化
 * 并初始化前后对具体的对象进行处理。
 * </p>
 * <p>
 * {@link io.mini.i18n.spring.beans.factory.support.ServiceMessageSourceBeanLifecyclePostProcessor#postProcessMergedBeanDefinition(RootBeanDefinition, Class, String)}
 * 这个方法会在{@link io.mini.i18n.ServiceMessageSource}类型的Bean定义合并后被调用,也就是仅对 beanType是{@link io.mini.i18n.ServiceMessageSource}
 * 或者继承自{@link io.mini.i18n.ServiceMessageSource}的 Bean 进行处理。
 * 其主要做了两件事:
 *     <ul>
 *         <li>
 *             如果Bean类型实现了{@link org.springframework.beans.factory.InitializingBean}接口, 则不做任何处理,
 *             否则会检查Bean定义中是否已经声明了初始化方法, 如果没有声明,
 *             则会将初始化方法设置为"init", 这样在Bean实例化后, Spring容器会调用其init()方法进行初始化操作。
 *         </li>
 *         <li>
 *             如果Bean类型实现了{@link org.springframework.beans.factory.DisposableBean}接口, 则不做任何处理,
 *             否则会检查Bean定义中是否已经声明了销毁方法, 如果没有声明, 则会将销毁方法设置为"destroy", 这样在容器销毁Bean时,
 *             会调用其destroy()方法进行清理操作。
 *        </li>
 *      </ul>
 * </p>
 */
public class _10_Feat_Class_ServiceMessageSourceBeanLifecyclePostProcessor {
}
