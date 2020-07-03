package TamilovKulanbek.FinalProject.Models;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
public class ResponseMessage {
    String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}

