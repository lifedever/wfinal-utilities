wfinal-utilities
===========

基于jfinal框架的扩展包

目的是为了更加简化Jfinal开发流程，定义通用的controller和db

关于框架的详细说明，请参见[wiki](https://github.com/gefangshuai/wfinal-utilities/wiki)

**有任何疑问请在[issues](https://github.com/gefangshuai/wfinal-utilities/issues)中留言！**

测试项目：
[https://github.com/gefangshuai/baseFinal-noshiro](https://github.com/gefangshuai/baseFinal-noshiro);

如直接使用可以添加以下maven仓库信息
```xml
<repositories>
    <repository>
        <id>haoch-maven-snapshot-repository</id>
        <name>haoch-maven-snapshot-repository</name>
        <url>https://raw.github.com/gefangshuai/maven/master/</url>
    </repository>
</repositories>
```

添加依赖
```xml
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-model</artifactId>
    <version>2.1.1</version>
</dependency>
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-plugin-menumapper</artifactId>
    <version>2.1.1</version>
</dependency>
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-flash</artifactId>
    <version>2.1.1</version>
</dependency>
```

# Thanks
- java: 1.7+
- [jfinal](http://jfinal.com): 2.0
- jfinal-ext: 3.1.2

<strong style="color: red">框架持续更新中，敬请关注^_^!</strong>