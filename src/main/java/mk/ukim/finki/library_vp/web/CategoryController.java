package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private final BookServiceImpl bookService;

    public CategoryController(CategoryServiceImpl categoryService, BookServiceImpl bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String allCategories(Model model) {
        model.addAttribute("categories",this.categoryService.findAll());
        return "contact";
    }

//    @GetMapping("/{id}")
//    public String categoryChosen(Model model, @PathVariable Long id) {
//        Category category = categoryService.findCategoryById(id);
//        model.addAttribute("books",this.bookService.searchByCategory(category));
//        return "allBooks";
//    }
}
