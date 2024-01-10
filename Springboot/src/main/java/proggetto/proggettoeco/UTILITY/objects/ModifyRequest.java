package proggetto.proggettoeco.UTILITY.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ModifyRequest {

    private String firstName;
    private String surname;
    private String email;
    
    
}
