package com.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {

    private String categoryId;

    @NotNull(message="Title is require")
    @Size(min = 3, message = "Please update title, minimum 3 character required")
    private String title;

    @Size(min=10, max = 1000, message = "Please update description, minimum 3 character required")
    private String description;

    private String coverImage;
}
