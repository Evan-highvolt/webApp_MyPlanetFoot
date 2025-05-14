package bis.consulting.AppWeb_MyPlanetFoot.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bis.consulting.appweb")
@Data
public class CustomProperties {
    private String apiURL;
}
