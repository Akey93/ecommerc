package proggetto.proggettoeco.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proggetto.proggettoeco.UTILITY.objects.Role;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import proggetto.proggettoeco.UTILITY.dto.UserTokenDTO;
import proggetto.proggettoeco.UTILITY.objects.AuthenticationRequest;
import proggetto.proggettoeco.UTILITY.objects.ModifyRequest;
import proggetto.proggettoeco.UTILITY.objects.RegisterRequest;
import proggetto.proggettoeco.entities.User;
import proggetto.proggettoeco.services.JwtService;
import proggetto.proggettoeco.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    final JwtService jwtService;
    final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity registerUser(@RequestBody RegisterRequest request){
        try {
            return new ResponseEntity(userService.registerUser(request),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addAdmin")
    public ResponseEntity registerAdmin(@RequestBody RegisterRequest request){
        try {
            return new ResponseEntity(userService.registerAdmin(request),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest request){
        try {
            return new ResponseEntity(userService.authenticate(request),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/modify")
    public ResponseEntity modifyNS(HttpServletRequest request, @RequestBody ModifyRequest u){
        try {
            return new ResponseEntity(userService.modifyNS(request, u),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/modifyP")
    public ResponseEntity modifyP(HttpServletRequest request,@RequestParam("oldPassword")String oldPassword,@RequestParam("password") String Password){
        try {
            return new ResponseEntity(userService.modifyP(request, oldPassword, Password),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/modifyPByAdmin")
    public ResponseEntity modifyPByAdmin(@RequestBody AuthenticationRequest requestL, HttpServletRequest request,@RequestParam("email")String email,@RequestParam("password") String Password){
        try {
            return new ResponseEntity(userService.modifyPByAdmin(requestL,request, email, Password),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody AuthenticationRequest requestB, HttpServletRequest request){
        try {
            userService.removeUser(requestB,request);
            return new ResponseEntity("Account rimosso con successo",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/ban")
    public ResponseEntity ban(@RequestBody AuthenticationRequest requestL, HttpServletRequest request,@RequestParam("email")String email){
        try {
            userService.ban(requestL,request, email);
            return new ResponseEntity("Account rimosso",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/findUser")
    public ResponseEntity findUser(@RequestParam("email")String email){
        try {
            return new ResponseEntity(userService.findUser(email),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/findUserM")
    public ResponseEntity findUserM(HttpServletRequest request){
        try {
            String email = jwtService.extractUserEmailByRequest(request);
            return new ResponseEntity(userService.findUser(email),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("/verificaAuthority")
    public Role verificaAuthority(HttpServletRequest request){
        return userService.ruolo(request);
    }
    @GetMapping("/userTokenDTO")
    public UserTokenDTO TokenDTO(@RequestParam("email")String email){
        String token = userService.token(email).getToken();
       return userService.DTOToken(email, token);

    }
}