package com.saas.mappers;

import com.saas.entities.Category;
import com.saas.requests.CategoryRequest;
import com.saas.responses.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toEntity(final CategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .deleted(false)
                .build();
    }

    public CategoryResponse toResponse(final Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
