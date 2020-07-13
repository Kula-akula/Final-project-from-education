package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.OrderItem;
import TamilovKulanbek.FinalProject.Services.OrderItemService;
import TamilovKulanbek.FinalProject.dto.CartDto.OrderItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> getAll(){
        return orderItemService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> putIntoCart(@RequestBody OrderItemModel orderItemModel, Principal principal){
        try {
            return new ResponseEntity<>(orderItemService.create(orderItemModel, principal.getName()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
