package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Cart;
import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Exception.RejectionNoMoneyException;
import TamilovKulanbek.FinalProject.Exception.WrongBalanceException;
import TamilovKulanbek.FinalProject.Exception.WrongOrderException;
import TamilovKulanbek.FinalProject.Models.DeliverResponseModel;
import TamilovKulanbek.FinalProject.Models.ResponseMessage;
import TamilovKulanbek.FinalProject.dto.CartDto.CartModel;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemQuantityViewModel;
import TamilovKulanbek.FinalProject.dto.WalletDto.PaymentResponseModel;

import java.util.List;

public interface CartService {
    Cart save(Cart cart);

    Cart findByUser(User user);

    List<ItemQuantityViewModel> showItemViews(String email);

    CartModel cartTotalAmount(String email);


    PaymentResponseModel buy(String email) throws WrongBalanceException;

//    DeliverResponseModel delivery (String login) throws WrongOrderException, RejectionNoMoneyException;
}
