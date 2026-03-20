package Big_Project.Instagram.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank
        @Size(min = 3, max = 50)
        String username,

        @Email
        @NotBlank
        String email,

        @Size(min = 6)
        String password
) {}
