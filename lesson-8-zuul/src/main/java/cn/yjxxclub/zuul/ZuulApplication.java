package cn.yjxxclub.zuul;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 路由网关(zuul)
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-5 下午2:30
 */
@EnableEurekaClient
@SpringBootApplication
public class ZuulApplication {
    /**
     * Zuul的主要功能是路由转发和过滤器
     * Zuul 是Netflix 提供的一个开源组件,致力于在云平台上提供动态路由，监控，弹性，安全等边缘服务的框架
     *
     * @param args
     */
    public static void main(String[] args) {SpringApplication.run(ZuulApplication.class, args);}

}