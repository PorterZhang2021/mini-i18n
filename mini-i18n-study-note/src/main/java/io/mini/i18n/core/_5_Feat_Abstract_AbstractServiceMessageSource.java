package io.mini.i18n.core;

import io.mini.i18n.AbstractServiceMessageSource;

import java.util.Locale;

/**
 * <p>8月23日 15:03</p>
 * <p>
 * 这里我们再来分析一下{@link io.mini.i18n.AbstractServiceMessageSource}类，这个类主要是一个抽象类的实现，主要是提供一个基础的国际化信息源的实现。
 * 我们首先让其实现对应的{@link io.mini.i18n.ServiceMessageSource}, 然后我们来一个个实现对应的功能
 *     <ul>
 *         <li>
 *              {@link io.mini.i18n.AbstractServiceMessageSource#AbstractServiceMessageSource(String)}: 该内容是
 *              AbstractServiceMessageSource类的构造函数，主要是传入一个source参数，这个参数主要是用来标识当前国际化信息的来源
 *              在构造函数中会对source进行非空校验，然后将其赋值给成员变量source，同时还会初始化一个codePrefix
 *              ，这个前缀主要是用来标识当前国际化信息的前缀，格式为source + "."，这样就可以形成一个完整的前缀。
 *         </li>
 *         <li>
 *             <p>
 *                 <b>⚠️注意：这个部分没有实现</b>
 *             </p>
 *             {@link io.mini.i18n.AbstractServiceMessageSource#getMessage(String, Object...)}: 该方法还是用接口的默认实现
 *             因为在接口默认实现已经给了一个默认的方法实现，所以这里我们就不需要再进行实现了，实际上也是要套用{@link io.mini.i18n.AbstractServiceMessageSource#getMessage(String, Locale, Object...)}
 *             只不过如果这里在写一遍有一些重复了，并且后期也会出现类似的霰弹式修改，所以直接用super.getMessage()即可, 防止后期出现霰弹式
 *             修改的情况，当然这里可以直接不实现，也没有任何问题，这里我暂时先选择不实现。
 *         </li>
 *         <li>
 *             {@link io.mini.i18n.AbstractServiceMessageSource#getMessage(String, Locale, Object...)}: 该方法主要是用来获取对应的国际化信息
 *             这里的实现其实主要是做一些对应的非空校验，最终的实现在{@link io.mini.i18n.AbstractServiceMessageSource#getInternalMessage(String, String, Locale, Locale, Object...)}，
 *             我理解这里主要是做一些对应的非空校验，然后最终调用一个抽象方法来获取对应的国际化信息，这样就可以让子类去实现具体的逻辑，而不需要
 *             重复去做这些非空校验工作了，对于参数code, locale同样也有对应的函数，方便需要对这两个内容进行二次处理。
 *         </li>
 *         <li>
 *             {@link AbstractServiceMessageSource#getLocale()}这部分的实现也是类似，它这里将locale这个内容交给子类去实现，抽象类这里
 *             会进行非空校验，如果子类没有实现，那么就会调用接口的默认实现，获取默认的locale。
 *         </li>
 *         <li>
 *             {@link AbstractServiceMessageSource#getDefaultLocale()}以及{@link AbstractServiceMessageSource#getSupportedLocales()}
 *             这两个方法主要是用来获取默认的locale以及支持的locale列表，这两个内容主要是通过成员变量进行赋值的，如果没有赋值，那么就会调用接口的默认实现
 *             默认情况下两者会去做成员变量的非空校验，如果没有赋值，那么就会调用接口的默认实现。
 *         </li>
 *         <li>
 *             {@link AbstractServiceMessageSource#defaultLocale}以及{@link AbstractServiceMessageSource#supportedLocales}两者
 *             都需要通过set进行设置，否则只会使用默认值进行传入，对于{@link AbstractServiceMessageSource#defaultLocale}来说，其设置比较简单，
 *             直接进行set即可，而对于{@link AbstractServiceMessageSource#supportedLocales}来说，其设置就比较复杂了，下面大概是一个简单的例子:
 *             <p>
 *                 假设传入的 supportedLocales 是：
 *                 <pre>{@code
 *                      List<Locale> supportedLocales = Arrays.asList(new Locale("zh", "CN", "variant"), new Locale("en", "US"));
 *                 }
 *                 </pre>
 *                 后续收集处理后的locale可能是:
 *                 <pre>{@code
 *                 List<Locale> processedLocales = Arrays.asList(new Locale("zh"), new Locale("zh", "CN"), new Locale("zh", "CN", "variant"),
 *                 new Locale("en"), new Locale("en", "US"));}
 *                 </pre>
 *                 这样就会形成一个完整的locale列表，方便后续进行匹配.
 *             </p>
 *         </li>
 *     </ul>
 * </p>
 */
public class _5_Feat_Abstract_AbstractServiceMessageSource {
}
