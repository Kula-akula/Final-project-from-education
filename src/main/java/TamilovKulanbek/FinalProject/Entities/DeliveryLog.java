package TamilovKulanbek.FinalProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "delivery_log")
public class DeliveryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "delivery_logs",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "company_id", nullable = false)}
    )
    List<Delivery> logs;

    @Column(name = "total_amount")
    BigDecimal totalAmount;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    User user;
//
//    @ManyToOne
//    @JoinColumn(name = "company_id",referencedColumnName = "id")
//    Company company;






}
