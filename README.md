# 整合项目

整合的项目包括网关、auth权限服务和backend服务。提供了一套微服务架构下，网关服务路由、鉴权和授权认证的项目案例。整个项目的架构图如下：

![ms](http://ovcjgn2x0.bkt.clouddn.com/%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84%E6%9D%83%E9%99%90%20%281%29.png "微服务架构权限")

欲了解更多，见博客《[微服务架构中整合网关、权限服务](http://blueskykong.com/2017/12/10/integration/)》 

 **你的star是对我最好的鼓励^_^**

## 项目使用方法

如果你的GitHub网络有问题，请移步码云：https://gitee.com/keets/microservice-integration

### 涉及到的组件与服务

微服务架构基于Spring Cloud，用到了部分Spring Cloud提供的组件。

- consul 服务发现组件，所有的服务注册到该组件，类似zk
- feign 声明式的http调用组件，Spring Cloud通过该组件实现服务之间的通信
- 存储主要是mysql和redis，auth的初始化sql脚本已经提供
- auth-server：端口9091 安全权限服务
- gateway：端口10101 网关，提供路由转发等
- backend-server：端口9092 实例的后端服务，提供接口级别的权限校验

### 端点测试

1. 安装一下，`mvn clean install -DskipTests`，包括三个模块。
2. 登录端点

![head](http://ovcjgn2x0.bkt.clouddn.com/login1-header.png "头部信息")
![form](http://ovcjgn2x0.bkt.clouddn.com/loginform2.png "表单信息")

```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTMwOTEzMzEsIlgtQU9ITy1Vc2VySWQiOiJjNjIxY2ZlMy02ZjUzLTQ1ZDYtODRiYi0wNzEyMTE5NDZiMjQiLCJ1c2VyX25hbWUiOiJrZWV0cyIsImp0aSI6ImEwODZmMWIyLWEzOTQtNDUwZS1iMDVlLTEzNmE0ZjdlOTEyZiIsImNsaWVudF9pZCI6ImZyb250ZW5kIiwic2NvcGUiOlsiYWxsIl19.MQP8l_RhmZ-rpxAg8Cguq6X6IA1PaKtrwlxZoMjMTLQ",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJrZWV0cyIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiJhMDg2ZjFiMi1hMzk0LTQ1MGUtYjA1ZS0xMzZhNGY3ZTkxMmYiLCJleHAiOjE1MTU2NDAxMzEsIlgtQU9ITy1Vc2VySWQiOiJjNjIxY2ZlMy02ZjUzLTQ1ZDYtODRiYi0wNzEyMTE5NDZiMjQiLCJqdGkiOiJlZTNhOThlOS0zOThhLTQwYTctOGI0YS1jYzRlOGY4ZmVmNDkiLCJjbGllbnRfaWQiOiJmcm9udGVuZCJ9.k2lVqFfJ0xNkly_10O1u3QQGTHMGp0kxVAEDMjdUgbo",
    "expires_in": 43199,
    "scope": "all",
    "X-AOHO-UserId": "c621cfe3-6f53-45d6-84bb-071211946b24",
    "jti": "a086f1b2-a394-450e-b05e-136a4f7e912f",
    "X-AOHO-ClientId": "frontend"
}
```
 
 3.刷新端点
 ![refresh](http://ovcjgn2x0.bkt.clouddn.com/refreshtoken.jpg "刷新端点")

返回结果与上面登录类似。

4.登出端点

![out](http://ovcjgn2x0.bkt.clouddn.com/logoutresponse.jpg "logout")

5.backend后端接口
![test](http://ovcjgn2x0.bkt.clouddn.com/demotest.jpg "demo test")

上述请求到达backend被拒绝，因为auth服务使用的userId是随机生成，如果你要真是使用，请修改那部分user服务的实现。

### 写在后面

感谢黄同学[@CANGWU](https://github.com/CANGWU) 对Spring EL部分做的修改。
项目整合如果遇到问题，可以加入qq群649932629，最好大家都能自行搞定。。

#### 推荐阅读
1. [微服务网关netflix-zuul](http://blueskykong.com/2017/11/13/gateway/)
2. [认证鉴权与API权限控制在微服务架构中的设计与实现（一）](http://blueskykong.com/2017/10/19/security1/)
3. [认证鉴权与API权限控制在微服务架构中的设计与实现（二）](http://blueskykong.com/2017/10/22/security2/)
4. [认证鉴权与API权限控制在微服务架构中的设计与实现（三）](http://blueskykong.com/2017/10/24/security3/)
5. [认证鉴权与API权限控制在微服务架构中的设计与实现（四）](http://blueskykong.com/2017/10/26/security4/)
6. [微服务架构中整合网关、权限服务](http://blueskykong.com/2017/12/10/integration/)



## 新版本修改 

###### By DLB 2018-3-16

### 修改记录

1. 修改所有包名，统一命名到com.wisfarm包下;
2. 所有服务中均加入了zipkin服务调用链监控；使用monitor-server作为zipkin-ui端进行监视；
3. auth-server 做授权服务，同时兼顾了user-server的功能，为外界提供user和permission的查询；
4. gateway对所有进入的请求做oauth2校验，并同时通过Feign调用auth-server中的user和permission接口对url进行权限校验；
5. gateway在转发所有的请求前，会在Request的Header处增加api_token字段，这个字段作为其他被调用服务对gateway身份的验证依据；
6. backend-demo微服务的demo项目，在每个项目中需要注意Inteceptor扩展来对gateway身份进行校验；

### 改进计划

1. user-server功能从auth-server中独立成一个微服务；
2. 在微服务项目中对gateway的校验方式使用jwt；



