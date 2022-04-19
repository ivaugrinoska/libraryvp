package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.User;

public interface AuthService {

    User login(String username, String password);
}

