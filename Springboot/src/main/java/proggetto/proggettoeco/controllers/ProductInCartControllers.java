package proggetto.proggettoeco.controllers;

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

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import proggetto.proggettoeco.UTILITY.dto.AddProductInCartRequest;
import proggetto.proggettoeco.services.JwtService;
import proggetto.proggettoeco.services.ProductInCartService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor

public class ProductInCartControllers {

    final ProductInCartService productInCartService;
    final JwtService jwtService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/addProductToCart")
    public ResponseEntity addProductToCart( @RequestBody AddProductInCartRequest DtoPic) {
        try {

            return new ResponseEntity(productInCartService.addProductToCartService(DtoPic),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/buyProduct")
    public ResponseEntity buyProduct(HttpServletRequest request, @RequestParam("code")String code) {
        try {
     
            String email= jwtService.extractUserEmailByRequest(request);
            return new ResponseEntity(productInCartService.buyProductInCart(email, code, true),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @DeleteMapping("/removeProductToCart")
    public ResponseEntity removeProductToCart(HttpServletRequest request, @RequestParam("codeProduct")String code) {
        try {
            String email= jwtService.extractUserEmailByRequest(request);
            productInCartService.removeProductToCart(email, code);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/getAllCart")
    public ResponseEntity carrello(@RequestParam("email") String email) {
        try {
            return new ResponseEntity(productInCartService.getCart( email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/buyAllCart")
    public ResponseEntity buyAllProductInCart( @RequestParam("email") String email) {
        try {
            productInCartService.buyAllProductInCart(email);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/modifyQP")
    public ResponseEntity modifyQP(@RequestBody AddProductInCartRequest DtoPic) {
        
        try {
            return new ResponseEntity( productInCartService.modifiQ(DtoPic),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }

    }

}
