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

			userService.registerAdmin(new RegisterRequest("Gerry", "Germano", "dalla@ge.rm", "qwerty", "via Verdi"));
			userService.registerUser(new RegisterRequest("Giacomo", "Filiberti", "fili@ge.rm", "qwerty", "via Rossi"));
			Product p1 = new Product(null, "mozzarella01", "ZIZZONA", 19.99, 43, "alimentare",
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsdmStO0Av5PeBK_nHsx9jPPoxGaCTFB8Ygb5LkMZAw69ay7Kr9syaxO6GiATPvWZ4XfE&usqp=CAU","mozzarella da 1kg",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p2 = new Product(null, "Pan di stelle", "BISCOTTI01", 4.99, 85, "alimentare",
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6juHK7uiCdcSEAgIoXNSBl7av_FskhssrmsnkfLfrUtdcyY8m4CGaIrGrnzfCPAAhKgU&usqp=CAU","Pacco di dolci fatti con polvere stellare raccolta accuratamente da fate e gnomi",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p3 = new Product(null, "tonno rio mare", "TONNO01", 3.99, 56, "alimentare",
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnjHEefeyd2sMaMXawrhBPpEJKpsXGeL9lhdgkQgBMwbTUt4jyfvapeP8rC-GB9petgKY&usqp=CAU","tonno",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p4 = new Product(null, "filadelfia", "MOZZARELLA", 2.99, 44, "alimentare",
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsyb1GmQmzUvKVQEZTYkOWHRTe0cYnFAnYTlJKJhPMNsWP8zj4toGf_3M15rhwOVu4kHA&usqp=CAU","formaggio formaggioso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p5 = new Product(null, "birillo", "birillo151", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p6 = new Product(null, "birillo", "birillo125", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p7 = new Product(null, "birillo", "birillo135", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p8 = new Product(null, "birillo", "birillo145", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p9 = new Product(null, "birillo", "birillo1125", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p10 = new Product(null, "birillo", "birillo11235", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p11 = new Product(null, "birillo", "birillo1521", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p12= new Product(null, "birillo", "birillo121315", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p13= new Product(null, "birillo", "birillo12325", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p14= new Product(null, "birillo", "birillo13245", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p15= new Product(null, "birillo", "birillo15324", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));
			Product p16= new Product(null, "birillo", "birillo15234", 3.99, 67, "sport",
					"https://www.bindigiochi.it/media/catalog/product/cache/b4bbcee0edb64a94c08f0a61e165bd23/b/i/birilli-bambini.jpg","birillo birilloso",
					userRepository.findByEmail("dalla@ge.rm"));

			productService.addProduct(p4, "dalla@ge.rm");
			productService.addProduct(p1, "dalla@ge.rm");
			productService.addProduct(p2, "dalla@ge.rm");
			productService.addProduct(p3, "dalla@ge.rm");
			productService.addProduct(p5, "dalla@ge.rm");
			productService.addProduct(p6, "dalla@ge.rm");
			productService.addProduct(p7, "dalla@ge.rm");
			productService.addProduct(p8, "dalla@ge.rm");
			productService.addProduct(p9, "dalla@ge.rm");
			productService.addProduct(p10, "dalla@ge.rm");
			productService.addProduct(p11, "dalla@ge.rm");
			productService.addProduct(p12, "dalla@ge.rm");
			productService.addProduct(p13, "dalla@ge.rm");
			productService.addProduct(p14, "dalla@ge.rm");
			productService.addProduct(p15, "dalla@ge.rm");
			productService.addProduct(p16, "dalla@ge.rm");
			
			AddProductInCartRequest r1 = new AddProductInCartRequest("BISCOTTI01", 5, "fili@ge.rm");
			AddProductInCartRequest r2 = new AddProductInCartRequest("TONNO01", 5, "fili@ge.rm");
			AddProductInCartRequest r3 = new AddProductInCartRequest("birillo151", 10, "fili@ge.rm");

			productInCartService.addProductToCartService(r1);
			productInCartService.addProductToCartService(r2);
			productInCartService.addProductToCartService(r3);

		};
	}
}
