package cn.yjxxclub.hystrixClient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon配置
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-6 下午3:37
 */
@Component
@EnableHystrix
@EnableHystrixDashboard
public class RibbonConfig {

    /**
     * 负载均衡+rest
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
