package TamilovKulanbek.FinalProject.jwt;

import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Exception.CompanyNotFoundException;
import TamilovKulanbek.FinalProject.Exception.JwtAuthenticationException;
import TamilovKulanbek.FinalProject.Exception.ShopNotFoundException;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Services.AuthLogService;
import TamilovKulanbek.FinalProject.Services.CompanyService;
import TamilovKulanbek.FinalProject.Services.ShopService;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthLogService authLogService;

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ShopService shopService;

    public void authenticate(String email, String password) throws JwtAuthenticationException {
        try {
            if (userService.findByEmail(email)!=null)
                if(authLogService.countByUserAndStatus(Status.FAILED, email, 0) >= 3)
                    userService.deActivateUser(email);
            else if (companyService.findByEmail(email)!=null)
                if (authLogService.countByCompanyAndStatus(Status.FAILED,email,0)>=3)
                    companyService.deActivateCompany(email);
            else if (shopService.findByName(email)!=null)
                if (authLogService.countByShopAndStatus(Status.FAILED, email,0)>=3)
                    shopService.deActivateShop(email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            authLogService.create(email, Status.OK);
        }catch (UserNotFoundException e) {
            throw new JwtAuthenticationException("USER NOT FOUND");
        }catch (CompanyNotFoundException e) {
            throw new JwtAuthenticationException("COMPANY NOT FOUND");
        }catch (ShopNotFoundException e) {
            throw new JwtAuthenticationException("SHOP NOT FOUND");
        }
        catch (DisabledException e){
            authLogService.create(email, Status.FAILED);
            throw new JwtAuthenticationException("USER_DISABLED");
        }catch (BadCredentialsException e){
            throw new JwtAuthenticationException("INVALID EMAIL OR PASSWORD");
            }
    }
}
