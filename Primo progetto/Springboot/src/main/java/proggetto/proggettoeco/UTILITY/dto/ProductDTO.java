package proggetto.proggettoeco.UTILITY.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proggetto.proggettoeco.entities.Product;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ProductDTO {

    private String nameProduct;
    private String type;
    private int quantity;
    private double priceProduct;

    public ProductDTO(Product p){

        this.nameProduct=p.getNameProduct();
        this.type=p.getType();
        this.quantity=p.getQuantity();
        this.priceProduct=p.getPrice();
    
    }
    
}
