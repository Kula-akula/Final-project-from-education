package TamilovKulanbek.FinalProject.Repositories;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByEmail(String email);
    Company findByName(String name);
}
