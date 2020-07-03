package TamilovKulanbek.FinalProject.dto.ItemDto;


import TamilovKulanbek.FinalProject.Entities.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemQuantityViewModel {
    Item item;
    Integer quantity;
}
