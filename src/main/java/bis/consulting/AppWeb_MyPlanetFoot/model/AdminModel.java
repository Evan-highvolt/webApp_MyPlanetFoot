package bis.consulting.AppWeb_MyPlanetFoot.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminModel {
    private Integer idAdm;
    private String nomAdm;
    private String prenomAdm;
    private String emailAdm;
    private String telephoneAdm;
    private String photoPrflAdm;
    private String respoAdm;
    private LocalDate dateAdm;
}
