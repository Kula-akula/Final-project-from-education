package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item save(@RequestBody Item item){
        return itemService.save(item);
    }

    @GetMapping
    public List<Item> getAll(){
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable("id")Long id){
        return itemService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        itemService.deleteById(id);
    }
}
