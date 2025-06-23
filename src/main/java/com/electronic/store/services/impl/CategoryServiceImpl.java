package com.electronic.store.services.impl;

import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.CategoryDto;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.Category;
import com.electronic.store.entities.User;
import com.electronic.store.exceptions.ResourceNotFoundException;
import com.electronic.store.helper.Helper;
import com.electronic.store.repositories.CategoryRepository;
import com.electronic.store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category saveCategory = modelMapper.map(categoryDto, Category.class);
        String categoryId= UUID.randomUUID().toString();
        saveCategory.setCategoryId(categoryId);
        categoryRepository.save(saveCategory);
        return modelMapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(categoryId+ " is available in database"));
        category.setDescription(categoryDto.getDescription());
        category.setTitle(categoryDto.getTitle());
        category.setCoverImage(categoryDto.getCoverImage());
        categoryRepository.save(category);


        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("desc") ?  Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> page= categoryRepository.findAll(pageable);
        return Helper.getPageableResponse(page, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(categoryId+ " is available in database"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);

    }
}
