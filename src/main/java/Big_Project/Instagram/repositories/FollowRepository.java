package Big_Project.Instagram.repositories;

import Big_Project.Instagram.entities.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends BaseRepository<Follow> {

    Optional<Follow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Follow> findByFollowerId(Long followerId);

    List<Follow> findByFollowingId(Long followingId);

}