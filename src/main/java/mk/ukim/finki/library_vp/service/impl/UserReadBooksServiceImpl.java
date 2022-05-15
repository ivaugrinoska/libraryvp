package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.*;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.repository.UserReadBooksRepository;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.UserReadBooksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserReadBooksServiceImpl implements UserReadBooksService {

    private final UserReadBooksRepository userReadBooksRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserReadBooksServiceImpl(UserReadBooksRepository userReadBooksRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.userReadBooksRepository = userReadBooksRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public User addBookToProfile(String username, Long bookId) {
        User user = this.userRepository.findUserByUsername(username);
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        if(user.getReadBooks()
                .stream().filter(i -> i.getId().equals(bookId))
                .collect(Collectors.toList()).size() > 0)
            throw new BookAlreadyInProfileException(bookId, username);
        book = this.bookRepository.save(book);
        user.getReadBooks().add(book);
        return this.userRepository.save(user);
    }

    @Override
    public List<Book> listAllBooksInProfile(String username) {
        if(!this.userRepository.findByUsername(username).isPresent())
            throw new UserNotFoundException(username);
        return this.userRepository.findByUsername(username).get().getReadBooks();

    }


}
