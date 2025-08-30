package io.mini.i18n.core;

import io.mini.i18n.ResourceServiceMessageSource;

/**
 * <p>
 * {@link io.mini.i18n.PropertiesResourceServiceMessageSource}实现完成后，我们首先实现{@link io.mini.i18n.DefaultServiceMessageSource}这个类，
 * 该类主要是一个默认实现，其主要需要完成如何获取Resource以及如何加载Resource也就是形成一个Reader，用来读取，而最终这里的读取其实都是存储到Properties都是交给了其
 * 父类{@link io.mini.i18n.PropertiesResourceServiceMessageSource}来完成的, 其主要有两个方法需要实现
 *     <ul>
 *         <li>
 *             {@link io.mini.i18n.DefaultServiceMessageSource#getResource(String)} 该方法主要是用来获取对应的Resource资源的,
 *             这里的实现主要是从META-INF/i18n/{}/{}下获取对应的资源文件。
 *         </li>
 *         <li>
 *             {@link io.mini.i18n.DefaultServiceMessageSource#loadAllPropertiesResources(String)} 该方法主要是用来加载对应的Resource资源的,
 *             这里的实现主要是通过ClassLoader的getResources来获取对应的资源文件, 所以我们将获取到的resource传入后，我们就可以获取
 *             到对应的资源文件列表，然后将其转换成Reader返回即可。
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     ⚠️注意：{@link ResourceServiceMessageSource#getEncoding()}这里为什么需要的原因，就是因为我们需要通过{@link java.io.InputStreamReader}来进行流的读取，
 *     所以这里需要指定编码格式，默认情况下返回UTF-8 编码.
 * </p>
 */
public class _11_Feat_Class_DefaultServiceMessageSource {
}
