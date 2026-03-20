package Big_Project.Instagram.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name="comments")
@Getter
@Setter
@SQLDelete(sql="UPDATE comments SET is_deleted=true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class Comment extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Comment parent;

    @OneToMany(mappedBy="parent")
    private List<Comment> replies;

    private String text;
}