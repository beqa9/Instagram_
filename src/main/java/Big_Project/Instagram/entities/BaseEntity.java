package Big_Project.Instagram.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "is_deleted")
    protected boolean deleted = false;

    protected OffsetDateTime createdAt = OffsetDateTime.now();

}
