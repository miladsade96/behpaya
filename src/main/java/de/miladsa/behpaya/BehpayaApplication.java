package de.miladsa.behpaya;

import de.miladsa.behpaya.configuration.CustomAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
@EnableJpaAuditing
public class BehpayaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BehpayaApplication.class, args);
    }

    @Bean
    public AuditorAware<User> auditorAware() {
        return new CustomAuditorAware();
    }

}
