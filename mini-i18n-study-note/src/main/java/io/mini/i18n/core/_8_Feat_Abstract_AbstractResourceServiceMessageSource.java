package io.mini.i18n.core;

import io.mini.i18n.AbstractResourceServiceMessageSource;
import io.mini.i18n.AbstractServiceMessageSource;
import io.mini.i18n.ServiceMessageSource;

import java.util.Map;

/**
 * <p>
 * 在完成了{@link io.mini.i18n.ResourceServiceMessageSource}接口的定义之后，我们可以通过定义一个新的抽象类
 * {@link io.mini.i18n.AbstractResourceServiceMessageSource}来实现从Resource中获取国际化信息，
 * 该类继承自{@link io.mini.i18n.AbstractServiceMessageSource}抽象类，并实现了
 * {@link io.mini.i18n.ResourceServiceMessageSource}接口，其内部有的方法如下:
 * <ul>
 *  <li>
 *      {@link AbstractResourceServiceMessageSource#init()}方法主要是用来进行资源初始化的,
 *      该部分来自{@link ServiceMessageSource}接口，其主要逻辑如下
 *      <ol>
 *          <li>确认是否存在source，如果不存在就报错</li>
 *          <li>获取支持的Locales, 通过Locales获取对应的Resource</li>
 *          <li>获取到对应的Resource下的Messages,本质上就是类似文件或者其他资源的kv对,做一层验证
 *          该部分就是在{@link AbstractResourceServiceMessageSource#initializeResource(String, Map)}
 *          的实现了
 *          </li>
 *          <li>将Messages进行缓存，方便后续获取</li>
 *      </ol>
 *  </li>
 *  <li>
 *      {@link io.mini.i18n.AbstractResourceServiceMessageSource#initializeResource(String)}以及
 *      {@link io.mini.i18n.AbstractResourceServiceMessageSource#initializeResources(Iterable)}是
 *      两个初始化Resource的方法，这两个来自于{@link io.mini.i18n.ResourceServiceMessageSource}接口，
 *      一个是初始化单个Resource，一个是初始化多个Resource，这两个方法主要是用来加载对应的资源文件，其实这里的实现，
 *      我们可以让{@link io.mini.i18n.AbstractResourceServiceMessageSource#initializeResource(String)}方法调用
 *      {@link io.mini.i18n.AbstractResourceServiceMessageSource#initializeResources(Iterable)}方法来加载资源文件，
 *      因为本质上来说，这里的初始化资源文件的逻辑是一样的，只不过一个是单个，一个是多个而已，所以我们可以通过这种方式来简化代码，
 *      该部分可以说是《重构成设计模式》这本书中提到的"模板方法模式"的一个简单应用，以及用Composite替换一/多之分的一个应用。
 *      <p>
 *          ⚠️注意：这里使用的是synchronized关键字来保证线程安全，因为在多线程环境下，可能会有多个线程同时调用该方法来初始化资源文件，
 *          这样就可能会导致资源文件被重复加载，或者加载不完整的问题，所以我们需要通过synchronized关键字来保证同一时间只有一个线程能够执行该方法，
 *          从而保证资源文件的完整性和一致性。
 *      </p>
 *  </li>
 *  <li>
 *      {@link io.mini.i18n.AbstractResourceServiceMessageSource#getInitializeResources()}: 该方法主要是用来获取已经初始化的资源列表，
 *      返回一个Set集合，里面包含了所有已经初始化的资源名称。
 *  </li>
 *  <li>
 *      {@link io.mini.i18n.AbstractResourceServiceMessageSource#getEncoding()}: 该方法主要是用来获取资源文件的编码格式，
 *      默认情况下返回UTF-8 编码.
 *  </li>
 *  <li>
 *      {@link io.mini.i18n.AbstractResourceServiceMessageSource#getInternalMessage(String, String, Locale, Locale, Object...)}:
 *      该方法主要是用来获取对应的国际化信息，这里是一个基本的实现，其主要是获取到message后进行格式化处理，然后返回对应的结果.
 *  </li>
 * </ul>
 * </p>
 * <p>
 *     <ul>
 *         <li>
 *             <p>
 *                 🙋：为啥这部分有两个实现{@link AbstractResourceServiceMessageSource#init()}以及
 *                 {@link AbstractResourceServiceMessageSource#initializeResource(String)}}, 底层都用了
 *                 {@link AbstractResourceServiceMessageSource#initializeResource(String, Map)}的方法，
 *                 这个是什么原因呢？ 不是到后面就有了两个不同的初始化操作？
 *             </p>
 *         </li>
 *     </ul>
 * </p>
 */
public class _8_Feat_Abstract_AbstractResourceServiceMessageSource {
}
