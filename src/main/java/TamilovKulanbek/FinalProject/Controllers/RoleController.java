package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Models.RoleChangeModel;
import TamilovKulanbek.FinalProject.Services.RoleService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Role save(@RequestBody Role role){
        return roleService.save(role);
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
    @PostMapping("/change-role")
    public ResponseMessage changeUserRole(@RequestBody RoleChangeModel roleChangeModel){
       return roleService.changeUserRole(roleChangeModel);
    }
}
