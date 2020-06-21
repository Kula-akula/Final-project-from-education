package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Entities.UserRole;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserRegisterException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Repositories.UserRepository;
import TamilovKulanbek.FinalProject.Repositories.UserRoleRepository;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.dto.userDto.UserModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserFindModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private WalletService walletService;



    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser=userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User findByEmailAndIsActive(String email, Integer isActive) {
        return userRepository.findByEmailAndIsActive(email, isActive);
    }
    @Override
    public ResponseMessage create(UserModel userModel) throws UserRegisterException, UserNotFoundException {
        if(!checkUserModelForUnique(userModel.getEmail())) {
            throw  new UserRegisterException("User with this email already exists");
        }

        User user = saveAndGetUserByUserModel(userModel);
//        createWalletForUser(user);

        return new ResponseMessage(user.getEmail() + " was successfully registered");

    }
    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new UserNotFoundException("User with '" + email + "'  email not found");
        return user;
    }

    @Override
    public List<User> findByFirstNameAndLastName(UserFindModel userFindModel) throws UserNotFoundException {
        List<User> users = userRepository.findByFirstNameAndLastName(userFindModel.getLastName(), userFindModel.getFirstName());
        if(users == null) throw new UserNotFoundException("User with surname: '" + userFindModel.getLastName() + "' and firstName: '" + userFindModel.getFirstName() + "' not found");
        return users;
    }

    @Override
    public User deActivateUser(String email) throws UserNotFoundException {
        User user = findByEmail(email);
        user.setIsActive(0);
        return save(user);
    }

    @Override
    public User againActivateUser(String email) throws UserNotFoundException {
        User user = findByEmail(email);
        user.setIsActive(1);
        return save(user);
    }

    private User saveAndGetUserByUserModel(UserModel userModel){
        UserRole roleUser = userRoleRepository.findByRole("ROLE_USER");
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(roleUser);

        User user = User.builder()
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .isActive(1)
                .roles(userRoleList)
                .build();
        return save(user);
    }

//    private void createWalletForUser(User user){
//        Wallet wallet = Wallet.builder()
//                .balance(new BigDecimal(BigInteger.ZERO))
//                .status(Status.ACTIVE)
//                .user(user)
//                .build();
//
//        walletService.save(wallet);
//    }

    private boolean checkUserModelForUnique(String email) throws UserNotFoundException {
        return findByEmail(email) == null;
    }

}
