package TamilovKulanbek.FinalProject.Entities;

import TamilovKulanbek.FinalProject.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    Item item;

    @Column(name = "unit_item_price")
    BigDecimal unitItemPrice;

    @Column(name = "items_quantity")
    Integer itemsQuantity;

    @Column(name = "purchased_date")
    Date purchasedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;
}