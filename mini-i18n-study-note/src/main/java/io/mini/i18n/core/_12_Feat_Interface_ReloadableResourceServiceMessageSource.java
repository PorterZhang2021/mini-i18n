package io.mini.i18n.core;

/**
 * <p>
 * 该接口主要是定义了一个可重新加载的资源信息获取接口，之所以要定义这个接口，主要是考虑到在实际的应用中，
 * 可能会存在资源文件发生变化的情况，比如我们在运行过程中，可能会对国际化的资源文件进行更新，这时候如果不重新加载，
 * 那么就无法获取到最新的资源信息，从而影响应用的国际化功能。
 * </p>
 * <p>
 * 通过实现这个接口，我们可以让我们的消息源具备重新加载资源的能力，从而确保在资源文件发生变化时，
 * 我们的应用能够及时获取到最新的资源信息，提升用户体验和系统的灵活性。
 * </p>
 * <p>
 * 对应的方法有两组分别如下:
 *     <ul>
 *         <li>
 *             {@link io.mini.i18n.ReloadableResourceServiceMessageSource#reload(String)}以及
 *             {@link io.mini.i18n.ReloadableResourceServiceMessageSource#reload(Iterable)} )}
 *             这两个方法主要是用来重新加载资源的, 这里同样是采用了单个资源重新加载和批量资源重新加载两种方式，方便不同场景下的使用。
 *         </li>
 *         <li>
 *             {@link io.mini.i18n.ReloadableResourceServiceMessageSource#canReload(String)}以及
 *             {@link io.mini.i18n.ReloadableResourceServiceMessageSource#canReload(Iterable)} )}
 *             这两个方法主要是用来判断是否支持重新加载资源的, 这里同样是采用了单个资源重新加载和批量资源重新加载两种方式，方便不同场景下的使用。
 *         </li>
 *     </ul>
 * </p>
 */
public class _12_Feat_Interface_ReloadableResourceServiceMessageSource {
}
