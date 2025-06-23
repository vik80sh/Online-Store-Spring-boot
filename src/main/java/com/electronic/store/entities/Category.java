package com.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
}
