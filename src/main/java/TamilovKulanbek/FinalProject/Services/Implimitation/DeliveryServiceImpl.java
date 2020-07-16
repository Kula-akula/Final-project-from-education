package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Delivery;
import TamilovKulanbek.FinalProject.Enums.Currency;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Exception.RejectionNoMoneyException;
import TamilovKulanbek.FinalProject.Exception.WrongOrderException;
import TamilovKulanbek.FinalProject.dto.DeliveryDto.DeliverResponseModel;
import TamilovKulanbek.FinalProject.Repositories.DeliveryRepository;
import TamilovKulanbek.FinalProject.Services.OrderItemService;
import TamilovKulanbek.FinalProject.Services.CartService;
import TamilovKulanbek.FinalProject.Services.DeliveryService;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.dto.DeliveryDto.DeliveryCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public DeliveryCreateModel createDelivery(String shopName) throws WrongOrderException, RejectionNoMoneyException {
        Delivery delivery = deliveryRepository.findByShopShopName(shopName);
        return DeliveryCreateModel.builder()
                .consumer(delivery.getShop().getShopName())
                .requisiteOfConsumer(delivery.getShop().getShopAddress())
                .requisiteOfSeller(delivery.getCompany().getName())
                .amount(delivery.getOrderItem().getCart().getTotalAmount())
                .currency(Currency.MONEY)
                .purchasedDate(new Date())
                .status(Status.NOT_DELIVERED)
                .build();
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getById(Long id) {
        Optional<Delivery> optionalDelivery=deliveryRepository.findById(id);
        return optionalDelivery.orElse(null);
    }

    @Override
    public Delivery save(Delivery object) {
        return deliveryRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        deliveryRepository.deleteById(id);

    }
}
