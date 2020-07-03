package TamilovKulanbek.FinalProject.Entities;

import TamilovKulanbek.FinalProject.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "item_type")
    String type;

    @Column(name = "item_status")
    Status status;

    @Column(name = "item_name")
    String itemName;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    Company company;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "discount_percentages")
    Integer discountPercentages;

    @ManyToMany(mappedBy = "items")
    List<Set> sets;

}
