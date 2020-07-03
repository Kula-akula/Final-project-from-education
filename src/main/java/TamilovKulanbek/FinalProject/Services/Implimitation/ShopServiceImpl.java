package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Repositories.ShopRepository;
import TamilovKulanbek.FinalProject.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getById(Long id) {
        Optional<Shop> optionalShop=shopRepository.findById(id);
        return optionalShop.get();
    }

    @Override
    public Shop findByEmail(String email) {
        return shopRepository.findByEmail(email);
    }

    @Override
    public Shop save(Shop object) {
        return shopRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        shopRepository.deleteById(id);
    }
}
