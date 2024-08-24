package com.cesar31.root.infrastructure.configuration;

import com.cesar31.root.SaRootApplication;
import com.cesar31.root.domain.repository.UserRepository;
import com.cesar31.root.domain.service.DomainUserService;
import com.cesar31.root.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SaRootApplication.class)
public class BeanConfiguration {

    @Bean
    UserService userService(final UserRepository userRepository) {
        return new DomainUserService(userRepository);
    }
}
