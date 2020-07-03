package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.OrderItem;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.dto.CartDto.CartItemHistoryModel;
import TamilovKulanbek.FinalProject.dto.CartDto.OrderItemModel;
import TamilovKulanbek.FinalProject.dto.CartDto.OrderItemResponseModel;
import TamilovKulanbek.FinalProject.dto.ItemDto.ItemQuantityViewModel;

import java.util.List;

public interface OrderItemService extends BaseService<OrderItem> {
    OrderItemResponseModel create(OrderItemModel orderItemModel, String email);

    List<OrderItem> findAllByCart_IdAndStatus(Long id, Status status);

    List<ItemQuantityViewModel> getItemViews(String email);

    List<CartItemHistoryModel> getAllPurchasedCartItems(String email);
}
