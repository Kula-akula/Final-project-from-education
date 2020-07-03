package TamilovKulanbek.FinalProject.dto.WalletDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletResponseModel {
    String email;
    String requisite;
    BigDecimal balance;

}
