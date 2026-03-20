package Big_Project.Instagram.mappers;

import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.models.UserModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserModel toModel(User user);

    List<UserModel> toModelList(List<User> users);
}