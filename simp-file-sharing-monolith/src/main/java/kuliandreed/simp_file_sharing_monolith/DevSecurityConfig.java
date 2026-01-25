package kuliandreed.simp_file_sharing_monolith;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Profile("development")
public class DevSecurityConfig {

  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(a -> {
      a.anyRequest().permitAll();
      // a.csrf().disbale();
    });
  }
}
