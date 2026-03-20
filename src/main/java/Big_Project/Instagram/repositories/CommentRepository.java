package Big_Project.Instagram.repositories;

import Big_Project.Instagram.entities.Comment;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {

    List<Comment> findByPostId(Long postId);
}