package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserRegisterException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.dto.userDto.UserModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserFindModel;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByEmailAndIsActive(String email, Integer isActive);

    ResponseMessage create(UserModel userModel) throws UserRegisterException, UserNotFoundException;

    User findByEmail(String email) throws UserNotFoundException;

    List<User> findByFirstNameAndLastName(UserFindModel userFindModel) throws UserNotFoundException;

    User deActivateUser(String email) throws UserNotFoundException;

    User againActivateUser(String email) throws UserNotFoundException;
}
