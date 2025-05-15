package bis.consulting.AppWeb_MyPlanetFoot.service;

import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.repo.AdminRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public Iterable<AdminModel>getAdmins() {
        return  adminRepo.getAdmins();
    }
}
