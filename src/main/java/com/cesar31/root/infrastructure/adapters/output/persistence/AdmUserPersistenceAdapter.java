package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.AdmUser;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.AdmUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AdmUserPersistenceAdapter implements UserOutputPort {

    private final AdmUserRepository admUserRepository;

    public AdmUserPersistenceAdapter(AdmUserRepository admUserRepository) {
        this.admUserRepository = admUserRepository;
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return admUserRepository.findById(userId)
                // TODO: use mapper here
                .map(admUser -> new User(admUser.getUserId(), admUser.getEmail(), admUser.getPassword(), admUser.getEntryDate()));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return admUserRepository.findByEmail(email)
                // TODO: use mapper here
                .map(admUser -> new User(admUser.getUserId(), admUser.getEmail(), admUser.getPassword(), admUser.getEntryDate()));
    }

    @Override
    public User save(User user) {
        var admUser = new AdmUser();
        // TODO: use mapper here
        admUser.setUserId(user.getUserId());
        admUser.setEmail(user.getEmail());
        admUser.setPassword(user.getPassword());
        admUser.setEntryDate(user.getEntryDate());
        var createdUser = admUserRepository.save(admUser);

        // TODO: user mapper here
        return new User(
                createdUser.getUserId(),
                createdUser.getEmail(),
                createdUser.getPassword(),
                createdUser.getEntryDate()
        );
    }
}
