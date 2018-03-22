package cn.yjxxclub.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 配置客户端
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-5 下午2:30
 */
//@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication {
    /**
     * 在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组
     * 在Spring Cloud中，有分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。
     *
     * @param args
     */
    public static void main(String[] args) {SpringApplication.run(ConfigClientApplication.class, args);}

}