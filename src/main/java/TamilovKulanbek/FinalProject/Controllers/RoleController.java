package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Models.RoleChangeModel;
import TamilovKulanbek.FinalProject.Models.RoleCreateModel;
import TamilovKulanbek.FinalProject.Services.RoleService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
//    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService){  //}, UserService userService) {
        this.roleService = roleService;
//        this.userService = userService;
    }

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }
    @GetMapping("/{id}")
    public Role getById(@PathVariable("id")Long id){
        return roleService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        roleService.deleteById(id);
    }
    @PostMapping
    public ResponseMessage createRole (@RequestBody RoleCreateModel roleCreateModel){
        return roleService.createNewRole(roleCreateModel);
    }
    @PostMapping("/change")
    public ResponseMessage changeUserRole(@RequestBody RoleChangeModel roleChangeModel){
        return roleService.changeUserRole(roleChangeModel);
    }
}
