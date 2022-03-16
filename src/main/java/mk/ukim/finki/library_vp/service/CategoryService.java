package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Long id);
    List<Category> findAll();
}
