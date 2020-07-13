package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Exception.CompanyNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Services.CompanyService;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyCreateModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyRequestModel;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseMessage create(@RequestBody CompanyCreateModel companyCreateModel){
        return companyService.create(companyCreateModel);
    }
    @PostMapping("/de-active")
    public ResponseMessage deActivate(@RequestBody String email) throws CompanyNotFoundException {
        return companyService.deActivateCompany(email);
    }
    @PostMapping("/re-active")
    public ResponseMessage reActivate(@RequestBody String email) throws CompanyNotFoundException {
        return companyService.reActivateCompany(email);
    }


    @GetMapping()
    public List<CompanyViewModel> viewAll(){
        return companyService.viewAll();
    }
    @GetMapping("/all")
    public List<Company> getAll(){
        return companyService.getAll();
    }
    @GetMapping("/id/{id}")
    public Company getById(@PathVariable("id") Long id){
        return companyService.getById(id);
    }
    @GetMapping("/{name}")
    public CompanyRequestModel getByName(@PathVariable("name") String name){
        return companyService.findByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        companyService.deleteById(id);
    }

}
