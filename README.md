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
