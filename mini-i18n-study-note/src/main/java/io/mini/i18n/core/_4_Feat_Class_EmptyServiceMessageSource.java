package io.mini.i18n.core;

/**
 * <p>8月23日 14:55</p>
 * <p>
 * 这里我们先不考虑{@link io.mini.i18n.AbstractServiceMessageSource}类，主要来分析一下{@link io.mini.i18n.EmptyServiceMessageSource}类
 * 的具体实现，这个类主要是一个空的实现，主要是为了提供一个空的国际化信息源实现，这个类内部没有其他的一些逻辑实现，主要就是实现一下{@link io.mini.i18n.ServiceMessageSource}
 * 作一些基本的方法的实现，比如说init、destroy、getMessage等方法，这些方法都是空实现.
 * </p>
 * <p>
 * <p>待确认</p>
 * <b>⚠️注意：下面只是对应的猜测，也可以说是问题，需要确认</b>
 * 这里可以发现对于{@link io.mini.i18n.EmptyServiceMessageSource},具体实现时我们是不可以自己进行实例化的，其本身用static关键字
 * 直接进行了实例化，并且是私有化的构造函数，这样就可以避免外部进行实例化，所以本身来看是一个单例模式的实现，这样就可以避免在使用的时候
 * 出现空指针异常的问题。
 * </p>
 */
public class _4_Feat_Class_EmptyServiceMessageSource {
}
