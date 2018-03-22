package cn.yjxxclub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-3-15 下午3:36
 */
@RestController
public class TestController {

    @Value("${foo}")
    String foo;
    @RequestMapping(value = "/hi")
    public String hi(){
        return "Hello World!" + foo;
    }
}

