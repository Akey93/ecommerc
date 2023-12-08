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
import proggetto.proggettoeco.entities.Product;
import proggetto.proggettoeco.services.JwtService;
import proggetto.proggettoeco.services.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

public class ProductController {

    final ProductService productService;
    final JwtService jwtService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody Product p, HttpServletRequest request){
        try {
            String email=jwtService.extractUserEmailByRequest(request);
            return new ResponseEntity(productService.addProduct(p,email),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/addQuantityProduct")
    public ResponseEntity addQuantityProduct(@RequestBody Product p){
        try {
            return new ResponseEntity(productService.addQuantityProduct(p),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/modify")
    public ResponseEntity modifyProduct(@RequestBody Product p){
        try {
            return new ResponseEntity(productService.modifyProduct(p),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/removeProduct")
    public ResponseEntity removeProduct(@RequestParam ("code")String code){
        try {
            productService.removeProduct(code);
            return new ResponseEntity("Il prodotto è stato rimosso",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        } 
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/findProduct")
    public ResponseEntity findProductByCode(@RequestParam("codeProduct")String codeProduct){
        try {
            return new ResponseEntity(productService.getProduct(codeProduct),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/productPage")
    public ResponseEntity getProductPage(@RequestParam("nPage") int nPage, @RequestParam ("dPage") int dPage){
        try {
            return new ResponseEntity(productService.getAllPage(nPage, dPage),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllByTypo")
    public ResponseEntity getProductByTipo (@RequestParam("nPage")int nPage,@RequestParam("dPage")int dPage, @RequestParam("typo") String type){
        try {
            return new ResponseEntity(productService.getProductByTipe(nPage,dPage,type.toUpperCase()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllProduct")
    public ResponseEntity getAllProduct(){
        try {
            return new ResponseEntity(productService.getAllProduct(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass().getSimpleName(),HttpStatus.BAD_REQUEST);
        }

    }
    
    
}
