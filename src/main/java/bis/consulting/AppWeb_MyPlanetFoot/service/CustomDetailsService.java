package bis.consulting.AppWeb_MyPlanetFoot.service;

import bis.consulting.AppWeb_MyPlanetFoot.model.AdminModel;
import bis.consulting.AppWeb_MyPlanetFoot.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminModel adminModel = adminRepo.findByEmailAdm(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new User(
                adminModel.getEmailAdm(),
                adminModel.getCompteModel().getMdpCpt(),
                Collections.singletonList(new SimpleGrantedAuthority("Admin"))
        );
    }
}
