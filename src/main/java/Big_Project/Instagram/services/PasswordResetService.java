package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.PasswordResetToken;
import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.repositories.PasswordResetTokenRepository;
import Big_Project.Instagram.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository repo;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder encoder;

    @Transactional
    public void requestReset(String email) {

        User user = userRepository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken reset = new PasswordResetToken();
        reset.setToken(token);
        reset.setUser(user);
        reset.setExpiryDate(OffsetDateTime.now().plusMinutes(15));

        repo.save(reset);

        emailService.sendEmail(
                email,
                "Password Reset",
                "Reset link: http://localhost:8085/api/auth/reset?token=" + token
        );
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {

        PasswordResetToken reset = repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (reset.getExpiryDate().isBefore(OffsetDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User user = reset.getUser();
        user.setPasswordHash(encoder.encode(newPassword));

        userRepository.save(user);
    }
}