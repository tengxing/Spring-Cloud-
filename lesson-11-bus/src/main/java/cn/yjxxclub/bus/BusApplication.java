package cn.yjxxclub.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 消息总线
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-20 下午2:30
 */
@EnableEurekaClient
@SpringBootApplication
public class BusApplication {

    public static void main(String[] args) {SpringApplication.run(BusApplication.class, args);}}
