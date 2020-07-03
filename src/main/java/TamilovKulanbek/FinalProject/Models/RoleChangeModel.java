package TamilovKulanbek.FinalProject.Models;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleChangeModel {
    private String email;
    private Long roleId;
}
