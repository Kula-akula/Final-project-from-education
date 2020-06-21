package TamilovKulanbek.FinalProject.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "shop_name")
    String shopName;

    @Column(name = "shop_address")
    String shopAddress;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    User user;


}
