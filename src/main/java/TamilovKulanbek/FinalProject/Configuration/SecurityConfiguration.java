package TamilovKulanbek.FinalProject.Configuration;

import TamilovKulanbek.FinalProject.jwt.JwtAuthenticationEntryPoint;
import TamilovKulanbek.FinalProject.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Value("${jwt.get.token.uri}")
    private String loginPath;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/employee").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/employee/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/employee").hasRole("ADMIN");
        http.csrf().disable().cors()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, loginPath)
                .antMatchers(HttpMethod.POST, "/user/register");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("admin").password("{noop}admin123").roles("ADMIN")
////                .and()
////                .withUser("kulanbek").password("{noop}kulanbek123").roles("USER")
////                .and()
////                .withUser("aidin").password("{noop}aidin123").roles("USER");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select login, password, is_active from users where login=?")
//                .authoritiesByUsernameQuery("select u.login, ur.role_name from user_roles ur join users u on ur.user_id=u.id where u.login=? and u.is_active=true");
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic().and().authorizeRequests()
////                .antMatchers(HttpMethod.POST, "/items").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST, "/orders/add").hasAnyRole("ADMIN", "USER")
////                .antMatchers(HttpMethod.POST, "/wallets/add").hasAnyRole("ADMIN", "USER")
//
////                .antMatchers(HttpMethod.DELETE, "/categories/delete/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/items/delete/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/users/delete/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/wallets/delete/**").hasAnyRole("ADMIN", "USER")
////                .antMatchers(HttpMethod.DELETE, "/orders/delete/**").hasAnyRole("ADMIN", "USER")
////
////                .antMatchers(HttpMethod.GET, "/items").hasAnyRole("ADMIN", "USER")
////                .antMatchers(HttpMethod.GET, "/categories").hasAnyRole("ADMIN", "USER")
////                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
////                .antMatchers(HttpMethod.GET, "/orders").hasAnyRole("ADMIN", "USER")
////                .and().csrf().disable().headers().frameOptions().disable().and()
////                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//        http.httpBasic().and().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/item").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/item/category/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/category").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/category").permitAll()
//                .antMatchers("/wallet/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/cart-item/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/cart/**").hasAnyRole("ADMIN", "USER")
//                .and().csrf().disable();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//}


