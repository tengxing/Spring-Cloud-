package cn.yjxxclub.hystrixClient;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-12 下午3:49
 */
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(@RequestParam(value = "name") String name) {
        System.out.println("rpc调用出错,使用熔断器！");
        return "hi,"+name+",sorry,error!";
    }
}
