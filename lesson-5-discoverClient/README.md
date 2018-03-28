#Feign学习笔记
##前言
在Spring Cloud Netflix栈中，各个微服务都是以HTTP接口的形式暴露自身服务的，因此在调用远程服务时就必须使用HTTP客户端。我们可以使用JDK原生的URLConnection、Apache的Http Client、Netty的异步HTTP Client, Spring的RestTemplate。但是，用起来最方便、最优雅的还是要属Feign了。
##Feign简介
Feign是简化Java HTTP客户端开发的工具(java-to-httpclient-binder),它的灵感来自于Retrofit、JAXRS-2.0和WebSocket。Feign的初衷是降低统一绑定Denominator到HTTP API的复杂度，不区分是否为restful。使用Feign, 
我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。
它通过将注释处理为模板化的请求来工作。 在发送之前，参数以直接的方式应用于这些模板。 虽然这限制Feign仅支持基于文本的API，但它极大地简化了系统方面，例如重播请求。 知道这一点，单元测试你的转换也很容易。
> Feign其实就是对JDK原生的HttpURLConnection的封装
##依赖
```
<!-- https://mvnrepository.com/artifact/com.netflix.feign/feign-core -->
<dependency>
    <groupId>com.netflix.feign</groupId>
    <artifactId>feign-core</artifactId>
    <version>8.18.0</version>
    <scope>runtime</scope>
</dependency>
```

##FeignTest例子
```
/**
 *  FeignTest
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-28 下午3:00
 */
public class FeignTest {

    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
    }

    public static void main(String... args) {
        GitHub github = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(GitHub.class, "https://api.github.com");

        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("tengxing", "Spring-Cloud-Learn");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }
    }
}
```

##集成SpringCloud
###依赖
```
<!--spring boot feign -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```
##小例子
在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。比如：
```
@Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.sayHello(name);
    }

```
helloService.sayHello()就能完成发送HTTP请求和解码HTTP返回结果并封装成对象的过程。

Feign的定义

为了让Feign知道在调用方法时应该向哪个地址发请求以及请求需要带哪些参数，我们需要定义一个接口：
```
@FeignClient(name = "helloService")
public interface HelloService {
    @RequestMapping(value = "/hello/{value}",method = RequestMethod.GET)
    String sayHello(@RequestParam(value = "name") String name);
}
```
- `@FeignClient`用于通知Feign组件对该接口进行代理(不需要编写接口实现)，使用者可直接通过@Autowired注入。

- `@RequestMapping`表示在调用该方法时需要向/sayHello发送GET请求。

- `@PathVariable`与`SpringMVC`中对应注解含义相同。

Spring-Cloud应用在启动时，Feign会扫描标有@FeignClient注解的接口，生成代理，并注册到Spring容器中。生成代理时Feign会为每个接口方法创建一个RequetTemplate对象，该对象封装了HTTP请求需要的全部信息，请求参数名、请求方法等信息都是在这个过程中确定的，Feign的模板化就体现在这里。

在本例中，我们把Feign与Eureka和Ribbon组合使用，@FeignClient(name = "helloService")意为通知Feign在调用该接口方法时要向Eureka中查询名为ea的服务，从而得到服务URL。

##Encoder、Decoder和ErrorDecoder

Feign将方法签名中方法参数对象序列化为请求参数放到HTTP请求中的过程，是由编码器(Encoder)完成的。同理，将HTTP响应数据反序列化为java对象是由解码器(Decoder)完成的。

默认情况下，Feign会将标有@RequestParam注解的参数转换成字符串添加到URL中，将没有注解的参数通过Jackson转换成json放到请求体中。注意，如果在@RequetMapping中的method将请求方式指定为POST，那么所有未标注解的参数将会被忽略，例如：

```
@RequestMapping(value = "/hello/{value}",method = RequestMethod.GET)
    String sayHello(@RequestParam(value = "name") String name);
```

此时因为声明的是GET请求没有请求体，所以obj参数就会被忽略。
在Spring Cloud环境下，Feign的Encoder*只会用来编码没有添加注解的参数*。如果你自定义了Encoder, 那么只有在编码obj参数时才会调用你的Encoder。对于Decoder, 默认会委托给SpringMVC中的MappingJackson2HttpMessageConverter类进行解码。
只有当状态码不在200 ~ 300之间时ErrorDecoder才会被调用。ErrorDecoder的作用是可以根据HTTP响应信息返回一个异常，该异常可以在调用Feign接口的地方被捕获到。我们目前就通过ErrorDecoder来使Feign接口抛出业务异常以供调用者处理。
##换HTTP Client
Feign在默认情况下使用的是JDK原生的URLConnection发送HTTP请求，没有连接池，但是对每个地址会保持一个长连接，即利用HTTP的persistence connection 。我们可以用Apache的HTTP Client替换Feign原始的http client, 从而获取连接池、
超时时间等与性能息息相关的控制能力。Spring Cloud从Brixtion.SR5版本开始支持这种替换，首先在项目中声明Apache HTTP Client和feign-httpclient依赖：
```
<!-- 使用Apache HttpClient替换Feign原生httpclient -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
        </dependency>
        <dependency>
            <groupId>com.netflix.feign</groupId>
            <artifactId>feign-httpclient</artifactId>
            <version>${feign-httpclient}</version>
        </dependency>
```
## 总结
Feign在调用过程中模板化，清晰化，透明化。测试用例代码托管于:[Github](https://github.com/tengxing/Spring-Cloud-Learn/tree/master/lesson-5-discoverClient)
比较阿里Dubbo中暴露远程服务的方式，Dubbo是基于私有二进制协议进行传输，而Feign本质上还是个HTTP客户端。因此在用Spring Cloud Netflix搭建微服务，那么Feign无疑是最佳选择。
