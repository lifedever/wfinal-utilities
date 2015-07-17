MenuMapperPlugin使用说明
----
# 原理
即通过全局拦截器将请求进行拦截，获取当前请求的action中的`@Menu`注解指定的菜单，并将其通过`request attribute`的方式传入到前台视图，在页面中与其进行比对，使得当前菜单高亮显示。

# 项目配置
在项目自定义config中添加插件
```java
@Override
public void configPlugin(Plugins me) {
    MenuMapperPlugin menuMapperPlugin = new MenuMapperPlugin();
    me.add(menuMapperPlugin);
}
```
继续在config中添加拦截器
```java
@Override
public void configInterceptor(Interceptors me) {
    me.add(new MenuMapperInterceptor());
}
```
配置完成！
# 使用说明
## 全局相关
`MenuMapperPlugin` 默认使用`menu`作为`request attribute`，用于前台获取，查看`MenuMapperPlugin`源代码可以得知。如果想自定义`request attribute`，只需要在添加插件的时候将其当作参数传入即可：
```java
@Override
public void configPlugin(Plugins me) {
    MenuMapperPlugin menuMapperPlugin = new MenuMapperPlugin("myMenuAttr");
    me.add(menuMapperPlugin);
}
```
视图够建也相当简单，直接将`myMenuAttr`拿来进行判断即可：
```jsp
<ul class="nav nav-pills nav-stacked" id="sidebar">
    <li <c:if test="${empty(myMenuAttr) || myMenuAttr eq 'index'}">class="active" </c:if> >
        <a href="/">首页</a>
    </li>
    <li <c:if test="${myMenuAttr eq 'todo'}"> class="active"</c:if>>
        <a href="/todo">待办事项</a>
    </li>
    <li <c:if test="${myMenuAttr eq 'debtproduct'}"> class="active"</c:if>>
        <a href="/debtproduct">债权管理</a>
    </li>
    <li <c:if test="${myMenuAttr eq 'debtor'}"> class="active"</c:if>>
        <a href="/debtor">债务人管理</a>
    </li>
</ul>
```
## 注解使用
`@Menu(mapper="")`注解支持Controller类级别和action方法级别
```java
@Menu(mapper = "todo")
@ControllerBind(controllerKey = "/todo", viewPath = "todo")
public class TODOController extends Controller{

    public void index(){
    }

    @Menu(mapper = "todolist")
    public void list(){
        
    }
}

```
优先级：方法级别>类级别。

- 当请求`/todo/index`时，发送到视图的菜单值为`todo`
- 当请求`/todo/list`时，发送到视图的菜单值为`todolist`
非常容易理解，方法级有注解时类级别的注解不会生效。

## 后期目标
1. 简化视图判断: 寻找一种方法，能够引入此依赖视图可以很简单的使用，而不需要自己去写各种判断。
2. 增加取消menu的注解: 有时候可能在同一个Controller中定义了公共的menu，但是在请求里面具体的一个方法的时候，不向视图发送任何menu，目前的解决方法只能是将此特殊action提取到另一个Controller中或者将menu全部定义到需要的action上，Controller级别不定义。