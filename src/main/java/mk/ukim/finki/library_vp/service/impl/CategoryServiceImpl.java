package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.library_vp.repository.CategoryRepository;
import mk.ukim.finki.library_vp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException());
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

}
