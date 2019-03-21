package my.sdtest.controleplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTestController {

    @Autowired SomethingValue v;

    @GetMapping("/something")
    public String somethine() {
        return v.getValue() + ":" + v.getCommon();
    }

}
