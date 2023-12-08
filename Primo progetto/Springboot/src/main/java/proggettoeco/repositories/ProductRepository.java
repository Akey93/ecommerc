package proggetto.proggettoeco.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proggetto.proggettoeco.entities.Product;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long>{

    Product findByCodeProduct (String codeProduct);
    boolean existsByCodeProduct (String codeProduct);
    Page<Product> findByType(Pageable pageable,String type);

    
}
