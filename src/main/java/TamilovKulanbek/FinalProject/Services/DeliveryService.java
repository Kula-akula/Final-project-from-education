package TamilovKulanbek.FinalProject.Services;

import TamilovKulanbek.FinalProject.Entities.Delivery;
import TamilovKulanbek.FinalProject.Exception.RejectionNoMoneyException;
import TamilovKulanbek.FinalProject.Exception.WrongOrderException;
import TamilovKulanbek.FinalProject.Models.DeliverResponseModel;

public interface DeliveryService extends BaseService<Delivery> {
    DeliverResponseModel createDelivery (String login) throws WrongOrderException, RejectionNoMoneyException;
}
