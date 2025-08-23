package io.mini.i18n.core;


import io.microsphere.lang.Prioritized;

/**
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
 */
public class _2_Feat_Interface_ServiceMessageSource {

}
