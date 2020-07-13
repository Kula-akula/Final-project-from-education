package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Cart;
import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Repositories.UserRepository;
import TamilovKulanbek.FinalProject.Repositories.RoleRepository;
import TamilovKulanbek.FinalProject.Services.CartService;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.Services.WalletService;
import TamilovKulanbek.FinalProject.dto.userDto.UserModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserFindModel;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WalletService walletService;
    @Autowired
    private CartService cartService;



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
    public ResponseMessage create(UserModel userModel) {
        if(findByEmail(userModel.getEmail())!= null)
            return new ResponseMessage("User with this email already exists");


//        if(!checkUserModelForUnique(userModel.getEmail())) {
//            throw  new UserRegisterException("User with this email already exists");
//        }

        User user = saveByUserModel(userModel);
        createWalletForUser(user);
        createCartForUser(user);

        return new ResponseMessage(user.getEmail()+ " was successfully registered");

    }
    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
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
    public User reActivateUser(String email) throws UserNotFoundException {
        User user = findByEmail(email);
        user.setIsActive(1);
        return save(user);
    }



    private User saveByUserModel(UserModel userModel){
        Role roleUser = roleRepository.findByRole("USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleUser);

        User user = User.builder()
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(passwordEncoder.encode(userModel.getPassword()))
//                .address(userModel.getAddress())
                .phoneNumber(userModel.getPhoneNumber())
                .isActive(1)
                .roles(roleList)
                .build();
        return save(user);
    }

    private void createWalletForUser(User user){
        Wallet wallet = Wallet.builder()
                .balance(new BigDecimal(BigInteger.ZERO))
                .user(user)
                .build();

        walletService.save(wallet);
    }
    private void createCartForUser(User user){
        Cart cart=Cart.builder()
                .user(user)
                .totalAmount(new BigDecimal(0))
                .build();

        cartService.save(cart);
    }

//    @Override
//    public User findByWalletId(Long id) {
//        return null;
//    }

    private boolean checkUserModelForUnique(String email) throws UserNotFoundException {
        return findByEmail(email) == null;
    }

}
