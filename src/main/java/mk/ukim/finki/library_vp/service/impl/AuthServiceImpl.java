package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.library_vp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findUserByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
