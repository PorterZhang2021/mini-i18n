# 进入根目录
cd /Users/porterzhang/other/code/ideaProject/mini-i18n

# 快速创建缺失目录和最小 pom
for m in mini-i18n-parent mini-i18n-dependencies mini-i18n-core mini-i18n-openfeign mini-i18n-spring mini-i18n-spring-boot mini-i18n-spring-cloud mini-i18n-spring-cloud-server mini-i18n-study-note
do
  mkdir -p "$m"/src/{main,test}/java
  cat > "$m"/pom.xml <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>io.porterzhang.mini-projects</groupId>
    <artifactId>mini-i18n</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </parent>

  <artifactId>\${m}</artifactId>
  <packaging>jar</packaging>

  <dependencies>
    <!-- 按需补充 -->
  </dependencies>
</project>
EOF
  # 替换占位符
  sed -i '' "s/\${m}/$m/g" "$m"/pom.xml
done