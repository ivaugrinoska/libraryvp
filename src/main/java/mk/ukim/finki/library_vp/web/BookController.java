package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.ReviewServiceImpl;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

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
    public String topRatedPage(Model model){
        model.addAttribute("categories",this.categoryService.findAll());
        model.addAttribute("books",this.bookService.findTopRated());
        model.addAttribute("bodyContent", "topRated");
        return "master-template";
    }

    @GetMapping("/mostRented")
    public String mostRatedPage(Model model){
        model.addAttribute("categories",this.categoryService.findAll());
        //model.addAttribute("books",this.bookService.findTopRated());
        model.addAttribute("bodyContent", "mostRented");
        return "master-template";
    }


    @GetMapping("/{id}")
    public String selectedBookPage(Model model, @PathVariable Long id){
        model.addAttribute("categories",this.categoryService.findAll());
        Book selectedBook = bookService.findBookById(id);
        model.addAttribute("selectedBook",selectedBook);
        model.addAttribute("reviews",this.reviewService.findReviewsByBook(selectedBook));
        model.addAttribute("relatedBooks",this.
                bookService.findFirst10(selectedBook.getCategory()));
        model.addAttribute("bodyContent", "bookDetails");
        return "master-template";

    }

    //allBooks e template samo so listanje knigi, mu predavame model spored request-ot

    @GetMapping("/search")
    public String searchAuthorOrTitle(@RequestParam String authorOrTitle, Model model) {
        String x = authorOrTitle;
        model.addAttribute("categories",this.categoryService.findAll());
        model.addAttribute("books",bookService.searchByTitleOrAuthor(authorOrTitle,authorOrTitle));
        return "allBooks";
    }

    @GetMapping("/search/{id}")
    public String searchCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("books",bookService.searchByCategory(category));
        model.addAttribute("categories",this.categoryService.findAll());
        model.addAttribute("bodyContent", "allBooks");
        return "master-template";
    }

    @PostMapping("/addReview")
    public String addReview(@RequestParam String review, @RequestParam Long bookId,Authentication authentication)
    {
       User user = (User) authentication.getPrincipal();
        this.reviewService.addNewReview(review,bookId,user);
        return "redirect:/books/"+bookId;
    }

    @PostMapping("/mark")
    public String markAsRead(@RequestParam Long bookId,Authentication authentication)
    {
        User user = (User) authentication.getPrincipal();
        this.bookService.markAsRead(bookId,user);
        return "redirect:/books/"+bookId;
    }
}
