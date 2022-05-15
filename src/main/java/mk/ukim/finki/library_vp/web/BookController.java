package mk.ukim.finki.library_vp.web;

import com.sun.istack.NotNull;
import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.ReviewServiceImpl;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.apache.coyote.Request;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;
    private final CategoryService categoryService;
    private final ReviewServiceImpl reviewService;
    private final UserServiceImpl userService;

    public BookController(BookServiceImpl bookService, CategoryService categoryService, ReviewServiceImpl reviewService, UserServiceImpl userService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.reviewService = reviewService;
        this.userService = userService;
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
        model.addAttribute("relatedBooks", this.
                bookService.findFirst10(selectedBook.getCategory()));
        model.addAttribute("bodyContent", "bookDetails");
        return "master-template";

    }

    //allBooks e template samo so listanje knigi, mu predavame model spored request-ot

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
        //return "bookDetails";
    }

    @PostMapping("/addReview")
    public String addReview(@RequestParam String review, @RequestParam Long bookId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.reviewService.addNewReview(review, bookId, user);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/mark")
    public String markAsRead(@RequestParam Long bookId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.bookService.markAsRead(bookId, user);
        return "redirect:/books/" + bookId;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

 /*   @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id, Model model){
        if (this.bookService.findById(id).isPresent()){
            Book book = this.bookService.findById(id).get();
            model.addAttribute("selectedBook", book);
            return "addNewBook";
        }
        return "redirect:/books?error=BookNotFoundException";
    }*/

//    @GetMapping("/edit-book/{id}")
//    public String editBook(@PathVariable Long id, Model model) {
//        if (this.bookService.findById(id).isPresent()) {
//            Book book = this.bookService.findById(id).get();
//            List<Category> categories = this.categoryService.listCategories();
//            model.addAttribute("categories", categories);
//            model.addAttribute("categories", categories);
//
//            return "addNewBook";
//        }
//        return "redirect:/books?error=BookNotFoundException";
//    }
//
//    @GetMapping("/add-new-book")
//    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
//    public String addNewBook() {
//        return "addNewBook";
//    }
//
//    @PostMapping("/add-book")
//    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
//    public String addBook(@RequestParam(required = false) Long id, HttpServletRequest req, Model model) {
//        String bookName = req.getParameter("bookName");
//        int bookStock = Integer.parseInt(req.getParameter("bookStock"));
//        float bookRating = Float.parseFloat(req.getParameter("bookRating"));
//        String bookDescription = req.getParameter("bookDescription");
//        String bookUrl = req.getParameter("bookUrl");
//        String bookAuthor = req.getParameter("bookAuthor");
//
//        List<Category> categories = this.categoryService.listCategories();
//        model.addAttribute("categories", categories);
//
//        if (id != null)
//            this.bookService.editBook(id, bookName, bookAuthor, bookStock, bookRating, bookDescription, bookUrl);
//        else
//            this.bookService.addNewBook(bookName, bookAuthor, bookStock, bookRating, bookDescription, bookUrl);
//
//        return "redirect:/books";
//
//    }

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
            //List<Manufacturer> manufacturers = this.manufacturerService.listAll();
            model.addAttribute("categories", categories);
            //model.addAttribute("manufacturers", manufacturers);
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
