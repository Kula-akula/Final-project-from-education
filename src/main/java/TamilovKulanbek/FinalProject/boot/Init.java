package TamilovKulanbek.FinalProject.boot;

import TamilovKulanbek.FinalProject.Entities.*;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Repositories.*;
import TamilovKulanbek.FinalProject.Services.CompanyService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    private final ItemRepository itemRepository;

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;
    private final UserService userService;

    private final RoleRepository roleRepository;


//    @Autowired
//    private CartRepos cartRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(CategoryRepository categoryRepository, CompanyService companyService, CompanyRepository companyRepository, ItemRepository itemRepository, WalletRepository walletRepository, UserRepository userRepository, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.companyService = companyService;
        this.companyRepository=companyRepository;
        this.itemRepository = itemRepository;
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
//        ROLES
        Role roleAdmin = roleRepository.save(Role.builder()
                .role("ROLE_ADMIN")
                .build());

        Role roleUser = roleRepository.save(Role.builder()
                .role("ROLE_USER")
                .build());
        Role roleManager = roleRepository.save(Role.builder()
                .role("ROLE_MANAGER")
                .build());



        List<Role> roleAdminList = new ArrayList<>();
        roleAdminList.add(roleAdmin);
        roleAdminList.add(roleUser);
        roleAdminList.add(roleManager);

        List<Role> roleCompanyList= new ArrayList<>();
        roleCompanyList.add(roleManager);

        List<Role> roleUserList=new ArrayList<>();
        roleUserList.add(roleUser);

//
        if (userService.getById(1L)==null) {
            userRepository.save(User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("123"))
                    .phoneNumber("+996(500)-511-932")
//                .gender("MALE")
//                .birthDate(LocalDate.of(1999, 3, 1))
                    .firstName("Kulanbek")
                    .lastName("Tamilov")
//                    .shopName("Mega")
//                    .address("Bishkek")
                    .isActive(1)
                    .roles(roleAdminList)
                    .build());
        }

        userRepository.save(User.builder()
                .email("user@gmail.com")
                .password(passwordEncoder.encode("123"))
                .phoneNumber("+996(500)-511-932")
//                .gender("MALE")
//                .birthDate(LocalDate.of(1999, 3, 1))
                .firstName("Kulanbek")
                .lastName("Tamilov")
//                    .shopName("Mega")
//                    .address("Bishkek")
                .isActive(1)
                .roles(roleUserList)
                .build());
//
////        //Store's wallet
////        walletRepository.save(new DeliveryLog().builder().requisite("chef0102").balance(new BigDecimal(1000000)).currency(Currency.KGZ).user(admin).bankCard("VISA").build());
////
////        //category initializing
        Category computer = categoryRepository.save(Category.builder().categoryName("computer hardware").build());
        Category camera = categoryRepository.save(Category.builder().categoryName("camera, photo & accessories").build());
        Category mobile = categoryRepository.save(Category.builder().categoryName("mobile phones & accessories").build());
        Category laptop = categoryRepository.save(Category.builder().categoryName("laptops").build());
        Category earphone = categoryRepository.save(Category.builder().categoryName("earphone & headphone").build());
        Category food = categoryRepository.save(Category.builder().categoryName("food").build());
        Category forCleaning = categoryRepository.save(Category.builder().categoryName("for cleaning").build());

        //companies initializing
        if (companyService.getById(1L)==null) {
            Company shoro = companyRepository.save(Company.builder()
                    .address("Osmonkula 344A")
                    .phone("+996 312 36 10 00")
                    .email("office@shoro.kg")
                    .name("Shoro")
                    .password("123")
                    .roles(roleCompanyList)
                    .isActive(1)
                    .build());
        }
//        if (companyService.getById(2L)==null) {
            companyRepository.save(Company.builder()
                    .address("Lushikhina 69")
                    .phone("+996 312 357130")
                    .email("hr_ccbb@cci.com.kg")
                    .name("Coca-Cola")
                    .password("123")
                    .roles(roleCompanyList)
                    .isActive(1)
                    .build());
//        }
//
//        //item initializing
        //food
        itemRepository.save(Item.builder()
                .itemName("bread")
                .price(new BigDecimal(15))
                .category(food)
                .status(Status.ACTIVE)
                .build());

        //for cleaning
        itemRepository.save(Item.builder()
                .itemName("washing powder 2kg")
                .price(new BigDecimal(200))
                .category(forCleaning)
                .status(Status.ACTIVE)
                .build());



//        //computer
        itemRepository.save(Item.builder()
                .itemName("cpu intel i3-10600k")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu intel i5-10600k")
                .price(new BigDecimal(19000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu intel i7-10600k")
                .price(new BigDecimal(24000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu intel i9-10600k")
                .price(new BigDecimal(35000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu amd ryzen 3 3300")
                .price(new BigDecimal(9000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu amd ryzen 5 3600")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu amd ryzen 5 2500x")
                .price(new BigDecimal(11000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("cpu amd ryzen 7 3700x")
                .price(new BigDecimal(23000))
                .discountPercentages(0)
                .category(computer)
                .status(Status.ACTIVE)
                .build());
//
//        //camera, photo & accessories
//
        itemRepository.save(Item.builder()
                .itemName("canon camera")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(camera)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("nikon camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(camera)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("samsung camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(camera)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("hidden camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(camera)
                .status(Status.ACTIVE)
                .build());
//
//        //mobile phones & accessories
//
        itemRepository.save(Item.builder()
                .itemName("Xiaomi Note 7")
                .price(new BigDecimal(15000))
                .discountPercentages(2)
                .category(mobile)
                .status(Status.ACTIVE)
                .build());
        itemRepository.save(Item.builder()
                .itemName("Iphone SE2")
                .price(new BigDecimal(33000))
                .discountPercentages(2)
                .category(mobile)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("Samsung Galaxy s9")
                .price(new BigDecimal(30000))
                .discountPercentages(2)
                .category(mobile)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("Nokia 6300I")
                .price(new BigDecimal(1000000))
                .discountPercentages(2)
                .category(mobile)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("Huawei p30")
                .price(new BigDecimal(23000))
                .discountPercentages(2)
                .category(mobile)
                .status(Status.ACTIVE)
                .build());
//
//        //laptops
//
        itemRepository.save(Item.builder()
                .itemName("Acer")
                .price(new BigDecimal(38000))
                .discountPercentages(0)
                .category(laptop)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("Asus")
                .price(new BigDecimal(40000))
                .discountPercentages(20)
                .category(laptop)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("Dell")
                .price(new BigDecimal(45000))
                .discountPercentages(10)
                .category(laptop)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("Lenovo")
                .price(new BigDecimal(35000))
                .discountPercentages(10)
                .category(laptop)
                .status(Status.ACTIVE)
                .build());

//        //earphone & headphone
//
        itemRepository.save(Item.builder()
                .itemName("EarPods")
                .price(new BigDecimal(1500))
                .discountPercentages(0)
                .category(earphone)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("AirPods Pro")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(earphone)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("HyperX Stinger clouds")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(earphone)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .itemName("A4Tech Bloody G520")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(earphone)
                .status(Status.ACTIVE)
                .build());
    }
}
