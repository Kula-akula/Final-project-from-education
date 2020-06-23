package TamilovKulanbek.FinalProject.boot;

import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


//    @Autowired
//    private CartRepos cartRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        ROLES
//        Role roleAdmin = roleRepository.save(Role.builder()
//                .role("ROLE_ADMIN")
//                .build());
//
//        Role roleUser = roleRepository.save(Role.builder()
//                .role("ROLE_USER")
//                .build());
//
//
//        List<Role> roleList = new ArrayList<>();
//        roleList.add(roleAdmin);
//        roleList.add(roleUser);

//        List<Role> userList = new ArrayList<>();
//        userList.add(roleUser);

//        User admin = userRepository.save(User.builder()
//                .email("chef@gmail.com")
//                .password(passwordEncoder.encode("12345"))
////                .phoneNumber("+996(500)-511-932")
////                .gender("MALE")
////                .birthDate(LocalDate.of(1999, 3, 1))
//                .firstName("Айдин")
//                .lastName("Сабыров")
//                .isActive(1)
//                .roles(roleList)
//                .build());

//        //Store's wallet
//        walletRepo.save(new Wallet().builder().requisite("chef0102").balance(new BigDecimal(1000000)).currency(Currency.KGZ).user(admin).bankCard("VISA").build());
//
//        //category initializing
//        Category computer = categoryRepo.save(new Category().builder().categoryName("computer hardware").build());
//        Category camera = categoryRepo.save(new Category().builder().categoryName("camera, photo & accessories").build());
//        Category mobile = categoryRepo.save(new Category().builder().categoryName("mobile phones & accessories").build());
//        Category laptop = categoryRepo.save(new Category().builder().categoryName("laptops").build());
//        Category earphone = categoryRepo.save(new Category().builder().categoryName("earphone & headphone").build());
//
//        //item initializing
//
//        //computer
//        itemRepo.save(new Item().builder()
//                .itemName("cpu intel i3-10600k")
//                .price(new BigDecimal(13000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu intel i5-10600k")
//                .price(new BigDecimal(19000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu intel i7-10600k")
//                .price(new BigDecimal(24000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu intel i9-10600k")
//                .price(new BigDecimal(35000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu amd ryzen 3 3300")
//                .price(new BigDecimal(9000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu amd ryzen 5 3600")
//                .price(new BigDecimal(13000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu amd ryzen 5 2500x")
//                .price(new BigDecimal(11000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("cpu amd ryzen 7 3700x")
//                .price(new BigDecimal(23000))
//                .discountPercentages(0)
//                .category(computer)
//                .status(Status.ACTIVE)
//                .build());
//
//        //camera, photo & accessories
//
//        itemRepo.save(new Item().builder()
//                .itemName("canon camera")
//                .price(new BigDecimal(15000))
//                .discountPercentages(0)
//                .category(camera)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("nikon camera")
//                .price(new BigDecimal(13000))
//                .discountPercentages(0)
//                .category(camera)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("samsung camera")
//                .price(new BigDecimal(13000))
//                .discountPercentages(0)
//                .category(camera)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("hidden camera")
//                .price(new BigDecimal(13000))
//                .discountPercentages(0)
//                .category(camera)
//                .status(Status.ACTIVE)
//                .build());
//
//        //mobile phones & accessories
//
//        itemRepo.save(new Item().builder()
//                .itemName("Xiaomi Note 7")
//                .price(new BigDecimal(15000))
//                .discountPercentages(2)
//                .category(mobile)
//                .status(Status.ACTIVE)
//                .build());
//        itemRepo.save(new Item().builder()
//                .itemName("Iphone SE2")
//                .price(new BigDecimal(33000))
//                .discountPercentages(2)
//                .category(mobile)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("Samsung Galaxy s9")
//                .price(new BigDecimal(30000))
//                .discountPercentages(2)
//                .category(mobile)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("Nokia 6300I")
//                .price(new BigDecimal(1000000))
//                .discountPercentages(2)
//                .category(mobile)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("Huawei p30")
//                .price(new BigDecimal(23000))
//                .discountPercentages(2)
//                .category(mobile)
//                .status(Status.ACTIVE)
//                .build());
//
//        //laptops
//
//        itemRepo.save(new Item().builder()
//                .itemName("Acer")
//                .price(new BigDecimal(38000))
//                .discountPercentages(0)
//                .category(laptop)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("Asus")
//                .price(new BigDecimal(40000))
//                .discountPercentages(20)
//                .category(laptop)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("Dell")
//                .price(new BigDecimal(45000))
//                .discountPercentages(10)
//                .category(laptop)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("Lenovo")
//                .price(new BigDecimal(35000))
//                .discountPercentages(10)
//                .category(laptop)
//                .status(Status.ACTIVE)
//                .build());
//
//        //earphone & headphone
//
//        itemRepo.save(new Item().builder()
//                .itemName("EarPods")
//                .price(new BigDecimal(1500))
//                .discountPercentages(0)
//                .category(earphone)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("AirPods Pro")
//                .price(new BigDecimal(15000))
//                .discountPercentages(0)
//                .category(earphone)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("HyperX Stinger clouds")
//                .price(new BigDecimal(15000))
//                .discountPercentages(0)
//                .category(earphone)
//                .status(Status.ACTIVE)
//                .build());
//
//        itemRepo.save(new Item().builder()
//                .itemName("A4Tech Bloody G520")
//                .price(new BigDecimal(15000))
//                .discountPercentages(0)
//                .category(earphone)
//                .status(Status.ACTIVE)
//                .build());
    }
}
