package TamilovKulanbek.FinalProject.jwt;

import TamilovKulanbek.FinalProject.Entities.User;
import TamilovKulanbek.FinalProject.Entities.Role;
import TamilovKulanbek.FinalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmailAndIsActive(email, 1);
        if(user == null){
//            throw new UsernameNotFoundException(String.format("User with email  '%s' not found", email));
            return null;
        }

        return JwtUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .isActive(user.getIsActive())
                .authorities(mapToGrantedAuthorities(user.getRoles()))
                .build();
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
