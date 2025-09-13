package io.mini.i18n.openfeign;

/**
 * <p>
 * 对于OpenFeign的一个国际化的支持主要就是要在其请求发送的最后阶段通过请求拦截器进行
 * 一轮请求语言的处理，从而达到国际化的支持， 主要就是在接受到请求后通过对应的
 * Accept-Language头信息获取到对应的语言信息，然后在通过OpenFeign的请求拦截器
 * 将这个头信息设置到请求中，从而达到国际化的支持。
 * </p>
 */
public class _2_Feat_AcceptLanguageHeaderRequestInterceptor {
}
