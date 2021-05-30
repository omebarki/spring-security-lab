package omar.mebarki.security.main;

import omar.mebarki.security.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = SecurityConfig.class)
public class SecurityLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityLabApplication.class, args);
    }

}
