package bis.consulting.AppWeb_MyPlanetFoot.config;

import bis.consulting.AppWeb_MyPlanetFoot.service.CustomDetailsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;


@Configuration
@EnableWebSecurity
@Data
public class SecurityConfig {

    @Autowired
    private CustomDetailsService customDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()
                        )
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                )
                // Sécurité des headers
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "default-src 'self'; " +
                                        "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; " +
                                        "script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; " +
                                        "img-src 'self' data:; " +
                                        "object-src 'none'; " +
                                        "frame-ancestors 'self';"
                                )
                        )
                        .frameOptions(frame -> frame.sameOrigin())
                )

                // Autorisation des accès
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers(
                                "/login",
                                "/inscriptionAdmin",
                                "/styles/**",
                                "/js/**",
                                "/images/**",
                                "/webjars/**"
//                                "/admin/liste"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // Login personnalisé avec formulaire
                .formLogin(form -> form
                        .loginPage("/login") // la page de login Thymeleaf
                        .defaultSuccessUrl("/admin/liste", true)
                        .permitAll()
                )

                // Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                )

        ;

        return http.build();


    }


}
