# springmvc-base 基础开发框架（后台）
## 项目搭建目标
基于多个优秀的开源项目，高度整合封装而成的高效，高性能，强安全性的Java EE快速开发平台。

(1)Spring mvc + MyBatis+ H-ui <br/>  
(2)Spring mvc + MyBatis+ Dwz <br/> 

#技术选型
##后端技术组成
JDK:Jdk1.7.0_80 <br/>  
核心框架：Spring Framework 4.0<br/>
安全框架：Apache Shiro 1.2<br/>
视图框架：Spring MVC 4.0<br/>
服务端验证：Hibernate Validator 5.1<br/>
工作流引擎：Activiti 5.15、FoxBPM 6<br/>
任务调度：Spring Task 4.0<br/>
持久层框架：MyBatis 3.2<br/>
数据库连接池：Alibaba Druid 1.0<br/>
缓存框架：Ehcache 2.6、Redis<br/>
日志管理：SLF4J 1.7、Log4j<br/>
工具类：Apache Commons、Jackson 2.2、Xstream 1.4、Dozer 5.3、POI 3.9<br/>
消息通讯：Activity mq <br/> 
服务管理：Dubbo <br/>

##开发平台
服务器中间件：在Java EE 5规范（Servlet 2.5、JSP 2.1）下开发，支持应用服务器中间件 有Tomcat 7。<br/>
数据库支持：目前仅提供MySql和Oracle数据库的支持。<br/>
开发环境：Java EE、Eclipse、Maven、Git。<br/>

#安全考虑
1.开发语言：系统采用Java 语言开发，具有卓越的通用性、高效性、平台移植性和安全性。<br/>
2.分层设计：（数据库层，数据访问层，业务逻辑层，展示层）层次清楚，低耦合，各层必须通过接口才能接入并进行参数校验（如：在展示层不可直接操作数据库），保证数据操作的安全。<br/>
3.双重验证：用户表单提交双验证：包括服务器端验证及客户端验证，防止用户通过浏览器恶意修改（如不可写文本域、隐藏变量篡改、上传非法文件等），跳过客户端验证操作数据库。<br/>
4.安全编码：用户表单提交所有数据，在服务器端都进行安全编码，防止用户提交非法脚本及SQL注入获取敏感数据等，确保数据安全。<br/>
5.密码加密：登录用户密码进行SHA1散列加密，此加密方法是不可逆的。保证密文泄露后的安全问题。<br/>
6.强制访问：系统对所有管理端链接都进行用户身份权限验证，防止用户直接填写url进行访问。<br/>


#目录结构
## Springmvc-base<br/>
--base-common        公共模块<br/>
--base-manager        后台管理<br/>
--base-web                前端项目<br/>
--base-payment        支付模块<br/>
--base-business        业务模块<br/>


dev-1.0 test
