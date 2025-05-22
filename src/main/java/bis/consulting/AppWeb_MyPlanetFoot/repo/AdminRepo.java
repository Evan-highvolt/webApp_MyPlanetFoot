package bis.consulting.AppWeb_MyPlanetFoot.repo;

import bis.consulting.AppWeb_MyPlanetFoot.config.CustomProperties;
import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Slf4j
@Component
public class AdminRepo {
    @Autowired
    private CustomProperties props;

    public Iterable<AdminModel> getAdmins() {
        String baseURL = props.getApiURL();
        String getAdminURL = baseURL + "/admin";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Iterable<AdminModel>> response = restTemplate.exchange(
                    getAdminURL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            log.debug("Get Admins Call {}", response.getStatusCode());

            if (response.getBody() != null) {
                return response.getBody();
            } else {
                log.warn("La réponse de l'API est vide");
                return new ArrayList<>();
            }

        } catch (Exception e) {
            log.error("Erreur lors de l'appel à l'API des admins : {}", e.getMessage());
            return new ArrayList<>();
        }
    }


    public AdminModel getAdmin(Integer id) {
        String baseURL = props.getApiURL();
        String getAdminURL = baseURL + "/admin/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AdminModel> response = restTemplate.exchange(
                getAdminURL,
                HttpMethod.GET,
                null,
                AdminModel.class
        );
        log.debug("Get Admin Call {}", response.getStatusCode());
        return response.getBody();
    }

    public AdminModel creatAdmin(AdminModel admin) {
        String baseURL = props.getApiURL();
        String createAdminURL = baseURL + "/admin";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AdminModel> request = new HttpEntity<>(admin);
        ResponseEntity<AdminModel> response = restTemplate.exchange(
                createAdminURL,
                HttpMethod.POST,
                request,
                AdminModel.class
        );
        log.debug("Create Admin Call {}", response.getStatusCode());
        return response.getBody();
    }

    public AdminModel updateAdmin(AdminModel admin) {
        String baseURL = props.getApiURL();
        String updateAdminURL = baseURL + "/admin/" + admin.getIdAdm();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AdminModel> request = new HttpEntity<>(admin);
        ResponseEntity<AdminModel> response = restTemplate.exchange(
                updateAdminURL,
                HttpMethod.PUT,
                request,
                AdminModel.class
        );
        log.debug("Update Admin Call {}", response.getStatusCode());
        return response.getBody();
    }


    public void deleteAdmin(Integer id) {
        String baseURL = props.getApiURL();
        String deleteAdminURL = baseURL + "/admin/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteAdminURL);

    }



}
