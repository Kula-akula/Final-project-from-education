package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Exception.CompanyNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyCreateModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyRequestModel;

public interface CompanyService extends BaseService<Company> {
    ResponseMessage create(CompanyCreateModel companyCreateModel);
    Company findByEmail(String email);
    CompanyRequestModel findByName(String name);
    Company deActivateCompany(String email) throws CompanyNotFoundException;

    Company reActivateCompany (String email) throws CompanyNotFoundException;
}
