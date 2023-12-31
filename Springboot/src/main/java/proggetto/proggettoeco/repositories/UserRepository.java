package proggetto.proggettoeco.repositories;


import proggetto.proggettoeco.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);
    User findByEmail (String email);
    boolean existsByEmail(String email);  
    
}
