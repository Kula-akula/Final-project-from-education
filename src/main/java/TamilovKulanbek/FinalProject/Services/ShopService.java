package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Exception.ShopNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopCreateModel;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopViewModel;

import java.util.List;

public interface ShopService extends BaseService<Shop> {
    Shop findByEmail(String email);
    List<ShopViewModel> viewAll();
    ResponseMessage create(ShopCreateModel shopCreateModel);
    ShopViewModel findByName(String name);
    ResponseMessage deActivateShop(String email) throws ShopNotFoundException;

    ResponseMessage reActivateShop (String email) throws ShopNotFoundException;
}
