package TamilovKulanbek.FinalProject.dto.userDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {
    String email;
    String password;
    String phoneNumber;
    String firstName;
    String lastName;
    String address;
}
