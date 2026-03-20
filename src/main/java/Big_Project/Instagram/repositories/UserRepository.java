package Big_Project.Instagram.repositories;

import Big_Project.Instagram.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User>  {

    Optional<User> findByUsernameAndDeletedFalse(String username);

    Optional<User> findByEmailAndDeletedFalse(String email);

    List<User> findByDeletedFalse();
}