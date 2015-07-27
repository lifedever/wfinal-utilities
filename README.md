# 介绍
~~`wfinal-utilities` 是基于jfinal极速开发框架的一个扩展，集成了`jfinal`相关依赖、`druid`连接池、`wincn-commons工具类集合`、`servlet-api`及`jstl`，所以引用此扩展后不需要在额外引入上述依赖。本扩展会同步保持jfinal最新版，体验极速开发最新特性！~~

从`2.1.0`开始，不再整体依赖，已经拆分为几个独立的module进行单独依赖，这样做的目的也是为了遵循jfinal的特性，防止项目依赖过于臃肿。

**有任何疑问请在[issues](https://github.com/gefangshuai/wfinal-utilities/issues)中留言！**

测试项目：
[https://github.com/gefangshuai/baseFinal-noshiro](https://github.com/gefangshuai/baseFinal-noshiro);


# 名称由来
个人网站`http://wincn.net + jfinal = wfinal`!

非常感谢jfinal给我们带来编码的各种快感，本框架的宗旨是
> **发扬JFinal精神，延续JFinal精神!**

# 使用方法
首先[点击这里](https://github.com/gefangshuai/wfinal-utilities/archive/master.zip)下载最新代码

然后将其解压，然后运行maven安装命令
```bash
mvn clean compile install
```
将扩展安装到mavne本地仓库
# 依赖介绍
## 1. jfinal基础依赖
只包含jfinal框架所需的基础libs
```xml
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-base</artifactId>
    <version>${wfinal.version}</version>
</dependency>
```
## 2. MenuMapperPlugin依赖
开启MenuMapper Plugin功能（插件已依赖`wfinal-base`，不需要再单独依赖！）
```xml
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-menumapper</artifactId>
    <version>${wfinal.version}</version>
</dependency>
```
## 3. 开启WModel功能（已依赖`wfinal-base`，不需要再单独依赖！）
```xml
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-model</artifactId>
    <version>${wfinal.version}</version>
</dependency>
```

## 4. 开启Flash功能（已依赖`wfinal-base`，不需要再单独依赖！）
```xml
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-flash</artifactId>
    <version>${wfinal.version}</version>
</dependency>
```

## 4. 开启Security功能（已依赖`wfinal-base`，不需要再单独依赖！）
```xml
<dependency>
    <groupId>io.github.gefangshuai.wfinal</groupId>
    <artifactId>wfinal-security</artifactId>
    <version>${wfinal.version}</version>
</dependency>
```

# 开发计划
Follow on [trello](https://trello.com/c/2w0GnVut/10--)

# 更新说明
### 2.1.4 update
- 完成`wfinal-cecurity`所有功能的扩展，具体使用方法可以参见[wfinal security使用说明](https://github.com/gefangshuai/wfinal-utilities/wiki/wfinal-security%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E)
- 修复没有加`@RequireLogin`注解导致`subjectKey`没有传入到前台的BUG
- 去掉`MenumapperInterceptor` 和 `SecurityInterceptor` 两个Interceptor的配置，改为插件中动态配置，不需要再人工引入，这样就可以集成框架的时候少写两个拦截器

### 2.1.3 update
* 加入`wfinal-security`安全验证模块，实现了登录验证功能托管，具体使用方法请参考[wfinal security使用说明](https://github.com/gefangshuai/wfinal-utilities/wiki/wfinal-security%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E)（未完成，查看进度请关注：[trello progress](https://trello.com/c/uLAtEEjG/5--)）
* 重构menumapper模块，删除没用的注解扫描过程
* 改进WModel中获取数据表信息的方式, 改为jfinal的方式获取，节省效率
```java
Table table = TableMapping.me().getTable(getClass());
```
* 其他已知bug修复

### 2.1.2 update
##### 扩展FlashMessageUtils方法，将redirect功能继承到FlashMessageUtils中。

虽然这个改进看似简单，却能将原来的两行代码缩少为一行！（掌声~~ -_-！），使用放法：
```java
FlashMessageUtils.redirectSuccessMessage(this, "/signin", "退出成功！");
```
其他方法可以直接查看源码即可。

# Thanks
- java: 1.7+
- [jfinal](http://jfinal.com): 2.0
- jfinal-ext: 3.1.2

<strong style="color: red">框架持续更新中，敬请关注^_^!</strong>
