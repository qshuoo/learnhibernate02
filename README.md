# LearnHibernate2
再次学习hibernate

## 搭建环境
### 导入jar包
* hibernate相关必须包(10个)
* 数据库驱动包

## 编写核心配置文件(hibernate.cfg.xml)
### 添加约束
### 配置链接属性
* 驱动
* 链接
* 用户名
* 密码
* 方言

### 其他配置
* 自动创建或更新表        
	create 创建表，创建表之前先清空表        
	create-drop 创建表，SessionFactory关闭时删除表       
	update 创建或更新表，没有表时创建表，有表时更新表         
	validate 验证表，只验证表的结构是否正确，不会创建表，可以更新         
* 显示、格式化sql语句
* 绑定线程session
* 配置二级缓存
### 添加映射路径

## 编写实体类

## 编写映射配置文件(xx.hbm.xml)
### 添加约束
### 映射class
### 配置唯一主键
### 配置主键生成策略
#### 自然主键
* assigned 用户自助管理

#### 代理主键
* native 主键递增
* identity 只支持主键可以递增的数据库
* increment 先查找主键的最大值，再加1插入
* sequence oracle支持
* uuid varchar 随机

## hibernate 核心api
* Configuration 加载配置文件
* SessionFactory 主要用于构建Session
* Session 进行数据库curd操作
* Transaction 处理事务
* Query 和 Criteria 用于查询数据

## 编写curd测试
`SessionFactory为重量级对象，只需要一个SessionFactory对象，编写工具实现`


### hibernate 对象状态
* 瞬时状态 与session和oid都不关联
* 持久状态 与session和oid都关联
* 游离状态(托管状态) 与session不关联，与oid关联


### hibernate 基本操作
* save()
* update()
* saveOrUpdate()
* delete()
* get()
* load()

`	当瞬时状态或游离状态转换为持久状态时，会存入session的缓存中，并形成对象的快照，以oid作为标示，对持久状态对象的修改，在事务commit时会通过oid与快照对比，若内存中的持久状态对象与快照不同，则会执行update操作`

`	对于save()操作，若使用主键代理操作，无论设置oid与否，都会在数据库中按照策略重新生成oid后插入一条新的数据`

`	对持久对象使用save(),update(),saveOrUpdate()，不会产生作用`

`	get为立即加载，load为延迟加载，返回的时代理对象，只有真正使用对象时，才会查询返回实际对象`


### hibernate 查询
#### 原生sql查询
* 编写sql语句
* 生成查询对象 session.getNativeQuery()
* 设置参数setParameter()
* 获取结果集list(),getResultList()
* 获取唯一结果 uniqueResult()
* 分页 setFirstResult(),setMaxResults()

#### hql语句
`面向对象的查询过程，根据类名查询，可移植性好`
* 编写hql语句
* 生成查询对象 session.getQuery()
* 设置参数setParameter()
* 获取结果集list(),getResultList()
* 获取唯一结果 uniqueResult()
* 分页 setFirstResult(),setMaxResults()

#### criteria查询
`比hql更加注重面向对象过程，不需要写查询语句`
* 生成CriteriaBuilder对象 session.getCriteriaBuilder()
* 生成CriteriaQuery查询对象 citeriaBuilder.createQuery()
* 生成Root(得到类) criteriaQuery.from()
* 生成Path(得到类中需要查询的属性) root.get()
* 生成Predicate(得到查询条件) 

	gt() 大于        
	lt() 小于       
	ge()  大于等于   
	le() 小于等于     
	equal() 等于    
	notEqual() 不等于     
	in()    在   范围内     
	between  A  and    B    输出A和B之间的内容，包括A 和B     
	like() 模糊匹配    
	notLike() 模糊匹配不上的内容    
	isNull()    
	isNotNull()    
	and   
	or    
	
* 选择查询的列criteiaQuery.select(path)	
* 通过条件查询criteriaQuery.where()
* session.createQuery(criteriaQuery)


## 缓存策略
### 一级缓存
`session级别的缓存`
* hibernate内置的缓存，不能解除
* 声明周期依赖于session的生命周期
* save(),update(),saveOrUpdate(),增加或更新数据库，将改变的数据添加至缓存中
* get(),load()第一次查询对象时，将查询的对象添加至缓存中
* evit()将指定的持久化对象从缓存中清除
* clear()清除缓存，释放资源 

### 二级缓存
`SessionFactory级别的缓存`
* 使用第三方插件，可插拔
* 被应用范围内的所有缓存共享，依赖于应用的生命周期

#### 过程
* 添加第三方jar包
* 配置hibernate.cfg.xml中添加二级缓存设置
* 编写ehcache.xml
* 在实体类映射文件中添加缓存设置 
