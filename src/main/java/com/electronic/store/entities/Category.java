package com.electronic.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name="id")
    private String categoryId;

    @Column(name="category_title")
    private String title;

    @Column(name="category_desc")
    private String description;

    @Column(name="category_image")
    private String coverImage;

    @OneToMany(mappedBy="category",cascade = CascadeType.ALL, fetch = FetchType.LAZY ) // fetchType.Lazy  when I fetch category, product should not fetch
    private List<Product> products= new ArrayList<>();

}
