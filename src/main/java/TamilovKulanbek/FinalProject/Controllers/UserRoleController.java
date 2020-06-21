package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.UserRole;
import TamilovKulanbek.FinalProject.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRoles")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public UserRole save(@RequestBody UserRole userRole){
        return userRoleService.save(userRole);
    }
    @GetMapping
    public List<UserRole> getAll(){
        return userRoleService.getAll();
    }
    @GetMapping("/{id}")
    public UserRole getById(@PathVariable("id")Long id){
        return userRoleService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        userRoleService.deleteById(id);
    }
}
