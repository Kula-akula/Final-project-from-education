package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Repositories.WalletRepository;
import TamilovKulanbek.FinalProject.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getById(Long id) {
        Optional<Wallet> optionalWallet=walletRepository.findById(id);
        return optionalWallet.get();
    }

    @Override
    public Wallet save(Wallet object) {
        return walletRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }
}
