package cn.yjxxclub.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-5 下午2:30
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaserverApplication {

    public static void main(String[] args) {SpringApplication.run(EurekaserverApplication.class, args);}}
