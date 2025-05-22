package bis.consulting.AppWeb_MyPlanetFoot.service;

import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.repo.AdminRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public Iterable<AdminModel>getAdmins() {
        return  adminRepo.getAdmins();
    }

    public AdminModel getAdmin(int id) {
        return adminRepo.getAdmin(id);
    }

    public AdminModel saveAdmin(AdminModel admin) {
        AdminModel savedAdmin;

        admin.setPrenomAdm(admin.getPrenomAdm());
        admin.setNomAdm(admin.getNomAdm().toUpperCase());
        admin.setEmailAdm(admin.getEmailAdm());
        admin.setDateAdm(admin.getDateAdm());
        admin.setTelephoneAdm(admin.getTelephoneAdm());

        savedAdmin = adminRepo.creatAdmin(admin);
        return savedAdmin;
    }

    public AdminModel updateAdmin(Integer id, AdminModel updatedAdmin) {
        AdminModel existingAdmin = adminRepo.getAdmin(id);
        existingAdmin.setPrenomAdm(updatedAdmin.getPrenomAdm());
        existingAdmin.setNomAdm(updatedAdmin.getNomAdm().toUpperCase());
        existingAdmin.setEmailAdm(updatedAdmin.getEmailAdm());
        existingAdmin.setDateAdm(updatedAdmin.getDateAdm());
        existingAdmin.setTelephoneAdm(updatedAdmin.getTelephoneAdm());
        return adminRepo.updateAdmin(existingAdmin);

    }

    public void deleteAdmin(Integer id) {
        adminRepo.deleteAdmin(id);
    }


}
