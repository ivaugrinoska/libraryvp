package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/","books"})
public class HomeController {

    private final CategoryServiceImpl categoryService;
    private final BookServiceImpl bookService;

    public HomeController(CategoryServiceImpl categoryService, BookServiceImpl bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("categories",this.categoryService.findAll());
        model.addAttribute("books",this.bookService.findAll());
        model.addAttribute("bodyContent", "allBooks");
        return "master-template";
    }

    @GetMapping("/help")
    public String helpPage(Model model) {
        model.addAttribute("categories", this.categoryService.findAll());
        return "contact";
    }
}
