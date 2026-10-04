package com.saas.controllers;

import com.saas.requests.CategoryRequest;
import com.saas.responses.CategoryResponse;
import com.saas.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> addCategory(@Valid @RequestBody final CategoryRequest categoryRequest) {
        this.categoryService.create(categoryRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable("category-id") final String id,
            @Valid @RequestBody final CategoryRequest categoryRequest
    ) {
        this.categoryService.update(id, categoryRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable("category-id") final String id
    ) {
        return ResponseEntity.ok(this.categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.findAll());
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("category-id")  final String id
    ) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
