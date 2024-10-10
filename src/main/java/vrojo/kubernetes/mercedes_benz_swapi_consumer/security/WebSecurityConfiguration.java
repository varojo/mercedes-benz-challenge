package vrojo.kubernetes.mercedes_benz_swapi_consumer.security;

import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.properties.UserPasswordProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.*;

//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * @author valentin.rojo
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfiguration {

    public static final AntPathRequestMatcher[] WHITE_LIST_URLS =
            {new AntPathRequestMatcher("/swapi-consumer/doc/**"), // TODO: inyect this as env var
             new AntPathRequestMatcher("/favicon.ico"),
             new AntPathRequestMatcher("/actuator/**")};

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web)
                ->
                web
                .ignoring()
                .requestMatchers(WHITE_LIST_URLS);
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(request -> { // TODO: configure CORS policy if security requirements demand so
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(List.of("*"));
                configuration.setAllowedHeaders(List.of("*"));
                return configuration;
            }))
            .csrf((csrf) -> csrf.disable() // TODO: configure CSRF policy if security requirements demand so. Like with .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers(WHITE_LIST_URLS).permitAll()
                                                            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserPasswordProperties userPasswordProperties(){
        return new UserPasswordProperties();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

        // AUTHORITIES
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // USERS
        List<UserDetails> userDetails = new LinkedList<>();
        List<String> userPasswordList = Arrays.stream(userPasswordProperties().getHttpBasicUsers().split(",")).toList();
        for (String userPassword : userPasswordList) {

            String[] userAndPassword = userPassword.split(":");
            String user = userAndPassword[0];
            String password = userAndPassword[1];

            userDetails.add(new User(user,
                                     passwordEncoder().encode(password),
                                     true, true, true, true,
                                     authorities));
        }

       return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}