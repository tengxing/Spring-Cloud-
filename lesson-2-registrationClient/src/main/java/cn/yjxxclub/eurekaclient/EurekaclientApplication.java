package cn.yjxxclub.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 注册客户端
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-5 下午2:30
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaclientApplication {

    public static void main(String[] args) {SpringApplication.run(EurekaclientApplication.class, args);}}
