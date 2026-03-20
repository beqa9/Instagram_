package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.models.AuthRequest;
import Big_Project.Instagram.models.AuthResponse;
import Big_Project.Instagram.models.RegisterRequest;
import Big_Project.Instagram.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest request) {

        Authentication auth =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()));

        String token =
                jwtService.generateToken(
                        (org.springframework.security.core.userdetails.UserDetails)
                                auth.getPrincipal());

        return new AuthResponse(token);
    }

    @Transactional
    @Override
    public void register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPasswordHash(
                passwordEncoder.encode(request.password()));
        user.setDeleted(false);

        userRepository.save(user);
    }
}