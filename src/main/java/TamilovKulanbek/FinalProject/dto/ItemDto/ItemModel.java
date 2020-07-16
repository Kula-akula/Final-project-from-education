package TamilovKulanbek.FinalProject.dto.ItemDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemModel {
    Long company;
    String itemName;
    Long category;
    BigDecimal price;
    Integer discountPercentages;
    
    
}
