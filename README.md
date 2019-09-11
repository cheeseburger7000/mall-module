# mall-modules
- [x] 添加商品->添加数据库和索引库（消息队列）
- [x] 高亮搜索
- [ ] 使用 Swagger 优化
- [ ] idea构建docker镜像，并发布到私服上（虚拟机上），然后vps从私服上获取docker镜像，然后构建应用。（compose）
- [ ] JsonFormatter ...
- [ ] 查询索引时，如何直接使用反序列器转化？
- [ ] 服务鉴权 JWT

## 涉及技术
- Spring Boot
- Mybatis
- ElasticSearch
- RabbitMQ

## 数据库
```sql
create database db_search_goods default character set utf8;

use db_search_goods

create table t_category (
    id varchar(255) not null,
    name varchar(255) not null,
    create_time timestamp not null,
    primary key (id)
);

create table t_goods (
    id varchar(255) not null,
    name varchar(255) not null,
    detail text not null,
    price bigint not null,
    stock int not null,
    image varchar(255) not null,
    create_time timestamp not null,
    category_id varchar(255) not null default '0',
    primary key (id)
);
```
备注：category_id为0的商品说明未分类。

## 其它
分而治之处理一个项目，从数据库文件开始理解。。。

关于前端页面设计
51cto jd mock webpack4.x
vue-order-app
blog-ui
happymall-ui 
