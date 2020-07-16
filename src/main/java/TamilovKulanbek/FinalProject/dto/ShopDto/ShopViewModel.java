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
public class ShopViewModel {
    String shopName;
    String shopAddress;
    String phone;
}
