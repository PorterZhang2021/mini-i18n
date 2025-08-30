package io.mini.i18n.core;

import java.util.Map;

/**
 * <p>
 * {@link PropertiesResourceServiceMessageSource} 该类是对应的从Properties文件中获取国际化信息的一个实现类，同时其继承于
 * {@link io.mini.i18n.AbstractResourceServiceMessageSource}，
 * 该类主要需要实现{@link io.mini.i18n.AbstractResourceServiceMessageSource#loadMessages(String)}方法，这个
 * 方法主要是用来加载对应的资源文件，并将其转换成Map形式返回，方便后续的根据key获取对应的国际化信息，
 * 在{@link io.mini.i18n.AbstractResourceServiceMessageSource#initializeResource(String, Map)}
 * 部分使用到了，但那块儿并没有直接实现，是一个抽象方法，而这里就需要完成对它的实现，实现也比较简单，就是从Resource中将Properties获取到
 * 然后将其转换成Map形式返回即可。
 * </p>
 * <p>
 * ⚠️注意：实际上这里的loadAllProperties依然需要获取到Resource才行，而Resource需要后续具体的实现类来实现获取，
 * 所以{@link PropertiesResourceServiceMessageSource}本身也会有两个抽象方法，一个是getResource(String)，
 * 一个是loadAllPropertiesResources(String)，这两个方法同样需要具体的实现类来实现。
 * </p>
 */
public class _10_Feat_Abstract_PropertiesResourceServiceMessageSource {
}
