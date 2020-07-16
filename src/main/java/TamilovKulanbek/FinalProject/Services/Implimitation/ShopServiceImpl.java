package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Exception.CompanyNotFoundException;
import TamilovKulanbek.FinalProject.Exception.ShopNotFoundException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Repositories.RoleRepository;
import TamilovKulanbek.FinalProject.Repositories.ShopRepository;
import TamilovKulanbek.FinalProject.Services.ShopService;
import TamilovKulanbek.FinalProject.Services.WalletService;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopCreateModel;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;
    private final RoleRepository roleRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, PasswordEncoder passwordEncoder, WalletService walletService, RoleRepository roleRepository) {
        this.shopRepository = shopRepository;
        this.passwordEncoder=passwordEncoder;
        this.walletService=walletService;
        this.roleRepository=roleRepository;
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getById(Long id) {
        Optional<Shop> optionalShop=shopRepository.findById(id);
        return optionalShop.orElse(null);
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


    @Override
    public ShopViewModel findByName(String shopName) {
        Shop shop=shopRepository.findByShopName(shopName);
        return ShopViewModel.builder()
                .shopName(shop.getShopName())
                .shopAddress(shop.getShopAddress())
                .phone(shop.getPhone())
                .build();
    }
    @Override
    public List<ShopViewModel> viewAll() {
        List<Shop> shopList=shopRepository.findAll();
        List<ShopViewModel> shopViewModelList=new ArrayList<>();
        for (Shop shop:shopList) {
            ShopViewModel shopViewModel= ShopViewModel.builder()
                    .shopAddress(shop.getShopAddress())
                    .shopName(shop.getShopName())
                    .phone(shop.getPhone())
                    .build();
            shopViewModelList.add (shopViewModel);
        }
        return shopViewModelList;
    }

    @Override
    public ResponseMessage create(ShopCreateModel shopCreateModel) {
        if (findByEmail(shopCreateModel.getEmail()) !=null)
            return new ResponseMessage("Shop already exists");

        Shop shop=saveByShopModel(shopCreateModel);
        createShopWallet(shop);

        return new ResponseMessage("Shop was successfully created");
    }

    @Override
    public ResponseMessage deActivateShop(String email) throws ShopNotFoundException {
        try {
            if (findByEmail(email) == null) throw new ShopNotFoundException("Shop does not exist");
            Shop shop = findByEmail(email);
            shop.setIsActive(0);
            save(shop);
            return new ResponseMessage("Shop is deactivated") ;
        }catch (Exception e){
            e.getStackTrace();
        }
        return  new ResponseMessage("Shop does not exist");
    }

    @Override
    public ResponseMessage reActivateShop(String email) throws ShopNotFoundException {
        try {
            if (findByEmail(email) == null) throw new ShopNotFoundException("Shop does not exist");
            Shop shop = findByEmail(email);
            shop.setIsActive(1);
            save(shop);
            return new ResponseMessage("Shop is Re-activated") ;
        }catch (Exception e){
            e.getStackTrace();
        }
        return  new ResponseMessage("Shop does not exist");
    }

    private Shop saveByShopModel(ShopCreateModel shopCreateModel){
        Role roleShop = roleRepository.findByRole("ROLE_SHOP");
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleShop);

        Shop shop=Shop.builder()
                .shopName(shopCreateModel.getShopName())
                .shopAddress(shopCreateModel.getShopAddress())
                .email(shopCreateModel.getEmail())
                .password(passwordEncoder.encode(shopCreateModel.getPassword()))
                .firstName(shopCreateModel.getFirstName())
                .lastName(shopCreateModel.getLastName())
                .isActive(1)
                .phone(shopCreateModel.getPhone())
                .roles(roleList)
                .build();
        return save(shop);
    }
    private void createShopWallet(Shop shop){
        Wallet wallet = Wallet.builder()
                .balance(new BigDecimal(BigInteger.ZERO))
                .shop(shop)
                .build();
        walletService.save(wallet);
    }
}
