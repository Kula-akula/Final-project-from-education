package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemModel;

import java.util.List;

public interface ItemService extends BaseService<Item> {
    Item create (ItemModel itemModel);
    List<Item> findAllByCategory_IdAndStatus(Long id, Status status);
    List<Item> findAllByStatus(Status status);
    List<Item> itemsByCategoryId(Long categoryId);
}
