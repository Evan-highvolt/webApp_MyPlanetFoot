package bis.consulting.AppWeb_MyPlanetFoot.controller;


import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        Iterable<AdminModel> listeAdmin = adminService.getAdmins();
        model.addAttribute("lisetAdmin", listeAdmin);
        return "home";
    }
}
