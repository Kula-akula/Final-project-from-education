package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Category;
import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Repositories.ItemRepository;
import TamilovKulanbek.FinalProject.Services.CategoryService;
import TamilovKulanbek.FinalProject.Services.ItemService;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.get();
    }

    @Override
    public Item save(Item object) {
        return itemRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);

    }

    @Override
    public List<Item> itemsByCategoryId(Long categoryId) {
        return itemRepository.findByCategory_Id(categoryId);
    }

    @Override
    public Item create(ItemModel itemModel) {
        Category category = categoryService.getById(itemModel.getCategory());
        Item item = Item.builder()
                .itemName(itemModel.getItemName())
                .category(category)
                .price(itemModel.getPrice())
                .discountPercentages(itemModel.getDiscountPercentages())
                .build();


        return save(item);
    }

    @Override
    public List<Item> findAllByCategory_IdAndStatus(Long id, Status status) {
        return itemRepository.findAllByCategory_IdAndStatus(id, status);
    }

    @Override
    public List<Item> findAllByStatus(Status status) {
        return itemRepository.findAllByStatus(status);
    }
}
