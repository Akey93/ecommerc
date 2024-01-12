package proggetto.proggettoeco.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import proggetto.proggettoeco.UTILITY.exceptions.InsufficientMoneyException;
import proggetto.proggettoeco.UTILITY.dto.AddProductInCartRequest;
import proggetto.proggettoeco.UTILITY.exceptions.InsufficientQuantityException;
import proggetto.proggettoeco.UTILITY.exceptions.ProductDoesNotExistException;
import proggetto.proggettoeco.UTILITY.exceptions.ProductDoesNotInCartException;
import proggetto.proggettoeco.UTILITY.exceptions.QuantityCannotBeLess0Exception;
import proggetto.proggettoeco.UTILITY.exceptions.UserDoesNotExistException;
import proggetto.proggettoeco.entities.Product;
import proggetto.proggettoeco.entities.ProductInCart;
import proggetto.proggettoeco.entities.User;
import proggetto.proggettoeco.repositories.ProductInCartRepository;
import proggetto.proggettoeco.repositories.ProductRepository;
import proggetto.proggettoeco.repositories.UserRepository;

@Service
@RequiredArgsConstructor

public class ProductInCartService {

    final ProductInCartRepository productInCartRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;

    public boolean controll(String email, String code, boolean verificaPic) throws RuntimeException {
        User user = userRepository.findByEmail(email.toLowerCase());
        Product product = productRepository.findByCodeProduct(code.toUpperCase());
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        if (product == null) {
            throw new ProductDoesNotExistException();
        }
        if (verificaPic) {
            if (productInCartRepository.findByUserAndProduct(user, product) == null) {
                throw new ProductDoesNotInCartException();
            }
            return true;
        }
        return true;
    }

    @Transactional
    public List<ProductInCart> addProductToCartService(AddProductInCartRequest dtoPic)
            throws RuntimeException {
        String code = dtoPic.getCodeProduct().toUpperCase();
        int quantity = dtoPic.getQuantity();
        String email = dtoPic.getEmail().toLowerCase();
        User user = userRepository.findByEmail(email.toLowerCase());
        Product product = productRepository.findByCodeProduct(code.toUpperCase());
        controll(email, code, false);
        int quantitaTotProdotto = product.getQuantity() - quantity;
        if (quantitaTotProdotto < 0) {
            throw new InsufficientQuantityException();
        }
        ProductInCart nPic = new ProductInCart();
        if (quantity <= 0)
            throw new QuantityCannotBeLess0Exception();
        if (productInCartRepository.findByUserAndProduct(user, product) == null) {
            nPic.setProduct(product);
            nPic.setUser(user);
            nPic.setQuantity(quantity);
            productInCartRepository.save(nPic);
            user.getCart().add(nPic);
            return userRepository.save(user).getCart();
        } else {
            ProductInCart pic = productInCartRepository.findByUserAndProduct(user, product);
            pic.setQuantity(quantity);
            quantitaTotProdotto = product.getQuantity() - (pic.getQuantity());
            if (quantitaTotProdotto < 0) {
                throw new InsufficientQuantityException();
            }
            productInCartRepository.save(pic);
            return userRepository.save(user).getCart();
        }
    }

    @Transactional
    public Product buyProductInCart(String email, String code, boolean verifica) throws RuntimeException {

        Product product = productRepository.findByCodeProduct(code.toUpperCase());
        User compratore = userRepository.findByEmail(email.toLowerCase());
        ProductInCart pic = productInCartRepository.findByUserAndProduct(compratore, product);
        controll(email, code, true);
        int quantity = pic.getQuantity();
        int quantitaTotProdotto = product.getQuantity() - quantity;
        if (quantity <= 0) {
            throw new QuantityCannotBeLess0Exception();
        }
        if (quantitaTotProdotto < 0) {
            throw new QuantityCannotBeLess0Exception();
        }
        double money = compratore.getMoney() - (product.getPrice() * quantity);
        if (money < 0) {
            throw new InsufficientMoneyException();
        }
        product.setQuantity(quantitaTotProdotto);
        compratore.setMoney(money);
        User venditore = product.getUser();
        venditore.setMoney(venditore.getMoney() + product.getPrice() * quantity);
        if (verifica) {
            productInCartRepository.delete(pic);
        }
        productRepository.save(product);
        userRepository.save(compratore);
        userRepository.save(venditore);
        return product;
    }

    @Transactional
    public void removeProductToCart(String email, String code) throws RuntimeException {
        Product product = productRepository.findByCodeProduct(code.toUpperCase());
        User user = userRepository.findByEmail(email.toLowerCase());
        controll(email, code, true);
        productInCartRepository.delete(productInCartRepository.findByUserAndProduct(user, product));
        return;
    }

    public List<ProductInCart> getCart(String email) throws RuntimeException {
        User user = userRepository.findByEmail(email.toLowerCase());
        user.getCart();
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        /* Sort value = Sort.by("id"); */

        Page<ProductInCart> lPic = productInCartRepository.findAllByUser(pageable, user);
        return lPic.getContent();
    }

    public void getCartDelete(String email) throws RuntimeException {
        User user = userRepository.findByEmail(email.toLowerCase());
        List<ProductInCart> cart = user.getCart();
        for (ProductInCart pic : cart) {
            productInCartRepository.delete(pic);
        }
    }

    public double calcolo(User user) throws RuntimeException {
        List<ProductInCart> cart = user.getCart();
        double totale = 0;
        for (ProductInCart pic : cart) {
            double prezzoSP = pic.getProduct().getPrice();
            double prezzoTP = prezzoSP * pic.getQuantity();
            totale = totale + prezzoTP;
        }
      
            return totale;
        
        
    }

    @Transactional
    public void buyAllProductInCart(String email) throws RuntimeException {
        User user = userRepository.findByEmail(email.toLowerCase());
        double totale= calcolo(user);
        if (totale>user.getMoney()) {
            throw new InsufficientMoneyException();
        }
        else{
            List<ProductInCart> cartCopy = new ArrayList<>(user.getCart());
        for (ProductInCart pic : cartCopy) {
            String code = pic.getProduct().getCodeProduct();
            buyProductInCart(email, code, false);
        }
        getCartDelete(email);
        return;
        }
        
    }

    @Transactional
    public int modifiQ(AddProductInCartRequest dtoPic) throws RuntimeException {
        addProductToCartService(dtoPic);
        return dtoPic.getQuantity();
    }
}
