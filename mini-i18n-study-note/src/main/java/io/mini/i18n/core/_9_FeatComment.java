package io.mini.i18n.core;

/**
 * <p>
 * 完成{@link io.mini.i18n.AbstractResourceServiceMessageSource}这个关于资源信息获取的抽象类后，我们接下来就开始具体的实现了，
 * 这块我们后续的实现首先会构造一个{@link io.mini.i18n.PropertiesResourceServiceMessageSource}这个类，这个类主要加载对应的Messages
 * 之后，完成对所有的Properties资源的加载，最终将其存入到一个Properties对象中，方便后续的获取。
 * </p>
 * <p>
 * 后续继承于{@link io.mini.i18n.PropertiesResourceServiceMessageSource}的类有两个，
 * 分别是{@link io.mini.i18n.DefaultPropertiesServiceMessageSource}, 该类主要是一个默认实现，其主要需要完成如何获取Resource以及
 * 如何加载Resource也就是形成一个Reader，用来读取，而最终这里的读取其实都是存储到Properties都是交给了其
 * 父类{@link io.mini.i18n.PropertiesResourceServiceMessageSource}来完成的。
 * </p>
 * <p>
 * 还有一个继承于{@link io.mini.i18n.PropertiesResourceServiceMessageSource}的类是{@link io.mini.i18n.PropertySourcesServiceMessageSource}，
 * 这个类需要同Spring做相关的兼容，其主要是通过Spring的Resource来进行资源的加载，最终也是通过父类{@link io.mini.i18n.PropertiesResourceServiceMessageSource}来完成
 * 对Properties的加载。
 * </p>
 * <p>
 *     ⚠️注意：PropertySourcesServiceMessageSource是对于spring的实现，所以在Core这块儿并没有进行具体的实现。
 * </p>
 */
public class _9_FeatComment {
}
