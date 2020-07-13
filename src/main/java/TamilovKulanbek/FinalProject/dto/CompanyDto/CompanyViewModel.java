package TamilovKulanbek.FinalProject.dto.CompanyDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyViewModel {
    String name;
    String address;
    String phone;

}
