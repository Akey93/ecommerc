package proggetto.proggettoeco.UTILITY.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proggetto.proggettoeco.UTILITY.objects.Role;
import proggetto.proggettoeco.entities.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserTokenDTO {

    
    private String email;
    private Role ruolo;
    private String token;
    
    
    public UserTokenDTO(User u){

        this.email=u.getEmail();
        this.ruolo=u.getRole();
        this.token=token;
        
    
    }

    
}
