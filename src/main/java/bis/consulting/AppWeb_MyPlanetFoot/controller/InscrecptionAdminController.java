package bis.consulting.AppWeb_MyPlanetFoot.controller;

import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.model.CompteModel;
import bis.consulting.AppWeb_MyPlanetFoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InscrecptionAdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/inscriptionAdmin")
    public String pageInscricption(Model model) {
        AdminModel adminModel = new AdminModel();
        adminModel.setCompteModel(new CompteModel());
        model.addAttribute("admin", adminModel);
        return "inscriptionAdmin";
    }

    @PostMapping("/inscriptionAdmin")
    public String processSignUp(@ModelAttribute("admin") AdminModel admin) {
        admin.setEmailAdm(admin.getCompteModel().getLoginCpt());
        adminService.saveAdmin(admin);
        return "redirect:/admin/liste";
    }


}
