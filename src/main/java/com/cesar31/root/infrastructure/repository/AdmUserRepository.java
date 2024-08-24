package com.cesar31.root.infrastructure.repository;

import com.cesar31.root.domain.User;
import com.cesar31.root.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdmUserRepository implements UserRepository {

    private final JpaAdmUserRepository jpaAdmUserRepository;

    public AdmUserRepository(JpaAdmUserRepository jpaAdmUserRepository) {
        this.jpaAdmUserRepository = jpaAdmUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaAdmUserRepository.findByEmail(email)
                // TODO: use mapper here
                .map(admUser -> new User(admUser.getEmail(), admUser.getPassword(), admUser.getEntryDate()));
    }

    @Override
    public void save(User user) {
        var admUser = new AdmUser();
        // TODO: use mapper here
        admUser.setEmail(user.getEmail());
        admUser.setPassword(user.getPassword());
        admUser.setEntryDate(user.getEntryDate());
        jpaAdmUserRepository.save(admUser);
    }
}
