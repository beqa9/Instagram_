package Big_Project.Instagram.services;


import Big_Project.Instagram.models.AuthRequest;
import Big_Project.Instagram.models.AuthResponse;
import Big_Project.Instagram.models.RegisterRequest;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    void register(RegisterRequest request);
}