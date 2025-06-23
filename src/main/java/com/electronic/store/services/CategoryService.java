package com.electronic.store.services;

import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.CategoryDto;
import com.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, String categoryId);

    PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);

    CategoryDto getCategoryById(String categoryId);

    void deleteCategory(String categoryId);

}
