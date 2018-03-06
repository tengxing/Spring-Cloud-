package cn.yjxxclub.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-6 下午2:42
 */
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this application name is:"+applicationName+",port:"+port;
    }
}
