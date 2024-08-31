package com.cesar31.root.infrastructure.config.bean;

import com.cesar31.root.SaRootApplication;
import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.application.ports.output.UserOutputPort;
import com.cesar31.root.application.mapper.UserMapper;
import com.cesar31.root.application.service.UserByEmailService;
import com.cesar31.root.application.service.UserService;
import com.cesar31.root.application.ports.input.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SaRootApplication.class)
public class BeanConfiguration {

    @Bean
    UserUseCase userService(final UserOutputPort userOutputPort, final PasswordEncoderPort passwordEncoderPort, final UserMapper mapper) {
        return new UserService(userOutputPort, passwordEncoderPort, mapper);
    }

    @Bean
    UserByEmailUseCase userByEmailUseCase(final UserByEmailPort userByEmailPort) {
        return new UserByEmailService(userByEmailPort);
    }
}
