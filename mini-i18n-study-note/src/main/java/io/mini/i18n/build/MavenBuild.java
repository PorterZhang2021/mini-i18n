package io.mini.i18n.build;

/**
 * 8月23日 10:44
 * <p></p>
 * 整个项目由Maven进行构建和管理，这里说一下当前项目是如何做最开始的初始化的
 * 1. 利用mvn创建一个官方的快捷项目
 * 2. 利用wrapper命令完成对maven wrapper的锁定
 * 3. 拿出创建的文件中的pom.xml文件，修改成pom.xml文件
 * 4. 利用快速创建脚本构建出目录的结构
 * 5. 利用mvn clean install -N来对父POM进行刷新
 * 6. 在利用mvn clean package进行一轮刷新
 * 7. 找到提示将maven加载到IDEA中
 * <p></p>
 * 为什么要用maven wrapper呢？
 * 使用这个之后，后续如果其他人拉取这个项目之后，就不需要再去安装maven了
 * 直接使用 ./mvnw clean package   # 无需本地装 Maven
 * 这里生成wrapper的命令是mvn wrapper:wrapper
 * <p></p>
 * 对应的快速目录生成脚本在resource文件夹下的create-project-structure.sh
 * <p></p>
 * 这里同样补充一下maven的settings文件，可能会用到, 同样在resource文件夹下的settings.xml
 */
public class MavenBuild {
}
