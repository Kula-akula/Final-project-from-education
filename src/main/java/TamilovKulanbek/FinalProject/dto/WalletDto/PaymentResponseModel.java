package TamilovKulanbek.FinalProject.dto.WalletDto;

import TamilovKulanbek.FinalProject.Enums.Currency;
import TamilovKulanbek.FinalProject.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponseModel {
    String consumer;
//    String requisiteOfConsumer;
//    String requisiteOfSeller;
    String purchasedDate;
    BigDecimal amount;
    Currency currency;
    Status status;
}
