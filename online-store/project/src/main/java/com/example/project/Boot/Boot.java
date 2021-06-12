package com.example.project.Boot;

import com.example.project.Entity.*;
import com.example.project.Enum.RegionCost;
import com.example.project.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Boot implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CardService cardService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private DeliveryService deliveryService;

    @Override
    public void run(String... args) throws Exception {

        Role roleAdmin = roleService.createRole(Role.builder().name("ROLE_ADMIN").build());
        roleService.createRole(Role.builder().name("ROLE_USER").build());

        User user1 = userService.createUser(User.builder().login("user").
                password("user123").status(1).build());
        User user2 = userService.createAdmin(User.builder().login("admin").
                password(passwordEncoder.encode("admin123")).roles(Collections.singleton(roleAdmin)).status(1).build());

        contactService.createContactCLR(Contact.builder().user(user2).email("admin@gmail.com").phoneNumber("77777777").postCode("1111").build());
        contactService.createContactCLR(Contact.builder().user(user1).email("user@gmail.com").phoneNumber("55555555").postCode("1111").build());

        Category category1 = categoryService.createCategory(Category.builder().name("Electronic").description("lllllllllllll").build());
        Category category2 = categoryService.createCategory(Category.builder().name("Clothes").description("lllllllllllll").build());

        Set<Category> categorySet1 = new HashSet<>();
        categorySet1.add(category1);

        Set<Category> categorySet2 = new HashSet<>();
        categorySet2.add(category2);

        productService.createProduct(Product.builder().name("Laptop").description("qqqqqqqq").categories(categorySet1).price(45.0).weight(2.0).build());
        productService.createProduct(Product.builder().name("Pullover").description("qqqqqqqq").categories(categorySet2).price(25.0).weight(1.0).build());

        cardService.createCard(Card.builder().number(12122121).ccv(121).balance(3000.0).build());

        locationService.createLocation(Location.builder().city("TALAS").country("KYRGYZSTAN").postalCode("11231").region(RegionCost.TALAS).build());

    }
}
