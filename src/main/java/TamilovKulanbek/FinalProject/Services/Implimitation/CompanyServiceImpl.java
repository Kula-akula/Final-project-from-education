package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Exception.CompanyNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Repositories.CompanyRepository;
import TamilovKulanbek.FinalProject.Repositories.RoleRepository;
import TamilovKulanbek.FinalProject.Services.BlackListService;
import TamilovKulanbek.FinalProject.Services.CompanyService;
import TamilovKulanbek.FinalProject.Services.WalletService;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyCreateModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyRequestModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WalletService walletService;
    @Autowired
    private BlackListService blackListService;

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public List<CompanyViewModel> viewAll() {
        List<Company> companyList=companyRepository.findAll();
        List<CompanyViewModel> companyViewModelList=new ArrayList<>();
        for (Company company:companyList) {
            CompanyViewModel companyViewModel= CompanyViewModel.builder()
                    .address(company.getAddress())
                    .name(company.getName())
                    .phone(company.getPhone())
                    .build();
        companyViewModelList.add (companyViewModel);
        }
        return companyViewModelList;
    }

    @Override
    public Company getById(Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        return companyOptional.orElse(null);
    }

    @Override
    public CompanyRequestModel findByName(String name) {
        Company company=companyRepository.findByName(name);
        return CompanyRequestModel.builder()
                .name(company.getName())
                .address(company.getAddress())
                .phone(company.getPhone())
                .build();
    }

    @Override
    public Company save(Company object) {
        return companyRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public ResponseMessage create(CompanyCreateModel companyCreateModel) {
        if (findByEmail(companyCreateModel.getEmail()) !=null)
            return new ResponseMessage("Company already exists");

        Company company=saveByCompanyModel(companyCreateModel);
        createCompanyWallet(company);

        return new ResponseMessage("Company was successfully created");
    }

    @Override
    public ResponseMessage deActivateCompany(String email) throws CompanyNotFoundException {
        try {
            if (findByEmail(email) == null) throw new CompanyNotFoundException("Company does not exist");
            Company company = findByEmail(email);
            company.setIsActive(0);
            save(company);
            return new ResponseMessage("Company is deactivated") ;
        }catch (Exception e){
            e.getStackTrace();
        }
        return  new ResponseMessage("Company does not exist");
    }

    @Override
    public ResponseMessage reActivateCompany(String email) throws CompanyNotFoundException {
        try {
            if (findByEmail(email) == null) throw new CompanyNotFoundException("Company does not exist");
            Company company = findByEmail(email);
            company.setIsActive(1);
            save(company);
            return new ResponseMessage("Company is Re-activated") ;
        }catch (Exception e){
            e.getStackTrace();
        }
        return  new ResponseMessage("Company does not exist");
    }

    private Company saveByCompanyModel(CompanyCreateModel companyCreateModel){
        Role roleCompany = roleRepository.findByRole("MANAGER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleCompany);

        Company company=Company.builder()
                .name(companyCreateModel.getName())
                .address(companyCreateModel.getAddress())
                .email(companyCreateModel.getEmail())
                .password(passwordEncoder.encode(companyCreateModel.getPassword()))
                .isActive(1)
                .phone(companyCreateModel.getPhone())
                .roles(roleList)
                .build();
        return save(company);
    }
    private void createCompanyWallet(Company company){
        Wallet wallet = Wallet.builder()
                .balance(new BigDecimal(BigInteger.ZERO))
                .company(company)
                .build();
        walletService.save(wallet);
    }
}
