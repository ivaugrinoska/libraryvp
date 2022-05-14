package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.library_vp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.library_vp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);

    }

    @Override
    public User editProfile(String username, String name, String surname) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        user.setName(name);
        user.setSurname(surname);

        return this.userRepository.save(user);
    }
}
