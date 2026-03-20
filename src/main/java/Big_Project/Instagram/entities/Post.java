package Big_Project.Instagram.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


@Entity
@Table(name="posts")
@Getter
@Setter
@SQLDelete(sql="UPDATE posts SET is_deleted=true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class Post extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String imageUrl;

    private String caption;
}