package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Services.ItemService;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAll(){
        return itemService.findAllByStatus(Status.ACTIVE);
    }

    @GetMapping("/category/{id}")
    public List<Item> getItemByCategoryId(@PathVariable Long id){
        return itemService.findAllByCategory_IdAndStatus(id, Status.ACTIVE);
    }

    @PostMapping
    public Item save (@RequestBody ItemModel itemModel){
        return itemService.create(itemModel);
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable("id") Long id){
        return itemService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteById(id);
    }
}
