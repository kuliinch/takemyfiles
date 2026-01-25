package kuliandreed.simp_file_sharing_monolith;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Profile("!development")
@EnableWebSecurity
public class SecurityConfig {

  // protected void configure(HttpSecurity http) {
  // http
  // //.authorizeRequests()
  // .anyRequest().authenticated();
  // http
  // .formLogin()
  // .loginPage("/login")
  // .permitAll()
  // .and()
  // .logout()
  // .permitAll();
  // }
}
