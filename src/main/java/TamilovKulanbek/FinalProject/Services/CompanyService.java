package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Exception.CompanyNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyCreateModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyRequestModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyViewModel;

import java.util.List;

public interface CompanyService extends BaseService<Company> {
    List <CompanyViewModel> viewAll();
    ResponseMessage create(CompanyCreateModel companyCreateModel);
    Company findByEmail(String email);
    CompanyRequestModel findByName(String name);
    ResponseMessage deActivateCompany(String email) throws CompanyNotFoundException;

    ResponseMessage reActivateCompany (String email) throws CompanyNotFoundException;
}
