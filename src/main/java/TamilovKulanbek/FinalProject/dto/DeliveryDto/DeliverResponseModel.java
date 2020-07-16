package TamilovKulanbek.FinalProject.dto.DeliveryDto;

import TamilovKulanbek.FinalProject.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliverResponseModel {
    String consumer;
    String requisiteOfConsumer;
    String requisiteOfSeller;
    String purchasedDate;
    BigDecimal amount;
    Currency currency;
    Status status;
}
