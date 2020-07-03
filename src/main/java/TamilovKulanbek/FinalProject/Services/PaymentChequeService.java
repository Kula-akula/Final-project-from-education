package TamilovKulanbek.FinalProject.Services;


import TamilovKulanbek.FinalProject.Entities.PaymentCheque;
import TamilovKulanbek.FinalProject.Entities.Wallet;
import TamilovKulanbek.FinalProject.Exception.WrongBalanceException;
import TamilovKulanbek.FinalProject.dto.WalletDto.PaymentResponseModel;

import java.util.List;

public interface PaymentChequeService extends BaseService<PaymentCheque> {
    PaymentResponseModel createPayment(String email) throws WrongBalanceException;

    List<PaymentCheque> findAllByWalletFrom(Wallet wallet);


}
