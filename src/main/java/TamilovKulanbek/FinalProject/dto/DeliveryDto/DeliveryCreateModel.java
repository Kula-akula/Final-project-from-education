package TamilovKulanbek.FinalProject.dto.DeliveryDto;

import TamilovKulanbek.FinalProject.Enums.Currency;
import TamilovKulanbek.FinalProject.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryCreateModel {
    String consumer;
    String requisiteOfConsumer;
    String requisiteOfSeller;
    Date purchasedDate;
    BigDecimal amount;
    Currency currency;
    Status status;
}
