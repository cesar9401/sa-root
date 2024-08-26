package com.cesar31.root.infrastructure.config.bean;

import com.cesar31.root.SaRootApplication;
import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.application.ports.output.UserOutputPort;
import com.cesar31.root.domain.service.UserByEmailService;
import com.cesar31.root.domain.service.UserService;
import com.cesar31.root.application.ports.input.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SaRootApplication.class)
public class BeanConfiguration {

    @Bean
    UserUseCase userService(final UserOutputPort userOutputPort) {
        return new UserService(userOutputPort);
    }

    @Bean
    UserByEmailUseCase userByEmailUseCase(final UserByEmailPort userByEmailPort) {
        return new UserByEmailService(userByEmailPort);
    }
}
