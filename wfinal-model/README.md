wfinal model使用说明
----
扩展Jfinal model功能，增加几个通用方法，方便CURD，同时扩展了两个属性`pkName`和`tableName`方便使用。

# API说明
##### 保存或更新，自动判断
`public boolean saveOrUpdate()`

##### 查询所有记录
`public List<M> findAll()`

##### 查询所有记录，带排序功能
`public List<M> findAll(Sort sort)`
* sort: 排序对象