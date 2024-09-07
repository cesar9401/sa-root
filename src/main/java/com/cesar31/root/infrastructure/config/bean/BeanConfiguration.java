package com.cesar31.root.infrastructure.config.bean;

import com.cesar31.root.SaRootApplication;
import com.cesar31.root.application.mapper.ClientMapper;
import com.cesar31.root.application.ports.input.ClientUseCase;
import com.cesar31.root.application.ports.input.RoleUseCase;
import com.cesar31.root.application.ports.input.RolesByUserIdUseCase;
import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import com.cesar31.root.application.ports.output.ClientOutputPort;
import com.cesar31.root.application.ports.output.CurrentUserOutputPort;
import com.cesar31.root.application.ports.output.ExistsOrgOutputPort;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.application.ports.output.RoleByUserIdPort;
import com.cesar31.root.application.ports.output.RoleOutputPort;
import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.application.ports.output.EmployeeOutputPort;
import com.cesar31.root.application.mapper.EmployeeMapper;
import com.cesar31.root.application.service.ClientService;
import com.cesar31.root.application.service.RoleService;
import com.cesar31.root.application.service.RolesByUserService;
import com.cesar31.root.application.service.UserByEmailService;
import com.cesar31.root.application.service.EmployeeService;
import com.cesar31.root.application.ports.input.EmployeeUseCase;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = SaRootApplication.class)
public class BeanConfiguration {

    @Bean
    EmployeeUseCase userService(
            final EmployeeOutputPort employeeOutputPort,
            final RoleOutputPort roleOutputPort,
            final PasswordEncoderPort passwordEncoderPort,
            final EmployeeMapper mapper,
            final CurrentUserOutputPort currentUserOutputPort,
            final ExistsOrgOutputPort existsOrgOutputPort
    ) {
        return new EmployeeService(
                employeeOutputPort,
                roleOutputPort,
                passwordEncoderPort,
                mapper,
                currentUserOutputPort,
                existsOrgOutputPort
        );
    }

    @Bean
    ClientUseCase clientService(
            final ClientOutputPort clientOutputPort,
            final RoleOutputPort roleOutputPort,
            final PasswordEncoderPort passwordEncoderPort,
            final ClientMapper mapper
    ) {
        return new ClientService(clientOutputPort, roleOutputPort, passwordEncoderPort, mapper);
    }

    @Bean
    RoleUseCase roleService(final RoleOutputPort roleOutputPort) {
        return new RoleService(roleOutputPort);
    }

    @Bean
    UserByEmailUseCase userByEmailUseCase(final UserByEmailPort userByEmailPort) {
        return new UserByEmailService(userByEmailPort);
    }

    @Bean
    RolesByUserIdUseCase rolesByUserIdUseCase(final RoleByUserIdPort rolesByUserIdPort) {
        return new RolesByUserService(rolesByUserIdPort);
    }

    @Bean
    @LoadBalanced
    public RestTemplate template() {
        return new RestTemplate();
    }
}
