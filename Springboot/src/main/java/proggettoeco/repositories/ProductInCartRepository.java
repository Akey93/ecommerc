package proggetto.proggettoeco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proggetto.proggettoeco.entities.Product;
import proggetto.proggettoeco.entities.ProductInCart;
import proggetto.proggettoeco.entities.User;

public interface ProductInCartRepository extends JpaRepository<ProductInCart,Long>{

    List<ProductInCart> findByProduct (Product product);
    List<ProductInCart> findByUser (User user);
    ProductInCart findByUserAndProduct (User user, Product product);
    List<User> findUsersByProduct (Product product);
    
}
