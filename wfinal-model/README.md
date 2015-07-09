介绍
----
扩展Jfinal model功能，增加几个通用方法，方便CURD，同时扩展了两个属性`pkName`和`tableName`方便使用。

其中WModel中主要扩展了`findAll`和`pageRecord`两中查询方式，相关参数有：

1. **不带参**: 直接查询所有记录，`select * from tableName` 或分页。
2. **[QueryMap](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-QueryMap)**: 查询参数封装
2. **[Sort](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-Sort)**: 排序封装
3. **[PageRequest](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-PageRequest)**: 分页参数封装，仅适用于分页查询`pageRecord`

# 使用方法
这里主要介绍一下分页的查询方式`pageRecord`，`findAll`功能类似，相对简单。

假如有一个书籍model，有两个属性 `title` 和 `author`。

假如查询框如下：
```html
<form class="input-group col-sm-3 pull-left" method="get" action="/book/page">
    <input type="text" class="form-control input-sm" name="key" placeholder="Search for...">
    <span class="input-group-btn">
        <button class="btn btn-default btn-sm" type="submit">Go!</button>
    </span>
</form>
```
可以看到查询参数为`key`。当我们输入查询值，同时模糊匹配 `title` 及 `author`，将与关键字匹配的书名或作者的数据查询出来。

现在我们要在后台实现列表数据，同时带查询及分页功能。

* 首先获得`key`参数，**如果key没填则默认为`""`**，这么做的目的是防止出现`%null%`而导致查询有误。
* 然后对`QueryMap`进行封装
```java
QueryParam queryParam = new QueryParam("title", Operator.LK, QueryUtils.getLikeValue(key));
QueryMap queryMap = new QueryMap(queryParam).or("author", Operator.LK, QueryUtils.getLikeValue(key));
```
**`QueryUtils`是一个为了方便查询的工具类**
* 然后对`PageRequest`进行封装
```java
// 这里的page是页面传来的当前第几页信息，从1开始
PageRequest pageRequest = new PageRequest(getParaToInt("page", 1), 5);
```
* 最后进行查询
```java
Page<Book> bookPage = Book.dao.pageRecord(pageRequest, queryMap, new Sort("title", Direction.ASC));
```

**搞定！**

# 继续延伸
* **[QueryParam](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-QueryParam)**
* **[QueryMap](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-QueryMap)**
* **[Sort](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-Sort)**
* **[PageRequest](https://github.com/gefangshuai/wfinal-utilities/wiki/WModel%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E-PageRequest)**