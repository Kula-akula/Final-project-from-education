package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Repositories.RoleRepository;
import TamilovKulanbek.FinalProject.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> optionalUserRole= roleRepository.findById(id);
        return optionalUserRole.get();
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

}
