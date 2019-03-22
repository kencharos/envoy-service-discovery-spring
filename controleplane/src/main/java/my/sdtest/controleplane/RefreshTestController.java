package my.sdtest.controleplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RefreshTestController {

    @Autowired SomethingValue v;
    @Autowired SensitiveValue sv;

    @Value("${fromsecret}") String fromsecret;

    @GetMapping("/something")
    public String somethine() {
        return v.getValue() + ":" + v.getCommon();
    }
    @GetMapping("/sensitive")
    public String sensitive() {
        return sv.getUser() + "/" + sv.getPass() + "/" + fromsecret;
    }

}
