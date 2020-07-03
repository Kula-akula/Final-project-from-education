package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Services.ShopService;
import jdk.nashorn.internal.ir.LoopNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PreAuthorize("ADMIN")
    @PostMapping
    public Shop save(@RequestBody Shop shop){
        return shopService.save(shop);
    }

    @GetMapping
    public List<Shop> getAll(){
        return shopService.getAll();
    }

    @GetMapping("/{id}")
    public Shop getById (@PathVariable("id")Long id){
        return shopService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        shopService.deleteById(id);
    }
}
