package io.mini.i18n.spring;

import org.springframework.context.EnvironmentAware;

/**
 * <p>
 * 我们这里首先来实现{@link io.mini.i18n.PropertiesResourceServiceMessageSource},
 * 该部分继承于{@link io.mini.i18n.PropertiesResourceServiceMessageSource}
 * ，我们知道PropertiesResourceServiceMessageSource是基于Properties文件的，
 * 它主要是能够将Properties文件中的内容加载并将其转换成Map形式返回，方便后续的根据key获取对应的国际化信息
 * 这里详细的实现过程的介绍可以看{@link io.mini.i18n.core._10_Feat_Abstract_PropertiesResourceServiceMessageSource}
 * </p>
 * <p>
 * 然后它还有两个接口实现分别是:
 *   <ul>
 *       <li>
 *           {@link io.mini.i18n.ReloadableResourceServiceMessageSource}这个接口的主要用来实现重新加载，也就是说当前整合后
 *           我们后续在Properties文件中修改了内容之后，可以通过某种方式来实现重新加载，从而让修改的内容生效，这部分的具体内容可以参考
 *           {@link io.mini.i18n.core._12_Feat_Interface_ReloadableResourceServiceMessageSource}
 *       </li>
 *       <li>
 *           另一个接口是{@link EnvironmentAware}，这个接口主要是用来设置对应的环境变量，使得我们能够通过Spring已经拿到的环境
 *           变量来获取对应的Properties内容。
 *       </li>
 *   </ul>
 * </p>
 * <p>
 *     该类的主要作用是通过Spring的Environment来获取对应的Properties内容，然后将其加载到
 *     {@link io.mini.i18n.PropertiesResourceServiceMessageSource}中，从而实现基于Spring Environment的国际化支持
 *     ，整体部分的实现主要就是用来解析对应的Resource，然后将对应的内容加载到PropertiesResourceServiceMessageSource中
 *     这里主要通过{@link PropertySourcesServiceMessageSource#loadAllPropertiesResources(String)}方法来实现的。
 * </p>
 */
public class _3_Feat_Class_PropertySourcesServiceMessageSource {
}
