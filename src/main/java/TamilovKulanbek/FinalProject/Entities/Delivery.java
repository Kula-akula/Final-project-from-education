package TamilovKulanbek.FinalProject.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "shops_id", referencedColumnName = "id")
    Shop shop;

    @ManyToOne
    @JoinColumn(name = "order_items_id", referencedColumnName = "id")
    OrderItem orderItem;

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "is_deliver")
    Boolean deliver;

    @Column(name = "deliver_time" )
    Date deliverDate=new Date();
}
