package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Delivery;
import TamilovKulanbek.FinalProject.Exception.RejectionNoMoneyException;
import TamilovKulanbek.FinalProject.Exception.WrongOrderException;
import TamilovKulanbek.FinalProject.Models.DeliverResponseModel;
import TamilovKulanbek.FinalProject.Repositories.DeliveryRepository;
import TamilovKulanbek.FinalProject.Services.OrderItemService;
import TamilovKulanbek.FinalProject.Services.CartService;
import TamilovKulanbek.FinalProject.Services.DeliveryService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliverResponseModel deliverResponseModel;
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public DeliverResponseModel createDelivery(String login) throws WrongOrderException, RejectionNoMoneyException {
        return deliverResponseModel;
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
