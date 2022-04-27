package mk.ukim.finki.library_vp.service;

import org.springframework.stereotype.Service;


public interface UserReadBooksService {
    void deleteAll();
    void deleteAllByUsername(String username);
}
