package proggetto.proggettoeco.services;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.var;
import proggetto.proggettoeco.UTILITY.dto.UserTokenDTO;
import proggetto.proggettoeco.UTILITY.exceptions.DataNotCorrectEcxeption;
import proggetto.proggettoeco.UTILITY.exceptions.InsufficientMoneyException;
import proggetto.proggettoeco.UTILITY.exceptions.UserAlreadyExistException;
import proggetto.proggettoeco.UTILITY.exceptions.UserDoesNotExistException;
import proggetto.proggettoeco.UTILITY.objects.AuthenticationRequest;
import proggetto.proggettoeco.UTILITY.objects.AuthenticationResponse;
import proggetto.proggettoeco.UTILITY.objects.ModifyRequest;
import proggetto.proggettoeco.UTILITY.objects.RegisterRequest;
import proggetto.proggettoeco.UTILITY.objects.Role;
import proggetto.proggettoeco.entities.User;
import proggetto.proggettoeco.repositories.UserRepository;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public boolean controllRegex(User u) {
        String nome = "^[a-zA-Z\s]+$";
        String cognome = "^[a-zA-Z\s]+$";
        String email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean a = u.getFirstName().matches(nome);
        boolean b = u.getSurname().matches(cognome);
        boolean c = u.getEmail().matches(email);
        return a && b && c;
    }

    public boolean ModifyRegex(ModifyRequest u) {
        String nome = "^[a-zA-Z\s]+$";
        String cognome = "^[a-zA-Z\s]+$";
        String email="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean a = u.getFirstName().matches(nome);
        boolean b = u.getSurname().matches(cognome);
          boolean c = u.getEmail().matches(email);
        return a && b && c;
    }

    public UserTokenDTO registerUser(RegisterRequest request) throws RuntimeException {
        User u = new User();
        u.setFirstName(request.getFirstName().toUpperCase());
        u.setSurname(request.getSurname().toUpperCase());
        u.setEmail(request.getEmail().toLowerCase());
        u.setIndirizzo(request.getIndirizzo().toLowerCase());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setRole(Role.USER);
        boolean e = userRepository.existsByEmail(u.getEmail());
        if (controllRegex(u)) {
            if (!e) {
                userRepository.save(u);
                var jwtToken = jwtService.generateToken(u);
                UserTokenDTO userTokenDTO = DTOToken(u.getEmail(), jwtToken);
                return userTokenDTO;
            }
            throw new UserAlreadyExistException();
        }
        throw new DataNotCorrectEcxeption();
    }

    public UserTokenDTO registerAdmin(RegisterRequest request) {
        User u = new User();
        u.setFirstName(request.getFirstName().toUpperCase());
        u.setSurname(request.getSurname().toUpperCase());
        u.setEmail(request.getEmail().toLowerCase());
        u.setIndirizzo(request.getIndirizzo().toLowerCase());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setRole(Role.ADMIN);
        boolean e = userRepository.existsByEmail(u.getEmail());
        if (controllRegex(u)) {
            if (!e) {
                userRepository.save(u);
                var jwtToken = jwtService.generateToken(u);
                UserTokenDTO userTokenDTO = DTOToken(u.getEmail(), jwtToken);
                return userTokenDTO;
            }
            throw new UserAlreadyExistException();
        }
        throw new DataNotCorrectEcxeption();
    }

    public UserTokenDTO authenticate(AuthenticationRequest request) throws RuntimeException {
        boolean verifica = userRepository.findByEmail(request.getEmail().toLowerCase()) != null;
        if (verifica) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail().toLowerCase(), request.getPassword()));
            User u = userRepository.findByEmail(request.getEmail().toLowerCase());
            var jwtToken = jwtService.generateToken(u);
            UserTokenDTO userTokenDTO = DTOToken(u.getEmail(), jwtToken);
            return userTokenDTO;
        }
        throw new DataNotCorrectEcxeption();

    }

    public UserTokenDTO modify(HttpServletRequest request, ModifyRequest u) throws RuntimeException {
        String email = jwtService.extractUserEmailByRequest(request).toLowerCase();
        System.out.println("email del token"+email+ "\n email da modificare "+u.getEmail());
        if (email != null) {
            User p = userRepository.findByEmail(email);
            if (ModifyRegex(u)) {
                p.setFirstName(u.getFirstName().toUpperCase().strip());
                p.setSurname(u.getSurname().toUpperCase().strip());
                p.setEmail(u.getEmail().toLowerCase().strip());
                System.out.println(p.getEmail());
                userRepository.save(p);
                var jwtToken = jwtService.generateToken(p);
                UserTokenDTO userTokenDTO = DTOToken(p.getEmail(), jwtToken);
                return userTokenDTO;
            }
            throw new DataNotCorrectEcxeption();
        }
        throw new DataNotCorrectEcxeption();
    }

    public UserTokenDTO modifyP(HttpServletRequest request, String oldPassword, String password)
            throws RuntimeException {
        String email = jwtService.extractUserEmailByRequest(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        if (email != null) {
            User p = userRepository.findByEmail(email.toLowerCase());
            p.setPassword(passwordEncoder.encode(password));
            userRepository.save(p);
            var jwtToken = jwtService.generateToken(p);
            UserTokenDTO userTokenDTO = DTOToken(p.getEmail(), jwtToken);
            return userTokenDTO;
        }
        throw new DataNotCorrectEcxeption();
    }

    public User modifyPByAdmin(AuthenticationRequest requestL, HttpServletRequest request, String email,
            String password) throws RuntimeException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestL.getEmail().toLowerCase(), requestL.getPassword()));
        boolean s = userRepository.existsByEmail(email.toLowerCase());
        if (s) {
            User u = userRepository.findByEmail(email.toLowerCase());
            u.setPassword(passwordEncoder.encode(password));
            return userRepository.save(u);
        }
        throw new DataNotCorrectEcxeption();

    }

    public void removeUser(AuthenticationRequest requestB, HttpServletRequest request) throws RuntimeException {
        User u = userRepository.findByEmail(requestB.getEmail().toLowerCase());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestB.getEmail().toLowerCase(), requestB.getPassword()));
        if (u != null) {
            userRepository.delete(u);
            return;
        }
        throw new UserDoesNotExistException();
    }

    public void ban(AuthenticationRequest requestL, HttpServletRequest request, String email) throws RuntimeException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestL.getEmail().toLowerCase(), requestL.getPassword()));
        User u = userRepository.findByEmail(email.toLowerCase());
        if (u != null) {
            userRepository.delete(u);
            return;
        }
        throw new UserDoesNotExistException();
    }

    public User findUser(String email) throws RuntimeException {
        User u = userRepository.findByEmail(email.toLowerCase());
        if (u != null) {
            return u;
        }
        throw new UserDoesNotExistException();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Role ruolo(HttpServletRequest request) {
        String email = jwtService.extractUserEmailByRequest(request);
        User u = userRepository.findByEmail(email);
        return u.getRole();
    }

    public UserTokenDTO DTOToken(String email, String token) {
        User u = userRepository.findByEmail(email.toLowerCase());
        UserTokenDTO c = new UserTokenDTO();
        c.setEmail(u.getEmail());
        c.setRuolo(u.getRole());
        c.setToken(token);
        return c;
    }

    public AuthenticationResponse token(String email) {
        User u = userRepository.findByEmail(email.toLowerCase());
        var jwtToken = jwtService.generateToken(u);
        return new AuthenticationResponse(jwtToken);
    }
    public double preleva(User u, double soldi)throws RuntimeException{
        if(u.getMoney()-soldi>=0){
            u.setMoney(u.getMoney()-soldi);
            userRepository.save(u);
            return u.getMoney();
        }throw new InsufficientMoneyException();
    }
    public double ricarica(User u, double soldi){
        u.setMoney(soldi+u.getMoney());
        userRepository.save(u);      
        return u.getMoney();
    }
}
