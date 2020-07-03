package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.AuthLog;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Repositories.AuthLogRepository;
import TamilovKulanbek.FinalProject.Services.AuthLogService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthLogServiceImpl implements AuthLogService {

    @Autowired
    private AuthLogRepository authLogRepository;
    @Autowired
    private UserService userService;

    @Override
    public AuthLog save(AuthLog authLog) {
        return authLogRepository.save(authLog);
    }

    @Override
    public AuthLog getById(Long id) {
        Optional<AuthLog> authLogOptional = authLogRepository.findById(id);
        return authLogOptional.orElse(null);
    }

    @Override
    public List<AuthLog> getAll() {
        return authLogRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        authLogRepository.deleteById(id);
    }

    @Override
    public AuthLog create(String email, Status status){
        User user = userService.findByEmail(email);
        AuthLog authLog = AuthLog.builder()
                .user(user)
                .status(status)
                .isRecovered(0)
                .build();

        return save(authLog);
    }

//    @Override
//    public Integer countAllByStatusAndUserAndRecovery(Status status, String email, boolean isRecovery) {
//        User user = userService.findByEmail(email);
//        return authLogRepository.countAllByStatusAndUserAndRecovery(status, user, isRecovery);
//    }

    @Override
    public Integer countByUserAndStatus(Status status, String email, Integer isRecovered) {
        User user = userService.findByEmail(email);
        return authLogRepository.getCount(status, user, isRecovered);
    }

    @Override
    public List<AuthLog> findAllByUserAndStatus(String email, Status status) {
        User user = userService.findByEmail(email);
        return authLogRepository.findAllByUserAndStatus(user, status);
    }

    @Override
    public List<AuthLog> saveAll(List<AuthLog> authLogs) {
        return authLogRepository.saveAll(authLogs);
    }
}
