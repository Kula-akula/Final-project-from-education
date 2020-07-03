package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Entities.User;

public interface ShopService extends BaseService<Shop> {
    Shop findByEmail(String email);
}
