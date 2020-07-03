package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Models.RoleChangeModel;

public interface RoleService extends BaseService<Role> {
    ResponseMessage changeUserRole(RoleChangeModel roleChangeModel);

}
