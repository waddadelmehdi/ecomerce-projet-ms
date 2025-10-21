package ma.waddad.customerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {
    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;

    private CustomerConfigParams customerConfigParams;

    public ConfigTestRestController(CustomerConfigParams customerConfigParams) {
        this.customerConfigParams = customerConfigParams;
    }
    @GetMapping("/testConfig1")
    public Map<String,String> configTest(){
        return Map.of("p1",p1,"p2",p2);
    };


    @GetMapping("/testConfig2")
    public CustomerConfigParams configTest2(){
        return customerConfigParams;
    };
}
