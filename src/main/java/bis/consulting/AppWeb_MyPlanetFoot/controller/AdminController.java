package bis.consulting.AppWeb_MyPlanetFoot.controller;


import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Admin controller.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = {"/", "/liste"})
    public String home(Model model) {
        Iterable<AdminModel> listeAdmin = adminService.getAdmins();
        model.addAttribute("listeAdmin", listeAdmin);
        return "listeAdmins";
    }

    @GetMapping(value = "/updateAdmin/{id}")
    public String updateAdmin(@PathVariable Integer id, Model model) {
        AdminModel admin = adminService.getAdmin(id);
        model.addAttribute("admin", admin);
        return "modificationAdmin";
    }
    /**
     * Create admin string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "/createAdmin")
    public String createAdmin(Model model) {
        AdminModel admin = new AdminModel();
        model.addAttribute("admin",admin);
        return "login";
    }


    /**
     * Update admin model and view.
     *
     * @param id         the id
     * @param adminModel the admin model
     * @return the model and view
     */
    @PostMapping(value = "/updatedAdmin/{id}")
    public ModelAndView updateAdmin(@PathVariable Integer id, @ModelAttribute("admin") AdminModel adminModel) {
        adminService.updateAdmin(id, adminModel);
        return new ModelAndView("redirect:/admin/liste");
    }

    /**
     * Delete admin model and view.
     *
     * @param id the id
     * @return the model and view
     */
    @PostMapping(value = "/deleteAdmin/{id}")
    public ModelAndView deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return new ModelAndView("redirect:/admin/liste");
    }

}
