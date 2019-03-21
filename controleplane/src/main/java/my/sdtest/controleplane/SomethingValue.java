package my.sdtest.controleplane;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "something")
public class SomethingValue {

    private String value;

    private String common;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}
