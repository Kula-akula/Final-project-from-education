package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Exception.ShopNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Services.ShopService;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopCreateModel;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopViewModel;
import jdk.nashorn.internal.ir.LoopNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseMessage create (@RequestBody ShopCreateModel shopCreateModel){
        return shopService.create(shopCreateModel);
    }

    @PostMapping("/de-active")
    public ResponseMessage deActivate(@RequestBody String email) throws ShopNotFoundException {
        return shopService.deActivateShop(email);
    }

    @PostMapping("/re-active")
    public ResponseMessage reActivate(@RequestBody String email) throws ShopNotFoundException {
        return shopService.reActivateShop(email);
    }

    @GetMapping
    public List<ShopViewModel> viewAll(){return shopService.viewAll();}

    @GetMapping("/all")
    public List<Shop> getAll(){
        return shopService.getAll();
    }

    @GetMapping("/{id}")
    public Shop getById (@PathVariable("id")Long id){
        return shopService.getById(id);
    }

    @GetMapping("/{name}")
    public ShopViewModel getByName(@PathVariable("name") String shopName){
        return shopService.findByName(shopName);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        shopService.deleteById(id);
    }
}
