package Big_Project.Instagram.controllers;

import Big_Project.Instagram.entities.PasswordResetToken;
import Big_Project.Instagram.models.AuthRequest;
import Big_Project.Instagram.models.AuthResponse;
import Big_Project.Instagram.models.RegisterRequest;
import Big_Project.Instagram.models.ResetPasswordRequest;
import Big_Project.Instagram.services.AuthService;
import Big_Project.Instagram.services.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);
    }

    @PostMapping("/reset-request")
    public void requestReset(@RequestParam String email) {
        passwordResetService.requestReset(email);
    }

    @PostMapping("/reset")
    public void reset(@RequestBody ResetPasswordRequest request) {
        passwordResetService.resetPassword(
                request.token(),
                request.newPassword()
        );
    }
}