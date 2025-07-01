package com.electronic.store.repositories;

import com.electronic.store.entities.Category;
import com.electronic.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findByTitleContaining(String subTitle, Pageable pageable);

    Page<Product> findByLiveTrue(Pageable pageable);

    List<Product> findByStock(boolean stock);

    Page<Product>  findByCategory(Category category, Pageable pageable);
}
