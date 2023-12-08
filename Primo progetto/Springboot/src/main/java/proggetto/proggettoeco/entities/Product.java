package proggetto.proggettoeco.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name= "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Column(name= "name_product",nullable = false)
    private String nameProduct;
    @Column(name= "code_product",nullable = false,unique = true)
    private String codeProduct;
    @Column(name="price",nullable = false)
    private double price;
    @Column(name="quantity",nullable= false)
    private int quantity;
    @Column(name="type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name="user_admin")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    
}
