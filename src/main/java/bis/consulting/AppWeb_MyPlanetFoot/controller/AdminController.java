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
public class AdminController {
    @Autowired
    private AdminService adminService;


    @GetMapping(value = {"/", "/admins"})
    public String home(Model model) {
        Iterable<AdminModel> listeAdmin = adminService.getAdmins();
        model.addAttribute("listeAdmin", listeAdmin);
        return "admins";
    }

    @GetMapping(value = "/createAdmin")
    public String createAdmin(Model model) {
        AdminModel admin = new AdminModel();
        model.addAttribute("admin",admin);
        return "login";
    }

    @PostMapping(value = "/saveAdmin")
    public ModelAndView saveAdmin(@ModelAttribute("admin") final AdminModel adminModel) {
        adminService.saveAdmin(adminModel);
        return new ModelAndView("redirect:/");
    }

}
