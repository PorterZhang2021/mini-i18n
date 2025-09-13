package io.mini.i18n.spring;

import io.mini.i18n.PropertiesResourceServiceMessageSource;

/**
 * <p>
 * 该部分的整合，主要是通过构建一个注解@EnableI18n来实现的, 当我们开启这个注解之后，我们就可以来实现对
 * i18n的相关的功能的支持，由于我们这里比较熟悉i18n的Core部分的实现，所以我们先实现对应的{@link PropertiesResourceServiceMessageSource}
 * 的内容，然后在来实现对Spring部分的整合。
 * </p>
 * <p>
 * 对于Spring部分的整合，当前的理解主要还是基于RegisterBean的方式，将对应需要的内容进行注册，然后后续就可以使用相关的内容了，
 * 但是至于注册的这些内容到底是什么，还需要后面实现的时候看一下，并且在最后整体在做一轮总结。
 * </p>
 */
public class _2_Comment {
}
