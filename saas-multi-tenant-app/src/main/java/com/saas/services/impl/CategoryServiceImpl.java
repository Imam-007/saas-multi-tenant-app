package com.saas.services.impl;

import com.saas.entities.Category;
import com.saas.mappers.CategoryMapper;
import com.saas.repositories.CategoryRepository;
import com.saas.requests.CategoryRequest;
import com.saas.responses.CategoryResponse;
import com.saas.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(final CategoryRequest request) {
        // check category already exists
        checkIfCategoryExistsByName(request.getName());

        final Category category = categoryMapper.toEntity(request);
        this.categoryRepository.save(category);

    }

    @Override
    public void update(final String id, final CategoryRequest request) {

        final Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.debug("Category not found with id {}", id);
            throw new EntityNotFoundException("Category not found with id " + id);
        }

        final Category categoryToUpdate = category.get();

        // check if category already exists
        if (!categoryToUpdate.getName().equals(request.getName())) {
            checkIfCategoryExistsByName(request.getName());
        }

        final Category categoryUpdated = categoryMapper.toEntity(request);
        categoryUpdated.setId(id);
        this.categoryRepository.save(categoryUpdated);
    }

    @Override
    public CategoryResponse findById(final String id) {
        return this.categoryRepository.findById(id)
                .map(this.categoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return this.categoryRepository.findAll()
                .stream()
                .map(this.categoryMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(final String id) {
        final Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        this.categoryRepository.delete(category);
    }

    private void checkIfCategoryExistsByName(String name) {

        final Optional<Category> category = this.categoryRepository.findByNameIgnoreCase(name);
        if (category.isPresent()) {
            log.debug("Category with name {} already exists", name);
            throw new RuntimeException("Category with name " + name + " already exists");
        }
    }
}
