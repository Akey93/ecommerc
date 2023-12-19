package proggetto.proggettoeco.services;





import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import proggetto.proggettoeco.UTILITY.dto.PageDTO;
import proggetto.proggettoeco.UTILITY.dto.ProductDTO;
import proggetto.proggettoeco.UTILITY.exceptions.CodeProductNotCorrectException;
import proggetto.proggettoeco.UTILITY.exceptions.PriceAndQuantityCannotBeLessZeroException;
import proggetto.proggettoeco.UTILITY.exceptions.PriceOrNameSetDifferentException;
import proggetto.proggettoeco.UTILITY.exceptions.ProductAlreadyExistException;
import proggetto.proggettoeco.UTILITY.exceptions.ProductDoesNotExistException;
import proggetto.proggettoeco.UTILITY.exceptions.TypeNotFoundException;
import proggetto.proggettoeco.UTILITY.exceptions.UserDoesNotExistException;
import proggetto.proggettoeco.entities.Product;
import proggetto.proggettoeco.entities.User;
import proggetto.proggettoeco.repositories.ProductRepository;
import proggetto.proggettoeco.repositories.UserRepository;

@Service
@RequiredArgsConstructor

public class ProductService {

    final ProductRepository productRepository;
    final JwtService jwtService;
    final UserRepository userRepository;

    public boolean controll(Product p){
        boolean c = p.getQuantity()>=0;
        boolean a = p.getPrice()>=0;

        return a&&c;
    }
    public ProductDTO addProduct(Product p, String email)throws RuntimeException{
         if(p.getUrl()==null){
            p.setUrl("");
        }
        if(p.getDescrizione()==null){
            p.setDescrizione("Nessuna Descrizione");
        }

        boolean c=productRepository.existsByCodeProduct(p.getCodeProduct().toUpperCase());
        
        User u = userRepository.findByEmail(email);
        if(!c){
            if(controll(p)){
                p.setCodeProduct(p.getCodeProduct().toUpperCase().strip());
                p.setUrl(p.getUrl());
                p.setDescrizione(p.getDescrizione());
                p.setNameProduct(p.getNameProduct().toUpperCase().strip());
                p.setType(p.getType().toUpperCase().strip());
                p.setUser(u);
                productRepository.save(p);
                ProductDTO pDTO = new ProductDTO(p);
                return pDTO;
            }throw new PriceAndQuantityCannotBeLessZeroException();
        }throw new ProductAlreadyExistException();
    }
    public Product setQuantityProduct(Product p)throws RuntimeException{
        Product d = productRepository.findByCodeProduct(p.getCodeProduct().toUpperCase());
        if(d!=null){
            boolean a= p.getNameProduct().toUpperCase().equals(d.getNameProduct());
            boolean b = p.getPrice()==d.getPrice();
            if(a&&b){
                d.setQuantity(p.getQuantity());
                if(d.getQuantity()>=0){
                    return productRepository.save(d);
                }throw new PriceAndQuantityCannotBeLessZeroException();
            }throw new PriceOrNameSetDifferentException();
        }throw new CodeProductNotCorrectException();
    }
    public Product modifyProduct(Product p)throws RuntimeException{
        Product d = productRepository.findByCodeProduct(p.getCodeProduct().toUpperCase());
        if(d!=null){
            if(controll(p)){
                d.setDescrizione(p.getDescrizione());
                d.setUrl(p.getUrl());
                d.setNameProduct(p.getNameProduct().toUpperCase());
                d.setPrice(p.getPrice());
                d.setType(p.getType().toUpperCase());
                d.setQuantity(p.getQuantity());
                return productRepository.save(d); 
            }throw new PriceAndQuantityCannotBeLessZeroException();
        }throw new CodeProductNotCorrectException();
    }
    public void removeProduct(String code)throws RuntimeException{
        Product d = productRepository.findByCodeProduct(code.toUpperCase());
        if(d!=null){
            productRepository.delete(d); 
            return;
        }throw new ProductDoesNotExistException();
    }
    public Product getProduct(String codeProduct)throws RuntimeException{
        Product p = productRepository.findByCodeProduct(codeProduct);
        if(p!=null){
            return p;
        }throw new ProductDoesNotExistException();
    }
    public Page<Product> getAllPage(PageDTO pageDTO){
        int nPage= pageDTO.getNPage();
        int dPage=pageDTO.getDPage();
        PageRequest pageable = PageRequest.of(nPage,dPage);
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }
    public Page<Product> getProductByTipe(int nPage, int dPage, String type)throws RuntimeException{
        PageRequest pageable = PageRequest.of(nPage,dPage);
        Page<Product> products = productRepository.findByType(pageable, type);
        if(products!=null){
            return products;
        }throw new TypeNotFoundException();
    }
    public List<Product> getAllProduct(){
        
        return productRepository.findAll();
    }
    public List<Product> getProductsByEmail(String email)throws RuntimeException{
        User user= userRepository.findByEmail(email.toLowerCase());
        if(email==null){
            throw new UserDoesNotExistException();
        }
        List<Product> listaProdotti= productRepository.findByUser(user);
        return listaProdotti;  
    }
    public String getProductUrl(String codeProduct)throws RuntimeException{
        Product product= productRepository.findByCodeProduct(codeProduct);
        if(product==null){
            throw new ProductDoesNotExistException();
        }
         String url= product.getUrl();
         return url;
    }
}
