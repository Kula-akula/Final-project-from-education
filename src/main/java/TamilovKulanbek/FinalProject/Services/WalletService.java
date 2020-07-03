package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Company;
import TamilovKulanbek.FinalProject.Entities.Shop;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.dto.WalletDto.WalletReplenishModel;
import TamilovKulanbek.FinalProject.dto.WalletDto.WalletModel;
import TamilovKulanbek.FinalProject.dto.WalletDto.WalletResponseModel;

public interface WalletService extends BaseService<Wallet> {
//    Wallet create (WalletModel walletModel, String email);

    Wallet findByCompany(Company company);
    Wallet getByUser(User user);
//    User findByUserId(Long id);

    String replenishByYourSelf(WalletReplenishModel walletReplenishModel);
    String replenishWallet(WalletReplenishModel walletReplenishModel);
    WalletResponseModel getWalletModelByUser() throws UserNotFoundException;
    String generateRequisite();
}
