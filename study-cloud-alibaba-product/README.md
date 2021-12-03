# spring cloud alibaba

## 一、注册中心（discovery）

1. 配置文件中，添加nacos配置

   ```yaml
   spring:
     application:
       #应用名称
       name: study-cloud-alibaba-product
     cloud:
       nacos:
         discovery:
           #nacos 注册中心地址
           server-addr: http://localhost:8848
           #注册到哪个命名空间中(不填,默认为public)
           namespace: 3e81c560-3c91-484f-bb67-c1a4e01ce688
   ```

2. 在启动类中，添加注解@EnableDiscoveryClient，开启服务注册与发现功能

   ```java
   @SpringBootApplication
   //开启服务注册与发现功能
   @EnableDiscoveryClient
   public class StudyCloudAlibabaProductApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(StudyCloudAlibabaProductApplication.class, args);
       }
   
   }
   ```

3. 启动后，在nacos服务管理-服务列表可看到已注册服务

![avatar](./picture/服务注册.png)

## 二、配置中心(config)

1. 在resources下创建bootstrap.properties或bootstrap.yml。config是默认从bootstrap中读取config配置

2. 在配置文件中配置config配置信息

   ```yaml
   cloud:
         config:
           #nacos 配置中心地址
           server-addr: http://localhost:8848
           #告诉从哪个命名空间获取配置(不填,默认为public)
           namespace: f15dc566-c615-4dbe-88f9-0c812ea6e2f6
           #配置文件名称(Data Id)
           #name: ${spring.application.name}
           #文件后缀是什么类型
           file-extension: yml
           #从哪个组进行配置获取（Group）
           group: DEFAULT_GROUP
           #多文件配置
           extension-configs[0]:
             dataId: ${spring.application.name}.yml
             group: DEFAULT_GROUP
             #true时(默认flase),@RestController才能生效
             refresh: true
           extension-configs[1]:
             dataId: all.yml
             group: DEFAULT_GROUP
             refresh: true
   ```
   
   - 配置单文件在name中配置就即可，多文件在extension-configs中配置。
   
   - @RestController，更改配置文件，程序不用重启，就可以读取更新后的配合数据
   
     ```java
     //更改配置文件，程序不用重启，就可以读取更新后的配置数据
     @RefreshScope
     @RestController
     public class TestConfigController {
     
         @Value("${test.alibaba.config}")
         public String configName;
     
         @Value("${test.all}")
         public String all;
     
         @RequestMapping("/testAlibabaConfig")
         public Object testAlibabaConfig(){
             return Arrays.asList(configName,all);
         }
     }
     ```
   
   - extension-configs:refresh=true时（默认flase），@RestController才能生效

