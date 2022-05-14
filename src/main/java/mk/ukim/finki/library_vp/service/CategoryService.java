package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category findCategoryById(Long id);
    List<Category> findAll();

    List<Category> listCategories();
}
