package proggetto.proggettoeco.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proggetto.proggettoeco.entities.Product;
import proggetto.proggettoeco.entities.User;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long>{

    Product findByCodeProduct (String codeProduct);
    List<Product> findByUser(User user);
    boolean existsByCodeProduct (String codeProduct);
    Page<Product> findByType(Pageable pageable,String type);

    
}
