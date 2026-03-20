package Big_Project.Instagram.repositories;

import Big_Project.Instagram.entities.Like;

import java.util.Optional;

public interface LikeRepository extends BaseRepository<Like> {

    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

}
