package com.electronic.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private  String userId;

    @NotNull(message = "Name is required")
    @Size(min=3, max = 49, message = "Name should have more than 2 char and less than 50")
    private String name;

    @Email(message = "Email is invalid")
    @NotNull(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password should have char and number")
    @Size(min = 3, message = "Minium 3 char is required")
    @NotNull(message = "Password is required")
    private String password;

    private String gender;
    private String about;
    private String imageName;
}
