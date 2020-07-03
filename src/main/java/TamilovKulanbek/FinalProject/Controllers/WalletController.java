package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.dto.WalletDto.WalletReplenishModel;
import TamilovKulanbek.FinalProject.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<Wallet> getAll(){
        return walletService.getAll();
    }

    @GetMapping("/{id}")
    public Wallet getById(@PathVariable("id") Long id){
        return walletService.getById(id);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getWallet(){
        try {
            return new ResponseEntity<>(walletService.getWalletModelByUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/replenish")
    public ResponseEntity<?> replenish(@RequestBody WalletReplenishModel walletReplenishModel){
        try {
            return new ResponseEntity<>(walletService.replenishByYourSelf(walletReplenishModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
