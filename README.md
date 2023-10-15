![newbee-mall-cloud-alibaba](./static-files/newbee-mall-cloud-alibaba-l.png)

![Build Status](https://img.shields.io/badge/build-passing-green.svg)
![Version 4.0.0](https://img.shields.io/badge/version-4.0.0-yellow.svg)
[![License](https://img.shields.io/badge/license-GPL3.0-blue.svg)](https://github.com/newbee-ltd/newbee-mall-cloud/blob/main/LICENSE)

newbee-mall-cloud 项目是新蜂商城 newbee-mall 项目的微服务版本，一款基于 Spring Cloud Alibaba + Nacos + Sentinel + Seata + Spring Cloud Gateway + OpenFeign + Spring Cloud Seluth + Zipkin + ELK 等技术的大型微服务实战项目。

当前分支的 Spring Boot 版本为 3.0.2，Spring Cloud 版本为 2022.0.0，想要学习和使用其它版本可以直接点击下方的分支名称跳转至对应的仓库分支中。

| 分支名称                                                     | Spring Boot Version | Spring Cloud Version | Spring Cloud Alibaba Version |
| ------------------------------------------------------------ | ------------------- | -------------------- | ---------------------------- |
| [main](https://github.com/newbee-ltd/newbee-mall-cloud)      | 2.6.3               | 2021.0.1             | 2021.0.1.0                   |
| [spring-cloud-2022.x](https://github.com/newbee-ltd/newbee-mall-cloud/tree/spring-cloud-2022.x) | 3.0.2               | 2022.0.0             | 2022.0.0.0                   |

2019 年开源了第一个单体版本，由最初新蜂商城单体项目，逐步过渡到前后端分离和微服务架构的项目，到现在已经“开枝散叶”，成长为一系列的项目集合，下图为新蜂商城项目由 2019 年至 2023 年的开源历程。由基础项目慢慢优化，不断地增加技术栈，让用户学习到越来越多知识点的同时，对开源作者的技术提升也是一个很大的帮助。

![newbee-mall-course-2023](./static-files/newbee-mall-course-2023.png)

**坚持不易，如果觉得项目还不错的话可以给项目一个 Star 吧，也是对我自 2019 年开始一直更新这个项目的一种鼓励啦，谢谢各位的支持。**

| 项目名称             | 仓库地址                                                     | 备注                                                         |
| :------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| newbee-mall          | [newbee-mall in GitHub](https://github.com/newbee-ltd/newbee-mall)<br>[newbee-mall in Gitee](https://gitee.com/newbee-ltd/newbee-mall) | 初始版本、Spring Boot、Thymeleaf、MyBatis、MySQL             |
| newbee-mall-plus     | [newbee-mall-plus in GitHub](https://github.com/newbee-ltd/newbee-mall-plus)<br/>[newbee-mall-plus in Gitee](https://gitee.com/newbee-ltd/newbee-mall-plus) | 升级版本、优惠券、秒杀、支付、Spring Boot、Thymeleaf、MyBatis、MySQL、Redis |
| newbee-mall-cloud    | [newbee-mall-cloud in GitHub](https://github.com/newbee-ltd/newbee-mall-cloud)<br/>[newbee-mall-cloud in Gitee](https://gitee.com/newbee-ltd/newbee-mall-cloud) | 微服务版本、分布式事务、Spring Cloud Alibaba、Nacos、Sentinel、OpenFeign、Seata |
| newbee-mall-api      | [newbee-mall-api in GitHub](https://github.com/newbee-ltd/newbee-mall-api)<br/>[newbee-mall-api in Gitee](https://gitee.com/newbee-ltd/newbee-mall-api) | 前后端分离、Spring Boot、MyBatis、Swagger、MySQL             |
| newbee-mall-api-go   | [newbee-mall-api-go in GitHub](https://github.com/newbee-ltd/newbee-mall-api-go)<br/>[newbee-mall-api-go in Gitee](https://gitee.com/newbee-ltd/newbee-mall-api-go) | 前后端分离、Go、Gin、MySQL                                   |
| newbee-mall-vue-app  | [newbee-mall-vue-app in GitHub](https://github.com/newbee-ltd/newbee-mall-vue-app)<br/>[newbee-mall-vue-app in Gitee](https://gitee.com/newbee-ltd/newbee-mall-vue-app) | 前后端分离、Vue2、Vant                                       |
| newbee-mall-vue3-app | [newbee-mall-vue3-app in GitHub](https://github.com/newbee-ltd/newbee-mall-vue3-app)<br/>[newbee-mall-vue3-app in Gitee](https://gitee.com/newbee-ltd/newbee-mall-vue3-app) | 前后端分离、Vue3、Vue-Router4、Pinia、Vant4                  |
| vue3-admin           | [vue3-admin in GitHub](https://github.com/newbee-ltd/vue3-admin)<br/>[vue3-admin in Gitee](https://gitee.com/newbee-ltd/vue3-admin) | 前后端分离、Vue3、Element-Plus、Vue-Router4、Vite            |

## 技术选型

当前分支下的开发框架选择如下所示。

| 开发框架             | 版本       |
| :----------------: | :----------------: |
| Spring Boot          | 3.0.2      |
| Spring Cloud         | 2022.0.0   |
| Spring Cloud Alibaba | 2022.0.0.0 |
| MyBatis              | 3.5.12     |
| SpringDoc            | 2.2.0      |
| Lombok               | 1.18.24    |

当前分支下的微服务组件技术选型如下所示。

|        技术        |            组件实现             |  版本  |  备注  |
| :----------------: | :-----------------------------: | :----: | :----: |
| 服务注册与服务发现 |              Nacos              | 2.2.1  | 已整合 |
|      配置中心      |              Nacos              | 2.2.1  | 已整合 |
|      服务通信      |           Open Feign            | 4.0.0  | 已整合 |
|     负载均衡器     |    Spring Cloud Loadbalancer    | 4.0.0  | 已整合 |
|      服务网关      |      Spring Cloud Gateway       | 4.0.0  | 已整合 |
|       断路器       |            Sentinel             | 1.8.6  | 已整合 |
|      链路追踪      |   Micrometer Tracing、Zipkin    | 1.10.3 | 已整合 |
|     分布式事务     |              Seata              | 1.7.0  | 已整合 |
|      日志中心      | ElasticSearch、Logstash、Kibana | 8.10.2 | 已整合 |

![technology-selection](./static-files/newbee-mall-cloud-technology-selection.png)

## 架构图简版

![simple-architecture](./static-files/newbee-mall-cloud-architecture.png)

## 开发及部署文档

1. [**Spring Cloud Alibaba 项目实战：点亮微服务技能点！**](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
2. [项目须知和课程约定](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
3. [漫谈微服务架构（一）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
4. [漫谈微服务架构（二）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
5. [漫谈微服务架构（三）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
6. [微服务落地一站式解决方案——Spring Cloud](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
7. [实战基础1-代码运行环境及开发工具介绍](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
8. [实战基础2-Spring Boot 开发介绍及Spring Cloud Alibaba模板项目构建](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
9. [服务通信基础讲解](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
10. [微服务架构中的服务治理](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
11. [Nacos安装与配置](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
12. [Nacos整合之服务注册编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
13. [Nacos整合之服务发现编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
13. [(补充章节)谈一谈配置中心](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
13. [(补充章节)整合Nacos配置中心编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
13. [(补充章节)配置动态刷新及多配置读取编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
14. [服务通信之负载均衡器](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
15. [负载均衡器的源码分析及自定义负载均衡算法](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
16. [服务治理与服务通信总结](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
17. [OpenFeign介绍与整合](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
18. [OpenFeign参数传递编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
19. [服务网关之Spring Cloud Gateway](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
20. [整合Spring Cloud Gateway编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
21. [服务网关Spring Cloud Gateway之Predicate（断言）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
22. [服务网关Spring Cloud Gateway之Filter（过滤器）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
23. [微服务最终实战项目的启动和运行注意事项](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
24. [最终实战项目的功能介绍与功能演示](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
25. [分布式事务问题演示](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
26. [分布式事务解决方案及Seata搭建](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
27. [整合Seata编码实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
28. [Seata的运行流程分析](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
29. [服务容错之限流与熔断](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
30. [服务容错之Sentinel限流配置实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
31. [服务容错之Sentinel降级熔断配置实践](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
32. [链路追踪之Sleuth+Zipkin整合](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
33. [从零到一搭建微服务项目编码实战（一）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
34. [从零到一搭建微服务项目编码实战（二）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
35. [从零到一搭建微服务项目编码实战（三）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
36. [从零到一搭建微服务项目编码实战（四）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
37. [从零到一搭建微服务项目编码实战（五）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
38. [从零到一搭建微服务项目编码实战（六）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
39. [从零到一搭建微服务项目编码实战（七）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
40. [从零到一搭建微服务项目编码实战（八）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
41. [从零到一搭建微服务项目编码实战（九）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
42. [从零到一搭建微服务项目编码实战（十）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
43. [从零到一搭建微服务项目编码实战（十一）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
44. [从零到一搭建微服务项目编码实战（十二）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
45. [从零到一搭建微服务项目编码实战（十三）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
46. [从零到一搭建微服务项目编码实战（十四）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
47. [从零到一搭建微服务项目编码实战（十五）](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
49. [(补充章节)Spring Cloud Gateway聚合Swagger接口](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
50. [(补充章节)微服务架构实战项目中整合Seata](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
51. [(补充章节)微服务架构实战项目打包及部署](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
52. [(补充章节)微服务架构实战项目中整合Sentinel](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
53. [(补充章节)微服务架构实战项目中整合Seluth、Zipkin](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
54. [(补充章节)链路追踪之ELK日志中心搭建](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
55. [(补充章节)微服务架构实战项目中整合ELK日志中心](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)
55. [课程总结](https://juejin.cn/book/7085254558678515742?suid=1996368849416216&source=android)

## 联系作者

关注公众号：**程序员十三**，回复"勾搭"进群交流。

![wx-gzh](https://newbee-mall.oss-cn-beijing.aliyuncs.com/wx-gzh/%E7%A8%8B%E5%BA%8F%E5%91%98%E5%8D%81%E4%B8%89-%E5%85%AC%E4%BC%97%E5%8F%B7.png)

> 大家有任何问题或者建议都可以在 [issues](https://github.com/newbee-ltd/newbee-mall-cloud/issues) 中反馈给我，我会慢慢完善这个项目。

- 我的邮箱：2449207463@qq.com
- QQ技术交流群：791509631

> newbee-mall-cloud 在 GitHub 和国内的码云都创建了代码仓库，如果有人访问 GitHub 比较慢的话，建议在 Gitee 上查看该项目，两个仓库会保持同步更新。

- [newbee-mall-cloud in GitHub](https://github.com/newbee-ltd/newbee-mall-cloud)
- [newbee-mall-cloud in Gitee](https://gitee.com/newbee-ltd/newbee-mall-cloud)

## 软件著作权

本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！

## 感谢

- [spring-projects](https://github.com/spring-projects/spring-boot)
- [spring-cloud](https://github.com/spring-cloud)
- [spring-cloud-alibaba](https://github.com/alibaba/spring-cloud-alibaba)
- [alibaba](https://github.com/alibaba)
- [seata](https://github.com/seata/seata)
- [elasticsearch](https://github.com/elastic/elasticsearch)
- [mybatis](https://github.com/mybatis/mybatis-3)
- [projectlombok](https://github.com/projectlombok/lombok)
- [swagger-api](https://github.com/swagger-api)
