package TamilovKulanbek.FinalProject.Repositories;

import TamilovKulanbek.FinalProject.Entities.PaymentCheque;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentChequeRepository extends JpaRepository<PaymentCheque, Long> {
    List<PaymentCheque> findAllByWalletFrom(Wallet wallet);
}
