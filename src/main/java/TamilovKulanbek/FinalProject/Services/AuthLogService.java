package TamilovKulanbek.FinalProject.Services;


import TamilovKulanbek.FinalProject.Entities.AuthLog;
import TamilovKulanbek.FinalProject.Enums.Status;

import java.util.List;

public interface AuthLogService extends BaseService<AuthLog> {
    AuthLog create(String email, Status status);

//    Integer countAllByStatusAndUserAndRecovery(Status status, String email, boolean isRecovery);

    Integer countByUserAndStatus(Status status, String email, Integer isRecovered);

    List<AuthLog> findAllByUserAndStatus(String email, Status status);

    List<AuthLog> saveAll(List<AuthLog> authLogs);
}
