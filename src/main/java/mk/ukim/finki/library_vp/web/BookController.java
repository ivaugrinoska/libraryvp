package mk.ukim.finki.library_vp.web;


import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.ReviewServiceImpl;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;
    private final CategoryService categoryService;
    private final ReviewServiceImpl reviewService;

    public BookController(BookServiceImpl bookService, CategoryService categoryService, ReviewServiceImpl reviewService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.reviewService = reviewService;

    }

    @GetMapping("/topRated")
    public String topRatedPage(Model model) {
        model.addAttribute("categories", this.categoryService.findAll());
        model.addAttribute("books", this.bookService.findTopRated());
        model.addAttribute("bodyContent", "topRated");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String selectedBookPage(Model model, @PathVariable Long id) {
        model.addAttribute("categories", this.categoryService.findAll());
        Book selectedBook = bookService.findBookById(id);
        model.addAttribute("selectedBook", selectedBook);
        model.addAttribute("reviews", this.reviewService.findReviewsByBook(selectedBook));
        model.addAttribute("relatedBooks",this.
                bookService.findFirst3(selectedBook.getCategory()));
        model.addAttribute("bodyContent", "bookDetails");
        return "master-template";

    }


    @GetMapping("/search")
    public String searchAuthorOrTitle(@RequestParam String authorOrTitle, Model model) {
        String x = authorOrTitle;
        model.addAttribute("categories", this.categoryService.findAll());
        model.addAttribute("books", bookService.searchByTitleOrAuthor(authorOrTitle));
        model.addAttribute("bodyContent", "searchBooks");
        return "master-template";
    }

    @GetMapping("/search/{id}")
    public String searchCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("books", bookService.searchByCategory(category));
        model.addAttribute("categories", this.categoryService.findAll());
        model.addAttribute("bodyContent", "searchBooks");
        return "master-template";
    }

    @PostMapping("/addReview")
    public String addReview(@RequestParam String review, @RequestParam Long bookId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.reviewService.addNewReview(review, bookId, user);
        return "redirect:/books/" + bookId;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public String addBookPage(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        //List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("categories", categories);
        //model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent", "addNewBook");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "editBook");
            return "master-template";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @PostMapping("/add")
    public String saveBook(
            @RequestParam(required = false) String id,
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam Integer stock,
            @RequestParam Float rating,
            @RequestParam String description,
            @RequestParam String url,
            @RequestParam Long category) {

        this.bookService.addNewBook(name, author, stock, rating, description, url, category);

        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(
            @RequestParam(required = false) String id,
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam Integer stock,
            @RequestParam Float rating,
            @RequestParam String description,
            @RequestParam String url,
            @RequestParam Long category) {

        this.bookService.editBook(Long.parseLong(id), name, author, stock, rating, description, url, category);

        return "redirect:/books";
    }

    @PostMapping("/rating/{bookId}")
    public String rate(@PathVariable Long bookId, @RequestParam float ratingNum) {
        this.bookService.rating(ratingNum, bookId);
        return "redirect:/books/" + bookId;
    }

}
