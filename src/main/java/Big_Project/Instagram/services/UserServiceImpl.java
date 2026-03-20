package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.models.CreateUserRequest;
import Big_Project.Instagram.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserCacheService userCacheService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findByDeletedFalse();
    }

    @Override
    public User getUserById(Long id){
        return userCacheService.getUserById(id);
    }

    @Transactional
    @Override
    @CacheEvict(value = {"users","users-id"}, allEntries = true)
    public User register(CreateUserRequest request){

        User user=new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setBio(request.bio());

        return userRepository.save(user);
    }

    @Transactional
    @Override
    @CacheEvict(value = {"users","users-id"}, allEntries = true)
    public void deleteUser(Long id){

        User user=getUserById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}