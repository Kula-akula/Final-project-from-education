package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Exception.WrongBalanceException;
import TamilovKulanbek.FinalProject.dto.CartDto.CartItemHistoryModel;
import TamilovKulanbek.FinalProject.dto.CartDto.CartModel;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemQuantityViewModel;
import TamilovKulanbek.FinalProject.Services.OrderItemService;
import TamilovKulanbek.FinalProject.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/cart")
public class CartController {
    private final OrderItemService orderItemService;

    private final CartService cartService;

    @Autowired
    public CartController(OrderItemService orderItemService, CartService cartService) {
        this.orderItemService = orderItemService;
        this.cartService = cartService;
    }
//
//    @GetMapping
//    public List<Cart> getAll(){
//        return cartService.getAll();
//    }
//
//    @PostMapping
//    public Cart save(@RequestBody Cart cart){
//        return cartService.save(cart);
//    }
//
//    @GetMapping("/{id}")
//    public Cart getById (@PathVariable("id") Long id){
//        return cartService.getById(id);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteOrder(@PathVariable("id") Long id) {
//        cartService.deleteById(id);
//    }

    @GetMapping
    public List<ItemQuantityViewModel> getAll(Principal principal){

        return cartService.showItemViews(principal.getName());
    }
    @GetMapping("/total")
    public CartModel getAmount(Principal principal){
        return cartService.cartTotalAmount(principal.getName());
    }

    @GetMapping("/purchase")
    public ResponseEntity<?> buyItems(Principal principal) throws WrongBalanceException {
//        principal = SecurityContextHolder.getContext().getAuthentication();
        try {
            return new ResponseEntity<>(cartService.buy(principal.getName()), HttpStatus.OK);
        }catch (WrongBalanceException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history")
    private ResponseEntity<List<CartItemHistoryModel>> getHistory(Principal principal){
        return new ResponseEntity<>(orderItemService.getAllPurchasedCartItems(principal.getName()), HttpStatus.OK);
    }
}
