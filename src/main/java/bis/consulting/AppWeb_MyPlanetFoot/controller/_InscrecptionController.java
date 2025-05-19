package bis.consulting.AppWeb_MyPlanetFoot.controller;

import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class _InscrecptionController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/inscription")
    public String pageInscricption(Model model) {
        model.addAttribute("admin", new AdminModel());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String processSignUp(@ModelAttribute("admin") AdminModel admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    @PostMapping(value = "/saveAdmin")
    public ModelAndView saveAdmin(@ModelAttribute("admin") final AdminModel adminModel) {
        adminService.saveAdmin(adminModel);
        return new ModelAndView("redirect:/");
    }


}
