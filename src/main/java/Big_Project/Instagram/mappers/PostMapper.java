package Big_Project.Instagram.mappers;

import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.models.PostModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source="user.id",target="userId")
    PostModel toModel(Post post);

    @Mapping(source="userId", target="user.id")
    Post toEntity(PostModel model);

    List<PostModel> toModelList(List<Post> posts);
}