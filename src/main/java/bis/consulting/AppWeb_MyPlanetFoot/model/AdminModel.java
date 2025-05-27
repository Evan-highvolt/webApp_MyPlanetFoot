package bis.consulting.AppWeb_MyPlanetFoot.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
public class AdminModel implements UserDetails {

    private Integer idAdm;
    private String nomAdm;
    private String prenomAdm;
    private String emailAdm;
    private String telephoneAdm;
    private String photoPrflAdm;
    private String respoAdm;
    private LocalDate dateAdm;

    private CompteModel compteModel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.getCompteModel().getMdpCpt();
    }

    @Override
    public String getUsername() {
        return this.getEmailAdm();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;

    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;

    }
}
