package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.*;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Exception.WrongBalanceException;
import TamilovKulanbek.FinalProject.Repositories.PaymentChequeRepository;
import TamilovKulanbek.FinalProject.Services.*;
import TamilovKulanbek.FinalProject.dto.WalletDto.PaymentResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PaymentChequeServiceImpl implements PaymentChequeService {
    @Autowired
    private PaymentChequeRepository paymentChequeRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ShopService shopService;
    @Autowired
    private CompanyService companyService;


    @Override
    public List<PaymentCheque> getAll() {
        return paymentChequeRepository.findAll();
    }

    @Override
    public PaymentCheque getById(Long id) {
        Optional<PaymentCheque> optionalPayment = paymentChequeRepository.findById(id);
        return optionalPayment.get();
    }

    @Override
    public PaymentCheque save(PaymentCheque object) {
        return paymentChequeRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if (getById(id) != null) paymentChequeRepository.deleteById(id);
    }


    @Override
    public PaymentResponseModel createPayment(String email) throws WrongBalanceException {
        User user = userService.findByEmail(email);
        Company company=companyService.findByEmail(email);
        Wallet walletFrom = walletService.getByUser(user);
        Wallet walletTo = walletService.findByCompany(company);
        if(walletFrom.getBalance().compareTo(BigDecimal.ZERO) <= 0) throw new WrongBalanceException();
        PaymentCheque paymentCheque = PaymentCheque.builder()
                .amount(cartService.findByUser(user).getTotalAmount())
                .createdDate(new Date())
//                .currency(walletFrom.getCurrency())
                .walletFrom(walletFrom)
                .walletTo(walletTo)
                .status(Status.SUCCESS)
                .build();

        return paymentProcess(paymentCheque);
    }

    @Override
    public List<PaymentCheque> findAllByWalletFrom(Wallet wallet) {
        return paymentChequeRepository.findAllByWalletFrom(wallet);
    }

    private PaymentResponseModel paymentProcess(PaymentCheque paymentCheque) {
        Wallet from = walletService.getById(paymentCheque.getWalletFrom().getId());
        from.setBalance(from.getBalance().subtract(paymentCheque.getAmount()));
        Wallet to = walletService.getById(paymentCheque.getWalletTo().getId());
        to.setBalance(to.getBalance().add(paymentCheque.getAmount()));
        walletService.save(from);
        walletService.save(to);

        Cart cart = cartService.findByUser(from.getUser());
        List<OrderItem> orderItemList = orderItemService.findAllByCart_IdAndStatus(cart.getId(), Status.NOT_PURCHASED);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setStatus(Status.PURCHASED);
            orderItem.setPurchasedDate(new Date());
            orderItemService.save(orderItem);
        }
        save(paymentCheque);
        return getPaymentResponseModel(paymentCheque);
    }

    private PaymentResponseModel getPaymentResponseModel(PaymentCheque paymentCheque){
        return PaymentResponseModel.builder()
                .consumer(paymentCheque.getWalletFrom().getUser().getEmail())
//                .requisiteOfConsumer(paymentCheque.getWalletFrom().getRequisite())
//                .requisiteOfSeller(paymentCheque.getWalletTo().getRequisite())
                .purchasedDate(paymentCheque.getCreatedDate().toString())
                .amount(paymentCheque.getAmount())
//                .currency(paymentCheque.getCurrency())
                .status(paymentCheque.getStatus())
                .build();
    }


}
