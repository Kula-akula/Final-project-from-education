package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Models.RoleChangeModel;
import TamilovKulanbek.FinalProject.Models.RoleCreateModel;

public interface RoleService extends BaseService<Role> {
    ResponseMessage changeUserRole(RoleChangeModel roleChangeModel);
    ResponseMessage createNewRole(RoleCreateModel roleCreateModel);

}
