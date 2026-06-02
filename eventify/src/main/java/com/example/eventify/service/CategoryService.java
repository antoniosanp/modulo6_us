package com.example.eventify.service;

import com.example.eventify.model.Category;
import com.example.eventify.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategoriesForSelection() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
