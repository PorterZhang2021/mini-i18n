package io.mini.i18n.core;

import io.mini.i18n.CompositeServiceMessageSource;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * <p>
 * 该类主要是用来实现一个复合的消息源，所谓复合消息源，指的是它可以同时包含多个不同的消息源，
 * 从而实现更灵活和强大的消息管理功能。
 * </p>
 * <p>
 * 通过实现这个类，我们可以将多个消息源组合在一起，形成一个统一的接口，，本质上其实就是一个消息源的集合，
 * 从而让我们可以更方便地管理和使用这些消息源。其相关的方法如下:
 *     <ul>
 *         <li>
 *             {@link CompositeServiceMessageSource#CompositeServiceMessageSource()}以及
 *             {@link CompositeServiceMessageSource#CompositeServiceMessageSource(List)}两者
 *             主要是用来创建一个复合消息源对象的，前者是无参构造函数，后者是带有一个消息源列表参数的构造函数。
 *            这里可以使用链式构造函数的模式来进行代码简化
 *         </li>
 *         <li>
 *             {@link CompositeServiceMessageSource#init()}该方法主要是用来进行初始化的，这里我们直接调用
 *             {@link CompositeServiceMessageSource#serviceMessageSources}然后通过其每个实例的方法进行初始化就行
 *         </li>
 *         <li>
 *             {@link CompositeServiceMessageSource#getMessage(String, Locale, Object...)} 这个方法则是用来获取对应的国际化信息的，
 *             这里我们通过遍历所有的消息源，然后调用每个消息源的getMessage方法来获取对应的国际化信息，如果某个消息源能够返回对应的信息，那么我们就直接返回该信息，
 *             否则继续遍历下一个消息源，直到找到对应的信息或者遍历完所有的消息源为止。
 *         </li>
 *     </ul>
 * 其他方法的内容都是类似的，这里我们就不再赘述，本质上就是遍历集合，然后调用相关的方法实现。
 * </p>
 * <p>
 *     我们这里重点说一下{@link CompositeServiceMessageSource#iterate(Consumer)}以及{@link CompositeServiceMessageSource#iterate(Class, Consumer)}
 *     两个方法，这两个方法主要是用来遍历所有的消息源的，前者是遍历所有的消息源，后者是根据指定的类型来遍历消息源, 两者是函数式编程的一个简单应用。
 *     这里对应的Consumer主要是用来处理每个消息源的逻辑的，这样我们就可以通过传入不同的Consumer来实现不同的处理逻辑，从而实现更灵活和强大的消息管理功能。
 * </p>
 */
public class _13_Feat_Class_CompositeServiceMessageSource {
}
