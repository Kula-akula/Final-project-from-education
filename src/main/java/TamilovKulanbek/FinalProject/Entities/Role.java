package TamilovKulanbek.FinalProject.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<Company> companies;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<Shop> shops;

//    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    private List<Company> companies;

//    @Column
//    boolean isActive;
}
