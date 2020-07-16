package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserRegisterException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Services.BlackListService;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.dto.userDto.UserActivationModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final BlackListService blackListService;

    @Autowired
    public UserController(UserService userService, BlackListService blackListService) {
        this.userService = userService;
        this.blackListService = blackListService;
    }

    @PostMapping("/register")
    public ResponseMessage save(@RequestBody UserModel userModel) throws  UserRegisterException {
        return userService.create(userModel);
    }
    @PostMapping
    public ResponseMessage activationController (@RequestBody UserActivationModel userActivationModel) throws UserNotFoundException {
        return userService.userActivation(userActivationModel);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable("id")Long id){
        return userService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        userService.deleteById(id);
    }

//    @PostMapping("/addBL")
//    public ResponseMessage

}
