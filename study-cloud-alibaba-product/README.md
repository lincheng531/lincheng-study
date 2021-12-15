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
           #不注册到注册中心(默认注册)
           register-enabled: true
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

## 二、配置中心（config）

1. 在resources下创建bootstrap.properties或bootstrap.yml。config是默认从bootstrap中读取config配置

2. 在配置文件中配置config配置信息

   ```yaml
   cloud:
         config:
           #nacos 配置中心地址
           server-addr: http://localhost:8848
           #是否开启监听和自动刷新
           refresh-enabled: true
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
             #true时(默认flase),@RefreshScope才能生效
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

## 三、远程调用（dubbo）

1. 在配置文件中配置dubbo配置信息

```
dubbo:
  application:
    #dubbo应用名称
    name: ${spring.application.name}
    #是否使用远程的元数据中心，如果使用，则会将元数据信息注册到指定的元数据中心，否则使用本地元数据中心
    metadata-type: local
    #qos=Quality of Service 是Dubbo的在线运维命令，可以对服务进行动态的配置、控制(上下线)及查询，
    #qos-enable: true
  registry:
    # 注册中心与注册地址
    address: spring-cloud://127.0.0.1:8848
    #不将注册中心用于配置中心
    use-as-config-center: false
    #是否允许注册
    register: true
    #是否允许订阅
    #subscribe: true
    # 注册者允许的协议
    #accepts: dubbo,rest
  #consumer:
    #启动时不检查生产者的状态，防止生产者未启动时启动失败
    #check: false
  #provider:
    # 服务提供者要求使用随机Token加密，防止消费者绕过注册中心调用
    #token: true
  #Dubbo服务暴露的协议配置
  protocol:
    #协议名称
    name: dubbo
    # dubbo协议端口(1 表示自增端口,从20880开始)
    port: -1
  #dubbo服务扫描基准包
  scan:
    #base-packages: com.lincheng.study.api
  cloud:
    #Dubbo 消费端订阅服务端的应用名，多个服务提供者用逗号分隔
    subscribed-services: study-cloud-alibaba-product
```

- metadata-type
  - dubbo.application.metadata-type 的取值为 remote 或 local， 默认 local。对于 应用的元数据，Dubbo提供了两种保存方式 【本地保存】 和 【元数据中心保存】。如果为local ，则会将应用的元数据信息保存在应用本地，否则则会将元数据信息保存到 metadata-report.address 指定的元数据中心中。因此，当此属性为 remote 时， 必须存在 metadata-report.address 配置
  - 调用时机：在DubboBootstrap中会调用 DubboBootstrap#initialize会调用 DubboBootstrap#initMetadataService方法，其中会根据 metadata-type 类型来获取不同的 MetadataService 实现类从而实现本地或远程保存
2. 提供者暴露接口

   ```java
   //暴露接口
   @DubboService
   public class ProductServiceApiImpl implements IProductServiceApi {
   
       public Object testDubbo(String source){
           return source + "测试product,dubbo成功";
       }
   }
   ```

3. 消费者调用提供者

   ```java
   @RestController
   @RequestMapping("/testDubbo")
   public class TestDubboController {
   
       @DubboReference(check = false, timeout = 5 * 1000, retries = 0)
       private IProductServiceApi productServiceApi;
   
       @RequestMapping("/transferProduct")
       public Object transferProduct() {
           return productServiceApi.testDubbo("consumer");
       }
   
   }
   ```


## 四、消息队列（rocketMQ）

1. github地址：

   [RocketMQ配合说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/RocketMQ) 

   [rocketmq-example](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/rocketmq-example/readme-zh.md) 

2. Spring Cloud Stream 内部有两个概念：Binder 和 Binding。

   - Binder: 跟外部消息中间件集成的组件，用来创建 Binding，各消息中间件都有自己的 Binder 实现。

3. rocket [win10配置，启动](https://blog.csdn.net/bbc2005/article/details/85218497?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522161830122416780271530766%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=161830122416780271530766&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-1-85218497.first_rank_v2_pc_rank_v29&utm_term=rockermq++win10%E6%9C%AC%E5%9C%B0%E5%AE%89%E8%A3%85) 

   - 启动NameServer

     ```
     start mqnamesrv.cmd
     ```

   - 启动Broker

     ```
     start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
     ```

   - 关闭broker

     ```
     mqshutdown.cmd broke
     ```

   - 关闭namesrv

     ```
     mqshutdown.cmd namesrv
     ```

4. 配置 Input 和 Output 的 Binding 信息并配合 `@EnableBinding` 注解使其生效

   ```java
   @EnableBinding({ProductStream.class})
   public class StudyCloudAlibabaProductApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(StudyCloudAlibabaProductApplication.class, args);
       }
   }
   ```

5. 生产者yml配置

   ```yaml
   spring:
     cloud:
     	stream:
         bindings:
           output: {destination: study-rocketMQ-topic, content-type: application/json}
         rocketmq:
           binder:
             name-server: localhost:9876
           bindings:
             output:
               producer:
                 #是否启用 Producer,默认值: true
                 enable: true
                 #是否使用同步得方式发送消息。默认值: false
                 sync: false
   
   ```

6. input通道接口

   ```java
   public interface ProductStream {
   
       @Output("output")
       MessageChannel productOutput();
   
   }
   ```

7. 消费者yml配置

   ```yaml
   spring:
     cloud:
     	stream:
         bindings:
           output: {destination: study-rocketMQ-topic, content-type: application/json, group: product-output-group }
         rocketmq:
           binder:
             name-server: localhost:9876
           bindings:
             output:
               consumer:
                 enabled: true
                 #Consumer 是否是广播消费模式。如果想让所有的订阅者都能接收到消息，可以使用广播模式。默认值: false。
                 broadcasting: false
                 #Consumer 是否同步消费消息模式。
                 orderly: false
   
   ```

8. input通道接口

   ```java
   public interface ConsumerStream {
   
       @Input("output")
       SubscribableChannel consumerOutput();
   
   }
   ```

9. 消费者监听

   ```JAVA
   @Component
   public class ConsumerStreamListener {
       @StreamListener("output")
       public void onMessage(@Payload RocketMQMessageDTO message) {
           System.out.println("消费mq");
           System.out.println("线程编号:" + Thread.currentThread().getId());
           System.out.println("消息内容:" + message);
       }
   
   }
   ```

10. 总结

    - 无论是yml，还是代码绑定的通道，都是要同一个才能进行发送与接
