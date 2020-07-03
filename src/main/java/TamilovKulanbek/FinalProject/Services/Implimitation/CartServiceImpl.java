package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Cart;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Exception.WrongBalanceException;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.Services.PaymentChequeService;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.dto.CartDto.CartModel;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemQuantityViewModel;
import TamilovKulanbek.FinalProject.Repositories.CartRepository;
import TamilovKulanbek.FinalProject.Services.OrderItemService;
import TamilovKulanbek.FinalProject.Services.CartService;
import TamilovKulanbek.FinalProject.dto.WalletDto.PaymentResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentChequeService paymentChequeService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public List<ItemQuantityViewModel> showItemViews(String email) {
        return orderItemService.getItemViews(email);
    }

    @Override
    public CartModel cartTotalAmount(String email) {
        User user= userService.findByEmail(email);
        Cart cart=findByUser(user);

        return CartModel.builder()
                .userId(cart.getId())
                .totalAmount(cart.getTotalAmount())
                .build();
    }

    @Override
    public PaymentResponseModel buy(String email) throws WrongBalanceException {
        return paymentChequeService.createPayment(email);
    }
//    @Override
//    public DeliverResponseModel delivery(String login) throws WrongOrderException, RejectionNoMoneyException {
//        return paymentChequeService.createPayment(login);
//
//    }


}