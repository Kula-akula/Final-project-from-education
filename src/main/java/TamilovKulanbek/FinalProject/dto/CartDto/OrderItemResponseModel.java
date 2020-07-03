package TamilovKulanbek.FinalProject.dto.CartDto;


import TamilovKulanbek.FinalProject.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponseModel {
    String category;
    String itemName;
    Integer itemQuantity;
    BigDecimal price;
    Integer discountPercentages;
    BigDecimal unitItemPrice;
    BigDecimal totalAmount;
    String consumer;
    Status status;


}
