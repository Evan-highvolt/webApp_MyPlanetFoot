package bis.consulting.AppWeb_MyPlanetFoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class profileController {
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("titre","Profile Admin");
        return "profile";
    }

}
