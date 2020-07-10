# spring-cloud-alibaba

#### 介绍
该项目通过学习 `gradle` 构建 `Spring Cloud Alibaba` 微服务架构

该项目由 `maven` 构建的 `Spring Cloud Alibaba` 微服务架构 通过 `gradle init` 初始化演变得来

所以有些配置文件 `build.gradle` 文件与正常文件不同
 
#### 软件架构说明
1. `Spring Boot` 和 `mybatis plus` 必须对应 （虽然官方没有说、但是我们自己还是要对应一下）

    ```
    Spring Boot 2.1.9.RELEASE 
    mybatis plus 3.2.0
    alibaba：
        nacos-discovery 2.1.0.RELEASE
        sentinel 0.9.0.RELEASE
        nacos-config 0.9.0.RELEASE
        openfeign 2.1.3.RELEASE
        gateway  2.1.3.RELEASE
    ```

2. `gradle` 配置 `lombok` 和 `maven` 不同

`gradle` 需要添加四个依赖 --> `gradle init` 时需要的依赖

正常构建时并不需要这么多的依赖
```
annotationProcessor 'org.projectlombok:lombok:1.18.12'
compileOnly 'org.projectlombok:lombok:1.18.12'
testAnnotationProcessor 'org.projectlombok:lombok:1.18.12'
testCompileOnly 'org.projectlombok:lombok:1.18.12'
```

#### 模块说明
1. `nacos-gateway` 网关
2. `nacos-user` 用户信息管理

#### 使用说明

1.  首先先启动 `nacos-gateway` 
    注意修改 `application.yml` 中的连接地址 `spring.cloud.nacos.discovery.server-addr='nacos的连接地址'`
2.  新加的模块 需要在 `nacos-gateway` 网关中进行配置
    ```
    # 采用自定义路由 ID（有固定用法，不同的 id 有不同的功能，详见：https://cloud.spring.io/spring-cloud-gateway/2.0.x/single/spring-cloud-gateway.html#gateway-route-filters）
    - id: NACOS-USER
      # 采用 LoadBalanceClient 方式请求，以 lb:// 开头，后面的是注册在 Nacos 上的服务名
      uri: lb://nacos-user
      # Predicate 翻译过来是“谓词”的意思，必须，主要作用是匹配用户的请求，有很多种用法
      predicates:
        # Method 方法谓词，这里是匹配 GET 和 POST 请求
        - Method=GET,POST
    ```
3. 访问

    访问时通过`nacos-gateway` + 服务名称进行访问相应的服务接口

#### 参与贡献
1. ShaoJie


#### 联系开发
ShaoJie 

博客 `www.lzmvlog.top`
