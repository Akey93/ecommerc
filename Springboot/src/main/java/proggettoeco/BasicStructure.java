package proggetto.proggettoeco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import proggetto.proggettoeco.UTILITY.dto.AddProductInCartRequest;
import proggetto.proggettoeco.UTILITY.objects.RegisterRequest;
import proggetto.proggettoeco.entities.Product;
import proggetto.proggettoeco.repositories.ProductRepository;
import proggetto.proggettoeco.repositories.UserRepository;
import proggetto.proggettoeco.services.UserService;
import proggetto.proggettoeco.services.ProductInCartService;
import proggetto.proggettoeco.services.ProductService;

@SpringBootApplication

public class BasicStructure {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductInCartService productInCartService;

	public static void main(String[] args) {
		SpringApplication.run(BasicStructure.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {

			userService.registerAdmin(new RegisterRequest("Gerry", "Germano", "dalla@ge.rm", "qwerty"));
			userService.registerUser(new RegisterRequest("Giacomo", "Filiberti", "fili@ge.rm", "qwerty"));
			Product p1 = new Product(null, "mozzarella01", "ZIZZONA", 19.99, 43, "alimentare",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p2 = new Product(null, "Pan di stelle", "BISCOTTI01", 4.99, 85, "alimentare",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p3 = new Product(null, "tonno rio mare", "TONNO01", 3.99, 56, "alimentare",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p4 = new Product(null, "filadelfia", "MOZZARELLA", 2.99, 44, "alimentare",
					userRepository.findByEmail("dalla@ge.rm"));

			productService.addProduct(p4, "dalla@ge.rm");
			productService.addProduct(p1, "dalla@ge.rm");
			productService.addProduct(p2, "dalla@ge.rm");
			productService.addProduct(p3, "dalla@ge.rm");

			AddProductInCartRequest r1 = new AddProductInCartRequest("BISCOTTI01", 5, "fili@ge.rm");
			AddProductInCartRequest r2 = new AddProductInCartRequest("TONNO01", 5, "fili@ge.rm");

			productInCartService.addProductToCartService(r1);
			productInCartService.addProductToCartService(r2);

			/*
			 * productInCartService.addProductToCartService("MOZZARELLA" , 5, "fili@ge.rm");
			 * System.out.println("il terzo è andato");
			 */

		};
	}
}
