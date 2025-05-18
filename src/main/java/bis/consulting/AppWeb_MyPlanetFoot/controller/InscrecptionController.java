package bis.consulting.AppWeb_MyPlanetFoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InscrecptionController {

    @GetMapping("/inscription")
    public String pageInscricption() {
        return "inscription";

    }

}
