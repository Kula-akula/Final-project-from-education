package TamilovKulanbek.FinalProject.dto.WalletDto;

import TamilovKulanbek.FinalProject.Enums.Currency;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletModel {
    String requisite;
    Currency currency;
    String bankCard;

}
