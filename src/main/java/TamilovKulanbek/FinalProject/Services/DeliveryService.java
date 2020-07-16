package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Delivery;
import TamilovKulanbek.FinalProject.Exception.RejectionNoMoneyException;
import TamilovKulanbek.FinalProject.Exception.WrongOrderException;
import TamilovKulanbek.FinalProject.dto.DeliveryDto.DeliverResponseModel;
import TamilovKulanbek.FinalProject.dto.DeliveryDto.DeliveryCreateModel;

public interface DeliveryService extends BaseService<Delivery> {
    DeliveryCreateModel createDelivery (String shopName) throws WrongOrderException, RejectionNoMoneyException;
}
