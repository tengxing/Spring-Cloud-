package cn.yjxxclub.hystrixClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return restTemplate.getForObject("http://helloService/hello?name="+name,String.class);
    }

    /**
     * 熔断器--hiError
     * @param name
     * @return
     */
    public String hiError(String name){
        System.out.println("rpc调用出错,使用熔断器！");
        return "hi,"+name+",sorry,error!";
    }

}
