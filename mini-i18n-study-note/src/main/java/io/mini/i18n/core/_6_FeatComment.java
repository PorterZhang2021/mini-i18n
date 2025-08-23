package io.mini.i18n.core;

/**
 * <p>
 * 在完成{@link io.mini.i18n.ServiceMessageSource},{@link io.mini.i18n.AbstractServiceMessageSource}
 * 以及{@link io.mini.i18n.EmptyServiceMessageSource},接下来，我们可以说完成了基本的核心功能骨架，
 * 然后就是要实现对应的具体内容，{@link io.mini.i18n.ServiceMessageSource}这部分主要是对信息来源以及获取信息的方式进行定义，
 * 接下来我们可以实现一些对应的具体实现比如从Resource中获取信息，简单来说就是初始化后通过资源(文件，数据库等)来获取我们想要的信息来源。
 * </p>
 * <p>
 * 从这里我们就可以发现对应的接口设计思路，首先是定义整体的功能抽象，在功能抽象的基础上，按照其对应的不同来源在进行一轮整体拆分，就比如i18n这个
 * {@link io.mini.i18n.ServiceMessageSource}, 它主要是对国际化信息的一个抽象，
 * 并通过{@link io.mini.i18n.AbstractServiceMessageSource}将大量一些重复的代码进行抽象，至于这个ServiceMessageSource从哪儿来，
 * 我们可以通过设计新接口，来定义对应的获取资源获取方式，比如从Resource中获取，还是从数据库中获取，这一套都可以抽象成资源，
 * 然后将对应可能需要的获取方法写到接口中，最后再通过具体的实现类来实现对应的功能，这样就可以形成一个完整的资源获取实现。
 * </p>
 */
public class _6_FeatComment {
}
