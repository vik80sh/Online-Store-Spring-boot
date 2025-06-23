package com.electronic.store.controllers;

import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.CategoryDto;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") String categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, categoryId), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value="pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value="pageSize", defaultValue = "10", required = false ) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return new ResponseEntity<>(categoryService.getAllCategory(pageNumber,pageSize,sortBy, sortDir), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategoryById(@PathVariable("categoryId") String categoryId){
        categoryService.deleteCategory(categoryId);
        ApiResponseMessage apiResponseMessage= ApiResponseMessage.builder()
                .message("Categry deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }
}
