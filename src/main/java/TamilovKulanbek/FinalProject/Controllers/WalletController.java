package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PostMapping
    public Wallet save(@RequestBody Wallet wallet){
        return walletService.save(wallet);
    }
    @GetMapping
    public List<Wallet> getAll(){
        return walletService.getAll();
    }
    @GetMapping("/{id}")
    public Wallet getById(@PathVariable("id")Long id){
        return walletService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        walletService.deleteById(id);
    }

}
