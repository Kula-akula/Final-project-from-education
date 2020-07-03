package TamilovKulanbek.FinalProject.dto.WalletDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletReplenishModel {
    Long walletId;
    BigDecimal amount;
//    String bankCard;
}
