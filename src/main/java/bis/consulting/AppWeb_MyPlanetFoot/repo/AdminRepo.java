package bis.consulting.AppWeb_MyPlanetFoot.repo;

import bis.consulting.AppWeb_MyPlanetFoot.config.CustomProperties;
import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AdminRepo {
    @Autowired
    private CustomProperties props;

    public Iterable<AdminModel> getAdmins() {
        String baseURL = props.getApiURL();
        String getAdminURL = baseURL + "/admins";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<AdminModel>> response = restTemplate.exchange(
                getAdminURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        log.debug("Get Admins Call " + response.getStatusCode());
        return response.getBody();
    }


}
