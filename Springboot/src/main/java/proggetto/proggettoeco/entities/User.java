package proggetto.proggettoeco.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import proggetto.proggettoeco.UTILITY.objects.Role;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "users")


public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Column(name="name",nullable = false)
    private String firstName;
    @Column(name="surname",nullable = false)
    private String surname;
    @Column(name="email",nullable = false, unique = true)
    private String email;
    @Column(name="indirizzo",nullable=false)
    private String indirizzo;    

    
    private String password;

    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "cart_id")
    private List<ProductInCart> cart = new ArrayList<>();

    @Column(name="money",nullable = false)
    private double money = 500;


    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name= "Product_add")
    private List<Product> scaffale = new ArrayList<>();

    

    
    @Enumerated(EnumType.STRING)
    private Role role;

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
