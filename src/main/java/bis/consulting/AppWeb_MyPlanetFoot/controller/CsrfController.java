package bis.consulting.AppWeb_MyPlanetFoot.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CsrfController {
    @ModelAttribute
    public void csrfToken(CsrfToken token, Model model) {
        model.addAttribute("_csrf", token);
    }

}
