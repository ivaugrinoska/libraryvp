package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String allCategories(Model model) {
        model.addAttribute("categories",this.categoryService.findAll());
        return "contact";
    }
}
