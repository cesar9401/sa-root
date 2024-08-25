package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.AdmUser;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.AdmUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdmUserPersistenceAdapter implements UserOutputPort {

    private final AdmUserRepository admUserRepository;

    public AdmUserPersistenceAdapter(AdmUserRepository admUserRepository) {
        this.admUserRepository = admUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return admUserRepository.findByEmail(email)
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
        admUserRepository.save(admUser);
    }
}
