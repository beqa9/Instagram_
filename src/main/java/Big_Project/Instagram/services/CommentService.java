package Big_Project.Instagram.services;


public interface CommentService {

    void commentPost(String username, Long postId, String text);

    void replyComment(String username, Long parentCommentId, String text);

}