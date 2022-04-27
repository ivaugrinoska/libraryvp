package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.repository.UserReadBooksRepository;
import mk.ukim.finki.library_vp.service.UserReadBooksService;
import org.springframework.stereotype.Service;


@Service
public class UserReadBooksServiceImpl implements UserReadBooksService {

    private final UserReadBooksRepository userReadBooksRepository;

    public UserReadBooksServiceImpl(UserReadBooksRepository userReadBooksRepository) {
        this.userReadBooksRepository = userReadBooksRepository;
    }

    @Override
    public void deleteAll() {
        this.userReadBooksRepository.deleteAll();
    }

    @Override
    public void deleteAllByUsername(String username) {
        this.userReadBooksRepository.deleteAllByUsername(username);
    }
}
