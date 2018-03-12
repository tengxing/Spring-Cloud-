package cn.yjxxclub.hystrixClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-6 下午3:45
 */
@FeignClient(name = "helloService", fallback = HelloServiceImpl.class)
public interface HelloService {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String sayHello(@RequestParam(value = "name") String name);
}
