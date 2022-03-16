package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.ReviewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
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
    public String topRatedPage(Model model){
        model.addAttribute("categories",this.categoryService.findAll());
        return "topRated";
    }

    @GetMapping("/{id}")
    public String selectedBookPage(Model model, @PathVariable Long id){
        model.addAttribute("categories",this.categoryService.findAll());
        Book selectedBook = bookService.findBookById(id);
        model.addAttribute("selectedBook",selectedBook);
        model.addAttribute("reviews",this.reviewService.findReviewsByBook(selectedBook));
        model.addAttribute("relatedBooks",this.bookService.findFirst10(selectedBook.getCategory()));
        return "bookDetails";

    }

    //allBooks e template samo so listanje knigi
    @GetMapping("/search")
    public String searchAuthorOrTitle(@RequestParam String authorOrTitle, Model model) {
        String x = authorOrTitle;
        model.addAttribute("books",bookService.searchByTitleOrAuthor(authorOrTitle,authorOrTitle));
        return "allBooks";
    }

    @GetMapping("/search/{id}")
    public String searchCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("books",bookService.searchByCategory(category));
        return "allBooks";
    }
}
