package TamilovKulanbek.FinalProject.dto.userDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserFindModel {
    String firstName;
    String lastName;
}
