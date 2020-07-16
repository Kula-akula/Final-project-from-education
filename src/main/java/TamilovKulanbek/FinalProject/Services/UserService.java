package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserRegisterException;
import TamilovKulanbek.FinalProject.Exception.WrongUserRegistration;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.dto.userDto.UserActivationModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserAuth;
import TamilovKulanbek.FinalProject.dto.userDto.UserModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserFindModel;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByEmailAndIsActive(String email, Integer isActive);

    ResponseMessage create(UserModel userModel);

    User findByEmail(String email);

//    User checkForNewEmail(String email)throws WrongUserRegistration;

    List<User> findByFirstNameAndLastName(UserFindModel userFindModel) throws UserNotFoundException;

    User deActivateUser(String email) throws UserNotFoundException;

    ResponseMessage userActivation(UserActivationModel userActivationModel) throws UserNotFoundException;

}