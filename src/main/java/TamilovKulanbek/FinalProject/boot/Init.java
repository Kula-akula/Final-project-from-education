package TamilovKulanbek.FinalProject.boot;

import TamilovKulanbek.FinalProject.Entities.*;
import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Repositories.*;
import TamilovKulanbek.FinalProject.Services.CompanyService;
import TamilovKulanbek.FinalProject.Services.ShopService;
import TamilovKulanbek.FinalProject.Services.UserService;
import TamilovKulanbek.FinalProject.dto.CompanyDto.CompanyCreateModel;
import TamilovKulanbek.FinalProject.dto.ShopDto.ShopCreateModel;
import TamilovKulanbek.FinalProject.dto.userDto.UserModel;
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
    private final ShopService shopService;


//    @Autowired
//    private CartRepos cartRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(CategoryRepository categoryRepository, ShopService shopService, CompanyService companyService, CompanyRepository companyRepository, ItemRepository itemRepository, WalletRepository walletRepository, UserRepository userRepository, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.shopService=shopService;
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
        Role roleShop = roleRepository.save(Role.builder()
                .role("ROLE_SHOP")
                .build());



        List<Role> roleAdminList = new ArrayList<>();
        roleAdminList.add(roleAdmin);
        roleAdminList.add(roleUser);
        roleAdminList.add(roleManager);
//
        List<Role> roleCompanyList= new ArrayList<>();
        roleCompanyList.add(roleManager);

        List<Role> roleUserList=new ArrayList<>();
        roleUserList.add(roleUser);

//
//        if (userService.getById(1L)==null) {
            userService.create(UserModel.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("123"))
                    .phoneNumber("+996(500)-511-932")
//                .gender("MALE")
//                .birthDate(LocalDate.of(1999, 3, 1))
                    .firstName("Kulanbek")
                    .lastName("Tamilov")
//                    .shopName("Mega")
                    .address("Bishkek")
                    .build());
//        }

        userService.create(UserModel.builder()
                .email("user@gmail.com")
                .password(passwordEncoder.encode("123"))
                .phoneNumber("+996(500)-511-932")
//                .gender("MALE")
//                .birthDate(LocalDate.of(1999, 3, 1))
                .firstName("Kulanbek")
                .lastName("Tamilov")
//                    .shopName("Mega")
                .address("Bishkek")
                .build());

//        //category initializing

        Category food = categoryRepository.save(Category.builder().categoryName("food").build());
        Category dairyProducts = categoryRepository.save(Category.builder().categoryName("dairy products, milk cheese ").build());
//        Category frozenProducts = categoryRepository.save(Category.builder().categoryName("frozen products, ice-creams").build());
        Category nonAlcoholicDrink = categoryRepository.save(Category.builder().categoryName("nonAlcoholic drinks").build());
        Category alcoholicDrink = categoryRepository.save(Category.builder().categoryName("alcoholic drinks").build());
        Category vegetablesAndFruits = categoryRepository.save(Category.builder().categoryName("vegetable and fruits").build());
        Category cigarette = categoryRepository.save(Category.builder().categoryName("cigarette").build());
//        Category forCleaning = categoryRepository.save(Category.builder().categoryName("for cleaning").build());

        //companies initializing
        companyService.create(CompanyCreateModel.builder()
                .address("Sovetskaya 1")
                .phone("+996 312 313131")
                .email("pekarnya@gmail.com")
                .name("Pekarnya")
                .password("123")
                .build());

        companyService.create(CompanyCreateModel.builder()
                .address("Osmonkula 344A")
                .phone("+996 312 36 10 00")
                .email("office@shoro.kg")
                .name("Shoro")
                .password("123")
                .build());
//        companyRepository.save(Company.builder()
//                    .address("Osmonkula 344A")
//                    .phone("+996 312 36 10 00")
//                    .email("office@shoro.kg")
//                    .name("Shoro")
//                    .password("123")
//                    .roles(roleCompanyList)
//                    .isActive(1)
//                    .build());
//        }
        companyService.create(CompanyCreateModel.builder()
                .name("Pepsi&Co")
                .address("Lushikhina 69")
                .phone("+996 312 357130")
                .email("hr_ccbb@cci.com.kg")
                .password("123")
                .build());
//        companyRepository.save(Company.builder()
//                .name("Pepsi&Co")
//                .address("Lushikhina 69")
//                .phone("+996 312 357130")
//                .email("hr_ccbb@cci.com.kg")
//                .password("123")
//                .roles(roleCompanyList)
//                .isActive(1)
//                .build());
//        }
        companyService.create(CompanyCreateModel.builder()
                .address("Pole v Sokuluke")
                .phone("+996 3132 23123")
                .email("fermer@gmail.com")
                .name("Fermer")
                .password("123")
                .build());
//
        companyService.create(CompanyCreateModel.builder()
                .address("Vostochnay promzona")
                .phone("+996 312 121212")
                .email("forester@gmail.com")
                .name("Forester")
                .password("123")
                .build());

        companyService.create(CompanyCreateModel.builder()
                .address("Fuchika/Jibek-Jolu")
                .phone("+996 312 313131")
                .email("top_noch@gmail.com")
                .name("Top Noch")
                .password("123")
                .build());

        shopService.create(ShopCreateModel.builder()
                .shopName("Frunze")
                .shopAddress("Bishkek")
                .firstName("Vasya")
                .lastName("Pupkin")
                .phone("0777777777")
                .email("frunze@gmail.com")
                .password("123")
                .build());



//
//        //item initializing
        //food
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pekarnya"))
                .itemName("bread")
                .type("400gr")
                .price(new BigDecimal(15))
                .category(food)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pekarnya"))
                .itemName("lepeshka")
                .type("200gr")
                .price(new BigDecimal(20))
                .category(food)
                .status(Status.ACTIVE)
                .build());

        //for cleaning
//        itemRepository.save(Item.builder()
//                .itemName("washing powder 2kg")
//                .price(new BigDecimal(200))
//                .category(forCleaning)
//                .status(Status.ACTIVE)
//                .build());



//        //non alcoholic drinks

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Shoro"))
                .itemName("Shoro Maksim")
                .type("1L")
                .price(new BigDecimal(45))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Shoro"))
                .itemName("Bozo")
                .type("1L")
                .price(new BigDecimal(50))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Shoro"))
                .itemName("Chalap")
                .type("1L")
                .price(new BigDecimal(50))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Mirinda")
                .type("1.5L")
                .price(new BigDecimal(65))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Mirinda")
                .type("1L")
                .price(new BigDecimal(50))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());


        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Mirinda")
                .type("0.5L")
                .price(new BigDecimal(30))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Pepsi")
                .type("1.5L")
                .price(new BigDecimal(65))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Pepsi")
                .type("1L")
                .price(new BigDecimal(50))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Pepsi")
                .type("0.5L")
                .price(new BigDecimal(30))
                .discountPercentages(0)
                .category(nonAlcoholicDrink)
                .status(Status.ACTIVE)
                .build());
//
//        //alcoholic drinks
//
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Forester"))
                .itemName("Johny Walker Blue Label")
                .type("1L")
                .price(new BigDecimal(25000))
                .discountPercentages(0)
                .category(alcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Forester"))
                .itemName("Klinskoe")
                .type("0.5L")
                .price(new BigDecimal(60))
                .discountPercentages(0)
                .category(alcoholicDrink)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Forester"))
                .itemName("Baltica №7")
                .type("0.5L")
                .price(new BigDecimal(60))
                .discountPercentages(0)
                .category(alcoholicDrink)
                .status(Status.ACTIVE)
                .build());

//        itemRepository.save(Item.builder()
//                .company(companyRepository.findByName("Abdysh-Ata"))
//                .itemName("Shahterskoe")
//                .type("2L")
//                .price(new BigDecimal(110))
//                .discountPercentages(0)
//                .category(alcoholicDrink)
//                .status(Status.ACTIVE)
//                .build());
//
//        //dairyProducts phones & accessories
//
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Veseliy molochnik")
                .type("1L")
                .price(new BigDecimal(50))
                .discountPercentages(2)
                .category(dairyProducts)
                .status(Status.ACTIVE)
                .build());
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Domik v derevne")
                .type("1L")
                .price(new BigDecimal(65))
                .discountPercentages(2)
                .category(dairyProducts)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Plavlenniy sir VM gribi")
                .type("100gr")
                .price(new BigDecimal(90))
                .discountPercentages(2)
                .category(dairyProducts)
                .status(Status.ACTIVE)
                .build());
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Plavlenniy sir VM gribi")
                .type("200gr")
                .price(new BigDecimal(140))
                .discountPercentages(2)
                .category(dairyProducts)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Pepsi&Co"))
                .itemName("Plavlenniy sir VM gribi")
                .type("400gr")
                .price(new BigDecimal(230))
                .discountPercentages(2)
                .category(dairyProducts)
                .status(Status.ACTIVE)
                .build());

//
//        //laptops
//
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Fermer"))
                .itemName("Arbuz")
                .type("Big")
                .price(new BigDecimal(13))
                .discountPercentages(0)
                .category(vegetablesAndFruits)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Fermer"))
                .itemName("Arbuz")
                .type("Small")
                .price(new BigDecimal(10))
                .discountPercentages(20)
                .category(vegetablesAndFruits)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Fermer"))
                .itemName("Kartoshka")
                .type("Big")
                .price(new BigDecimal(30))
                .discountPercentages(10)
                .category(vegetablesAndFruits)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Fermer"))
                .itemName("Kartoshka")
                .type("Small")
                .price(new BigDecimal(18))
                .discountPercentages(10)
                .category(vegetablesAndFruits)
                .status(Status.ACTIVE)
                .build());

//        //cigarette & headphone
//
        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Top Noch"))
                .itemName("Winston")
                .type("Blue")
                .price(new BigDecimal(60))
                .discountPercentages(0)
                .category(cigarette)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Top Noch"))
                .itemName("Sobranie")
                .type("Black")
                .price(new BigDecimal(120))
                .discountPercentages(0)
                .category(cigarette)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Top Noch"))
                .itemName("Winston XS")
                .type("Blue")
                .price(new BigDecimal(70))
                .discountPercentages(0)
                .category(cigarette)
                .status(Status.ACTIVE)
                .build());

        itemRepository.save(Item.builder()
                .company(companyRepository.findByName("Top Noch"))
                .itemName("LD")
                .type("White")
                .price(new BigDecimal(40))
                .discountPercentages(0)
                .category(cigarette)
                .status(Status.ACTIVE)
                .build());
    }
}
