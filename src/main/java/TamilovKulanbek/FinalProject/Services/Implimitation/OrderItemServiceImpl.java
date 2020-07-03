package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.*;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Repositories.OrderItemRepository;
import TamilovKulanbek.FinalProject.Services.*;
import TamilovKulanbek.FinalProject.dto.CartDto.CartItemHistoryModel;
import TamilovKulanbek.FinalProject.dto.CartDto.OrderItemModel;
import TamilovKulanbek.FinalProject.dto.CartDto.OrderItemResponseModel;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemQuantityViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;


    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getById(Long id) {
        Optional<OrderItem> cartItemOptional = orderItemRepository.findById(id);
        return cartItemOptional.orElse(null);
    }

    @Override
    public OrderItem save(OrderItem object) {
        return orderItemRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItemResponseModel create(OrderItemModel orderItemModel, String email) {
        User user = userService.findByEmail(email);
        Cart cart = cartService.findByUser(user);
        Item item = itemService.getById(orderItemModel.getItemId());
        OrderItem orderItem = OrderItem.builder()
                .cart(cart)
                .item(item)
                .unitItemPrice(item.getPrice().subtract
                        (item.getPrice().divide(new BigDecimal(100)).multiply(new BigDecimal(item.getDiscountPercentages()))))
                .itemsQuantity(orderItemModel.getItemsQuantity())
                .status(Status.NOT_PURCHASED)
                .build();
        save(orderItem);
        cart.setTotalAmount(cart.getTotalAmount().add(orderItem.getUnitItemPrice().multiply(new BigDecimal(orderItem.getItemsQuantity()))));
        cartService.save(cart);

        return OrderItemResponseModel.builder()
                .consumer(user.getEmail())
                .category(item.getCategory().getCategoryName())
                .itemName(item.getItemName())
                .itemQuantity(orderItem.getItemsQuantity())
                .price(item.getPrice())
                .discountPercentages(item.getDiscountPercentages())
                .unitItemPrice(orderItem.getUnitItemPrice())
                .totalAmount(cart.getTotalAmount())
                .status(orderItem.getStatus())
                .build();
    }

    @Override
    public List<OrderItem> findAllByCart_IdAndStatus(Long id, Status status) {
        return orderItemRepository.findAllByCart_IdAndStatus(id, status);
    }

    @Override
    public List<ItemQuantityViewModel> getItemViews(String  email) {
        User user = userService.findByEmail(email);
        Cart cart = cartService.findByUser(user);
        List<ItemQuantityViewModel> itemQuantityViewModelList = new ArrayList<>();
        List<OrderItem> orderItemList = findAllByCart_IdAndStatus(cart.getId(), Status.NOT_PURCHASED);
        for (OrderItem orderItem : orderItemList) {
            Item item = orderItem.getItem();

            ItemQuantityViewModel itemQuantityViewModel = new ItemQuantityViewModel();
            itemQuantityViewModel.setItem(item);
            itemQuantityViewModel.setQuantity(orderItem.getItemsQuantity());
            itemQuantityViewModelList.add(itemQuantityViewModel);
        }
        return itemQuantityViewModelList;
    }

    @Override
    public List<CartItemHistoryModel> getAllPurchasedCartItems(String email) {
        User user = userService.findByEmail(email);
        Cart cart = cartService.findByUser(user);
        Wallet wallet = walletService.getByUser(user);
        List<CartItemHistoryModel> cartItemHistoryModels = new ArrayList<>();

        List<OrderItem> orderItems = findAllByCart_IdAndStatus(cart.getId(), Status.PURCHASED);


        for (OrderItem c : orderItems) {
            CartItemHistoryModel cartItemHistoryModel = new CartItemHistoryModel();
            cartItemHistoryModel.setItemName(c.getItem().getItemName());
            cartItemHistoryModel.setItemQuantity(c.getItemsQuantity());
            cartItemHistoryModel.setStatus(c.getStatus());
            cartItemHistoryModel.setAmount(c.getUnitItemPrice().multiply(new BigDecimal(c.getItemsQuantity())));
            cartItemHistoryModel.setPurchasedDate(c.getPurchasedDate().toString());
            cartItemHistoryModels.add(cartItemHistoryModel);
        }
        return cartItemHistoryModels;
    }


}