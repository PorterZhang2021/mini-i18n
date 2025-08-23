package io.mini.i18n.core;

/**
 * <p>8月23日 14:47</p>
 * <p>
 * 在实现了{@link io.mini.i18n.ServiceMessageSource}接口之后，接下来我们就可以实现对应的类了，这里主要有两个类的实现:
 *    <ul>
 *        <li>
 *            {@link io.mini.i18n.EmptyServiceMessageSource}：这是一个空的国际化信息源实现类，主要是为了提供一个空的实现。
 *        </li>
 *        <li>
 *            {@link io.mini.i18n.AbstractServiceMessageSource}：这是一个抽象类的实现，主要是提供一个基础的国际化信息源的实现。
 *        </li>
 *    </ul>
 * </p>
 *
 * <p>
 *     <b>⚠️注意：下面只是当前的自己的猜测，如果正确那么后续可以把这部分整理到对应的卡片实例部分</b>
 *     可以发现在这部分的实现中，使用了一个类似于NULLObject的设计模式实现，因为这里有一个对应的EmptyServiceMessageSource类，来兜底了
 *     为NULL的情况，可以说是一个非常好的设计模式的应用，这样就可以避免在使用的时候出现空指针异常的问题。
 * </p>
 */
public class _3_FeatComment {
}
