package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Models.RoleChangeModel;
import TamilovKulanbek.FinalProject.Repositories.RoleRepository;
import TamilovKulanbek.FinalProject.Services.RoleService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> optionalUserRole = roleRepository.findById(id);
        return optionalUserRole.orElse(null);
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public ResponseMessage changeUserRole(RoleChangeModel roleChangeModel){
    User user = userService.findByEmail(roleChangeModel.getEmail());
    Role roleAdmin = getById(roleChangeModel.getRoleId());
    List<Role> roleList = new ArrayList<>();
        roleList.add(roleAdmin);
        user.setRoles(roleList);
        userService.save(user);

        return new ResponseMessage("Role of '"+user.getEmail()+"' was successfully changed");
    }
}
