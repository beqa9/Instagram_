package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final UserRepository userRepository;

    @Cacheable(value = "users", key = "#username")
    public User getUserByUsername(String username) {

        return userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Cacheable(value = "users-id", key = "#id")
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}