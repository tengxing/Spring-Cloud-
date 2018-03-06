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
public class RibbonClientApplication {

    /**
     * ribbon是一个负载均衡客户端，可以很好的控制http
     * 和tcp的一些行为。Feign默认集成了ribbon。
     * @param args
     */
    public static void main(String[] args) {SpringApplication.run(RibbonClientApplication.class, args);}

}