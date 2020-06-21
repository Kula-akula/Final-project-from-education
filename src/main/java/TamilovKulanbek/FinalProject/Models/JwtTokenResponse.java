package TamilovKulanbek.FinalProject.Models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class JwtTokenResponse implements Serializable {
    private static final long serialVersionUID = 8317676219297719109L;

    String token;

    public String getToken() {
        return token;
    }
}
