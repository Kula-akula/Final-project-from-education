package TamilovKulanbek.FinalProject.dto.ShopDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopCreateModel {
    String shopName;
    String shopAddress;
    String firstName;
    String lastName;
    String phone;
    String email;
    String password;
}
