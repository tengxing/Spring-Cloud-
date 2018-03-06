package cn.yjxxclub.discoverclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 调用已经注册的应用
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-5 下午2:30
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FeignClientApplication {
    /**
     * Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，
     * 只需要创建一个接口并注解。它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。
     * Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。
     * 1:Feign 采用的是基于接口的注解
     * 2:Feign 整合了ribbon
     * @param args
     */
    public static void main(String[] args) {SpringApplication.run(FeignClientApplication.class, args);}

}