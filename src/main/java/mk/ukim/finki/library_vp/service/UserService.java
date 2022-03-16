package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User findUserByUsername(String username);
}