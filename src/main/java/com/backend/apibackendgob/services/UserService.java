package com.backend.apibackendgob.services;

import com.backend.apibackendgob.models.User;
import com.backend.apibackendgob.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String email, String password) {
        // Busca el usuario por su email
        User user = userRepository.findByEmail(email);

        // Si el usuario existe y la contraseña coincide, autenticamos correctamente
        return user != null && user.getPassword().equals(password);
    }
}