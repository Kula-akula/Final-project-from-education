package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.UserRole;
import TamilovKulanbek.FinalProject.Repositories.UserRoleRepository;
import TamilovKulanbek.FinalProject.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getById(Long id) {
        Optional<UserRole> optionalUserRole=userRoleRepository.findById(id);
        return optionalUserRole.get();
    }

    @Override
    public UserRole save(UserRole object) {
        return userRoleRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }

}
