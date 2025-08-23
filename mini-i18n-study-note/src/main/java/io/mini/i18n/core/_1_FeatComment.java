package io.mini.i18n.core;

import io.microsphere.lang.Prioritized;

/**
 * 8月23日 12:02
 * <p>
 * 初步理解:
 * 该模块主要是要实现通过对应的properties来进行国际化的一个功能，这里设置对应国家的语言的properties就可以实现对国际化语言功能的替换支持
 * </p>
 *
 * <p>
 * 在这里具体实现已经成型了，所以我们就从接口层面往下进行分析，先从接口入手介绍可能的实现，然后最后再对每个要实现的代码功能进行一个具体的实现，
 * 最终形成一个完整的国际化功能的实现
 * </p>
 * <p>
 * 这一块儿可能整体实现完之后还有一轮具体的分析，主要分析核心功能最后时机的流程，在做一轮整体的总结，以求自己更加的理解，并且会在过程中提出
 * 一些对应的问题，还有对应的一些内容，这些内容如果不理解到时候可以直接去问小马哥，争取把这个功能点彻底的搞懂。
 * </p>
 * <p>
 * 对于问题我们就采用类似<b>问题··问题:这是一个问题</b>的方式来记录，方便后续的查阅与整理。
 * </p>
 *
 * <p>
 * 8月23日 14:04
 * </p>
 * <p>
 * 首先实现是一个接口{@link io.mini.i18n.ServiceMessageSource}，这个接口主要会初始化一下当前的生命周期，同样也会进行生命周期
 * 的销毁，同时该接口继承于{@link Prioritized}，这个接口主要为一个优先级接口，为对应的对象提供相对应的排序能力，主要会按照优先级进行排序
 * 接口主要会有如下方法:
 *     <ul>
 *         <li>
 *             init: 主要是进行初始化的一个方法
 *         </li>
 *         <li>
 *             destroy: 主要是进行销毁的一个方法
 *         </li>
 *         <li>
 *             getMessage: 主要是进行获取对应的国际化信息的一个方法, 其会提供一个对应的默认方法，主要是会自动调取getLocale()方法来获取当前
 *             的locale，做对应的本地化信息获取
 *         </li>
 *         <li>
 *             getLocale: 主要是获取当前的locale,同时也有一个默认的方法getDefaultLocale()，主要是获取当前的默认locale
 *         </li>
 *         <li>
 *             getSupportedLocales: 主要是获取当前所支持的所有的locale
 *         </li>
 *         <li>
 *             getSource: 主要是获取当前的国际化信息的来源
 *         </li>
 *     </ul>
 * </p>
 *
 * <p>8月23日 14:47</p>
 * <p>
 *    在实现了{@link io.mini.i18n.ServiceMessageSource}接口之后，接下来我们就可以实现对应的类了，这里主要有两个类的实现:
 *    <ul>
 *        <li>
 *            {@link io.mini.i18n.EmptyServiceMessageSource}：这是一个空的国际化信息源实现类，主要是为了提供一个空的实现。
 *        </li>
 *        <li>
 *            {@link io.mini.i18n.core.AbstractServiceMessageSource}：这是一个抽象类的实现，主要是提供一个基础的国际化信息源的实现。
 *        </li>
 *    </ul>
 * </p>
 *
 * <p>
 *     <b>⚠️注意：下面只是当前的自己的猜测，如果正确那么后续可以把这部分整理到对应的卡片实例部分</b>
 *     可以发现在这部分的实现中，使用了一个类似于NULLObject的设计模式实现，因为这里有一个对应的EmptyServiceMessageSource类，来兜底了
 *     为NULL的情况，可以说是一个非常好的设计模式的应用，这样就可以避免在使用的时候出现空指针异常的问题。
 * </p>
 *
 * <p>8月23日 14:55</p>
 * <p>
 *     这里我们先不考虑{@link io.mini.i18n.AbstractServiceMessageSource}类，主要来分析一下{@link io.mini.i18n.EmptyServiceMessageSource}类
 *     的具体实现，这个类主要是一个空的实现，主要是为了提供一个空的国际化信息源实现，这个类内部没有其他的一些逻辑实现，主要就是实现一下{@link io.mini.i18n.ServiceMessageSource}
 *     作一些基本的方法的实现，比如说init、destroy、getMessage等方法，这些方法都是空实现.
 * </p>
 * <p>
 *     <p>待确认</p>
 *     <b>⚠️注意：下面只是对应的猜测，也可以说是问题，需要确认</b>
 *     这里可以发现对于{@link io.mini.i18n.EmptyServiceMessageSource},具体实现时我们是不可以自己进行实例化的，其本身用static关键字
 *     直接进行了实例化，并且是私有化的构造函数，这样就可以避免外部进行实例化，所以本身来看是一个单例模式的实现，这样就可以避免在使用的时候
 *     出现空指针异常的问题。
 * </p>
 */
public class _1_FeatComment {
}
