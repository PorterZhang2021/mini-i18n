package io.mini.i18n.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 * {@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister} 这个类主要有两个接口实现
 *     <ul>
 *         <li>
 *             {@link org.springframework.context.annotation.ImportBeanDefinitionRegistrar}:
 *             这个接口主要是用来注册Bean定义的, 可以发现它是在context的annotation包下，所以需要构建
 *             注解后进行使用
 *         </li>
 *         <li>
 *             {@link org.springframework.context.EnvironmentAware} 这个接口主要用来设置Environment的，
 *             前面也有相关的使用
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     这个功能中核心的方法就是{@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)}
 *     它内部主要有四个相关的注册方法
 *     <ul>
 *         <li>
 *             <p>
 *             {@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister#registerServiceMessageSourceBeanDefinitions(AnnotationAttributes, BeanDefinitionRegistry)}
 *             这个方法，主要是将{@link io.mini.i18n.ServiceMessageSource}接口定义的Bean通过{@link io.mini.i18n.spring.beans.factory.ServiceMessageSourceFactoryBean}
 *             进行注册，注册的Bean名称是通过配置的source属性进行拼接, 比如source配置的是"messages", 那么注册的Bean名称就是"messagesServiceMessageSource"
 *             , 在整体注册完成后，还会注册一个"serviceMessageSource"的Bean, 这个Bean是一个代理Bean, 主要用来代理所有的
 *             {@link io.mini.i18n.ServiceMessageSource}接口的实现类, 这个Bean会被设置为Primary的
 *             {@link io.mini.i18n.spring.DelegatingServiceMessageSource}
 *             , 这个类的主要作用是将所有的ServiceMessageSource进行代理, 通过它来获取消息
 *             , 这个类的实现可以参考{@link io.mini.i18n.spring.DelegatingServiceMessageSource}，其最终的实现其实是
 *             {@link io.mini.i18n.CompositeServiceMessageSource}, 该部分的具体参考学习{@link io.mini.i18n.core._13_Feat_Class_CompositeServiceMessageSource}
 *             </p>
 *             <p>
 *                 这里在整体说一遍具体的过程:
 *                 <p>
 *                      1. 从两个地方获取到对应的sources，一个是Environment中获取的，一个是从注解EnableI18n的sources属性中获取的
 *                        , 这里的Environment中获取的主要是从配置文件中获取的,
 *                        具体的配置属性是"microsphere.i18n.sources", 这个属性的值是一个字符串数组, 代表多个source
 *                        , 这里的注解中的sources属性主要是用来配置默认的source, 也就是在没有配置文件中配置该属性时, 会使用注解中的sources属性
 *                        , 这里的sources属性的值也是一个字符串数组, 代表多个source
 *                        , 最终会将两个地方获取到的sources进行合并, 并去重, 最终得到一个唯一的sources集合
 *                  </p>
 *                  <p>
 *                      2.将所有的sources进行遍历, 对每一个source, 都会注册一个对应的Bean
 *                  </p>
 *                  <p>
 *                  3.注册一个"serviceMessageSource"的Bean, 该Bean是一个代理Bean, 主要用来代理所有的
 *                                          {@link io.mini.i18n.ServiceMessageSource}接口的实现类, 这个Bean会被设置为Primary的
 *                                          {@link io.mini.i18n.spring.DelegatingServiceMessageSource}
 *                  </p>
 *               </p>
 *         </li>
 *         <li>
 *           <p>
 *               {@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister#registerMessageSourceAdapterBeanDefinition(AnnotationAttributes, BeanDefinitionRegistry)}
 *               这个方法主要是注册一个MessageSource的适配器Bean, 该Bean的名称是"messageSource"
 *               , 该Bean的类型是{@link io.mini.i18n.spring.context.MessageSourceAdapter}
 *               , 该Bean的主要作用是用自定义的聚合 ServiceMessageSource（内部已经汇总多个来源）接管 Spring 原生的消息解析入口
 *               这样的话：
 *               <p>
 *               1. Spring 体系内的组件（如 Spring MVC、Spring Validation）在进行国际化消息解析时，
 *               将会使用自定义的MessageSourceAdapter，从而实现对国际化消息的统一管理和处理。
 *               </p>
 *               <p>
 *                2. Spring生态无缝衔接，接管Spring的消息部分的内容
 *               </p>
 *           </p>
 *         </li>
 *         <li>
 *            <p>
 *                {@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister#registerI18nApplicationListenerBeanDefinition(BeanDefinitionRegistry)}
 *                这个方法主要是注册一个I18nApplicationListener的Bean, 该Bean的名称是"i18nApplicationListener"
 *                , 该Bean的类型是{@link io.mini.i18n.spring.context.I18nApplicationListener}
 *                , 该Bean的主要作用是监听Spring的事件, 主要是用来监听ContextRefreshedEvent事件
 *                , 该事件是在Spring容器初始化完成后发布的
 *                , 该监听器的主要作用是在Spring容器初始化完成后,
 *                将自定义的ServiceMessageSource注册到Spring的MessageSource中
 *                , 这样的话, 就可以实现自定义的ServiceMessageSource和Spring的MessageSource的无缝衔接, 接管Spring的消息部分的内容
 *            </p>
 *         </li>
 *         <li>
 *          <p>
 *              {@link io.mini.i18n.spring.annotation.I18nImportBeanDefinitionRegister#registerBeanPostProcessorBeanDefinitions(BeanDefinitionRegistry)}
 *              这个方法主要是注册两个BeanPostProcessor的Bean, 主要用来处理ServiceMessageSource相关的Bean的生命周期
 *              , 这两个BeanPostProcessor分别是:
 *             {@link io.mini.i18n.spring.beans.factory.config.I18nBeanPostProcessor}以及{@link io.mini.i18n.spring.beans.factory.support.ServiceMessageSourceBeanLifecyclePostProcessor}
 *             <p>
 *                 1. {@link io.mini.i18n.spring.beans.factory.config.I18nBeanPostProcessor} 这个类主要是用来处理
 *                 在 Bean 实例化后、初始化前后，对带有国际化相关注解/属性的 Bean 进行处理（如注入 ServiceMessageSource，解析占位符，包装访问器等）
 *                 以及通过 BeanPostProcessor 机制，无需业务代码显式调用即可获得 i18n 支持。
 *             </p>
 *             <p>
 *                 2. {@link io.mini.i18n.spring.beans.factory.support.ServiceMessageSourceBeanLifecyclePostProcessor} 这个类主要是用来处理
 *                 ServiceMessageSource 相关 Bean 的生命周期管理（如初始化、销毁等），确保它们在 Spring 容器中的正确注册和注销。
 *             </p>
 *          </p>
 *         </li>
 *     </ul>
 * </p>
 */
public class _5_Feat_Class_I18nImportBeanDefinitionRegister {
    // todo 研究一下ImportBeanDefinitionRegistrar接口的内容
}
