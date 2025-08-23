package io.mini.i18n.core;

/**
 * <p>
 * 在完成了{@link io.mini.i18n.ServiceMessageSource}接口的定义之后，我们可以通过定义一个新的接口
 * {@link io.mini.i18n.ResourceServiceMessageSource}来实现从Resource中获取国际化信息，
 * 该接口继承自{@link io.mini.i18n.ServiceMessageSource}接口，其内部有的方法如下:
 * <ul>
 *  <li>
 *     {@link io.mini.i18n.ResourceServiceMessageSource#initializeResource(String)}以及
 *     {@link io.mini.i18n.ResourceServiceMessageSource#initializeResources(Iterable)}是两个初始化Resource的方法，
 *     一个是初始化单个Resource，一个是初始化多个Resource，这两个方法主要是用来加载对应的资源文件，具体如何加载以及对应的实现取决于
 *     具体的实现类。
 *  </li>
 *  <li>
 *      {@link io.mini.i18n.ResourceServiceMessageSource#getInitializeResources()}: 该方法主要是用来获取已经初始化的资源列表，
 *      返回一个Set集合，里面包含了所有已经初始化的资源名称。
 *  </li>
 *  <li>
 *      {@link io.mini.i18n.ResourceServiceMessageSource#getEncoding()}: 该方法主要是用来获取资源文件的编码格式，
 *      默认情况下返回UTF-8 编码.
 *  </li>
 * </ul>
 * </p>
 */
public class _7_Feat_Interface_ResourceServiceMessageSource {
}
