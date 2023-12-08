package proggetto.proggettoeco.UTILITY.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import proggetto.proggettoeco.entities.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private String firstName;
    private String surname;

    public UserDTO(User u){

        this.firstName=u.getFirstName();
        this.surname=u.getSurname();
    
    }
}
