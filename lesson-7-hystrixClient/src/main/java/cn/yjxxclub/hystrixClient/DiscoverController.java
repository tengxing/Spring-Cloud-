package cn.yjxxclub.hystrixClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-6 上午10:30
 */
@RestController
public class DiscoverController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.sayHello(name);
    }
}
