package bis.consulting.AppWeb_MyPlanetFoot.service;

import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.repo.AdminRepo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * The type Admin service.
 */
@Data
@Slf4j
@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    /**
     * Get admins iterable.
     *
     * @return the iterable
     */
    public Iterable<AdminModel>getAdmins() {
        try {
            return adminRepo.getAdmins();
        } catch (RestClientException e) {
            log.error("Erreur lors de la récupération des admins : {}", e.getMessage());
            return Collections.emptyList(); // retourne une liste vide plutôt que null
        }
    }

    /**
     * Gets admin.
     *
     * @param id the id
     * @return the admin
     */
    public AdminModel getAdmin(Integer id) {
        AdminModel admin =  adminRepo.getAdmin(id);
        if (admin == null) {
            throw new NoSuchElementException("Aucun administrateur trouvé pour l'ID : " + id);
        }
        return admin;
    }

    /**
     * Save admin model.
     *
     * @param admin the admin
     * @return the admin model
     */
    public AdminModel saveAdmin(AdminModel admin) {
        if (admin == null) {
            throw new IllegalArgumentException("L'objet admin ne peut pas être nul");
        }
        if (admin.getNomAdm() == null || admin.getNomAdm().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'administrateur est requis");
        }
        if (admin.getEmailAdm() == null ) { //|| !admin.getEmailAdm().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
            throw new IllegalArgumentException("L'adresse email de l'administrateur est invalide");
        }
        if (admin.getDateAdm() == null) {
            throw new IllegalArgumentException("La date de l'administrateur est requis");
        }

        admin.setPrenomAdm(admin.getPrenomAdm().trim());
        admin.setNomAdm(admin.getNomAdm().toUpperCase().trim());
        admin.setEmailAdm(admin.getEmailAdm().trim());
        admin.setDateAdm(admin.getDateAdm());
        admin.setTelephoneAdm(admin.getTelephoneAdm().trim());

        try {
            return adminRepo.creatAdmin(admin);

        } catch (Exception e) {
            log.error("Erreur lors de la sauvegarde de l'admin : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible d'enregistrer l'administrateur. Merci de réessayer.");
        }
    }

    /**
     * Update admin admin model.
     *
     * @param id           the id
     * @param updatedAdmin the updated admin
     * @return the admin model
     */
    public AdminModel updateAdmin(Integer id, AdminModel updatedAdmin) {
        AdminModel existingAdmin = adminRepo.getAdmin(id);
        if (existingAdmin == null) {
            throw new NoSuchElementException("Aucun administrateur trouvé avec l'ID : " + id);
        }

        existingAdmin.setPrenomAdm(
                updatedAdmin.getPrenomAdm() != null ? updatedAdmin.getPrenomAdm().trim() : existingAdmin.getPrenomAdm()
        );
        existingAdmin.setNomAdm(
                updatedAdmin.getNomAdm() != null ? updatedAdmin.getNomAdm().trim() : existingAdmin.getNomAdm()
        );
        existingAdmin.setEmailAdm(
                updatedAdmin.getEmailAdm() != null ? updatedAdmin.getEmailAdm().trim() : existingAdmin.getEmailAdm()
        );
        existingAdmin.setDateAdm(
                updatedAdmin.getDateAdm() != null ? updatedAdmin.getDateAdm() : existingAdmin.getDateAdm()
        );
        existingAdmin.setTelephoneAdm(
                updatedAdmin.getTelephoneAdm() != null ? updatedAdmin.getTelephoneAdm() : existingAdmin.getTelephoneAdm()
        );
        return adminRepo.updateAdmin(existingAdmin);

    }

    /**
     * Delete admin.
     *
     * @param id the id
     */
    public void deleteAdmin(Integer id) {
        AdminModel existingAdmin = adminRepo.getAdmin(id);
        if (existingAdmin == null) {
            throw new NoSuchElementException("Aucun administrateur trouvé avec l'ID : " + id);
        }
        adminRepo.deleteAdmin(id);
    }


}
