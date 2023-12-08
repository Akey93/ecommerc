package proggetto.proggettoeco.UTILITY.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class AddProductInCartRequest {

    private String codeProduct;
    private int quantity;
    private String email;

    
}
