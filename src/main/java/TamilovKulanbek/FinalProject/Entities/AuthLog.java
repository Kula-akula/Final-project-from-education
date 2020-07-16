package TamilovKulanbek.FinalProject.Entities;



import TamilovKulanbek.FinalProject.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "auth_logs")
public class AuthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "updated_date")
    LocalDateTime updatedDate;

    @PostPersist
    public void createdDate(){
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void updatedDate(){
        updatedDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    Company company;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    Shop shop;



    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @Column(name = "is_recovery")
    Integer isRecovered;


}
