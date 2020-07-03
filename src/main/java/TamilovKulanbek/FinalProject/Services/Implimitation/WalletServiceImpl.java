package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.dto.WalletDto.WalletReplenishModel;
import TamilovKulanbek.FinalProject.Repositories.WalletRepository;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.Services.WalletService;
import TamilovKulanbek.FinalProject.dto.WalletDto.WalletResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getById(Long id) {
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        return optionalWallet.orElse(null);
    }

    @Override
    public Wallet save(Wallet object) {
        return walletRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }

//    @Override
//    public Wallet create(WalletModel walletModel, String email) {
//        Wallet wallet = new Wallet();
//        User user = userService.findByEmail(email);
//        if(user == null) return null;
//        wallet.setUser(user);
////        wallet.setRequisite(walletModel.getRequisite());
//        wallet.setBalance(new BigDecimal(0));
////        wallet.setCurrency(walletModel.getCurrency());
//
//        return save(wallet);
//    }

    @Override
    public Wallet getByUser(User user) {
        return walletRepository.findByUser(user);
    }

    @Override
    public WalletResponseModel getWalletModelByUser() throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        User user = userService.findByEmail(email);
        Wallet wallet = getByUser(user);
        return WalletResponseModel.builder()
                .email(wallet.getUser().getEmail())
//                .requisite(wallet.getRequisite())
                .balance(wallet.getBalance())
                .build();
    }

    @Override
    public String replenishByYourSelf(WalletReplenishModel walletReplenishModel) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        User user = userService.findByEmail(email);
        Wallet wallet = getByUser(user);
        wallet.setBalance(wallet.getBalance().add(walletReplenishModel.getAmount()));
        save(wallet);
        return "You have successfully replenishByYourSelf your balance";

    }

    @Override
    public String replenishWallet(WalletReplenishModel walletReplenishModel) {
        if (!walletRepository.findById(walletReplenishModel.getWalletId()).isPresent())
            return "Wallet ID is incorrect";
        Wallet wallet= Wallet.builder()
                .id(walletReplenishModel.getWalletId())
                .build();
        wallet.setBalance(wallet.getBalance().add(walletReplenishModel.getAmount()));
        save(wallet);
        User user=wallet.getUser();

        return "You have successfully replenish '"+user.getFirstName()+"' wallet";
    }

    @Override
    public Wallet findByCompany(Company company) {
        return walletRepository.findByCompany(company);
    }


    @Override
    public String generateRequisite() {
        return "" + System.currentTimeMillis();
    }
}
