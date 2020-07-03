package TamilovKulanbek.FinalProject.dto.CartDto;

import TamilovKulanbek.FinalProject.dto.ItemDto.ItemQuantityViewModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartModel {
    Long userId;

    List<ItemQuantityViewModel> itemQuantityViewModels;

    BigDecimal totalAmount;

}
