package io.mini.i18n.spring;

/**
 * <p>
 * {@link io.mini.i18n.spring.context.I18nApplicationListener}主要实现了{@link org.springframework.context.event.SmartApplicationListener}
 * 接口：这个接口是Spring事件监听器增强接口，继承自{@link org.springframework.context.ApplicationListener}以及{@link org.springframework.core.Ordered}
 * ，其主要作用是监听特定类型的事件，并且可以定义监听器的执行顺序。
 * </p>
 * <p>
 * 这里监听的事件类型有两个:
 *     <ul>
 *         <li>
 *             {@link org.springframework.context.event.ContextRefreshedEvent}:
 *             这个主要是Spring中出发了上下文重新刷新的事件，如果触发了这个事件，说明Spring容器已经初始化完成或者刷新完成，
 *             这个时候可以执行一些初始化操作，比如加载配置文件、初始化资源等。
 *             该事件通常在以下两种情况下触发：
 *             <ul>
 *                 <li>当使用ConfigurableApplicationContext接口的refresh()方法手动刷新应用程序上下文时。</li>
 *                 <li>当使用Spring的ApplicationContext实现类（如ClassPathXmlApplicationContext或AnnotationConfigApplicationContext）创建上下文时，</li>
 *             </ul>
 *             <p>
 *                 在该类中，主要做了两件事：
 *                 <ul>
 *                     <li>初始化国际化消息源（ServiceMessageSource）: 通过从Spring上下文中获取名为"serviceMessageSource"的bean，并将其设置到I18nUtils中，供应用程序其他部分使用。</li>
 *                     <li>初始化{@link org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver}:
 *                     该部分代码检查Spring上下文中是否存在AcceptHeaderLocaleResolver类的bean，如果存在，则会对其设置对应的默认语言。</li>
 *                 </ul>
 *             </p>
 *             <p>
 *                 {@link org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver}是Spring对国际化支持的一部分，用于
 *                 用于根据 HTTP 请求的 Accept-Language 头来决定请求的区域设置（Locale），从而实现内容的本地化显示。
 *             </p>
 *         </li>
 *         <li>
 *             {@link org.springframework.context.event.ContextClosedEvent}：这部分主要是监听Spring上下文关闭的事件，
 *             当Spring容器关闭时，会触发这个事件，这个时候可以执行一些清理操作，比如释放资源、关闭连接等。
 *             在该类中，主要做了一件事：销毁国际化消息源，主要就是置为null。
 *         </li>
 *     </ul>
 * </p>
 */
public class _8_Feat_Class_I18nApplicationListener {
}
