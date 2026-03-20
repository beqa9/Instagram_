package Big_Project.Instagram.repositories;

import Big_Project.Instagram.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostRepository extends BaseRepository<Post> {

    List<Post> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT p FROM Post p JOIN Follow f ON p.user.id = f.following.id WHERE f.follower.id = :userId")
    Page<Post> getFeed(Long userId, Pageable pageable);
}
