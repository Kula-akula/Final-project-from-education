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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
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
        http.csrf().disable().cors()
                .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/user/all").permitAll()
                    .antMatchers(HttpMethod.GET, "/user").hasAnyRole("ADMIN", "MANAGER")
                    .antMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "user/**").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/roles/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/roles/**").hasRole("ADMIN")

//                .antMatchers(HttpMethod.GET, "/shop").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers(HttpMethod.POST, "/shop/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/shop/delete").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET, "/company").permitAll()
                    .antMatchers(HttpMethod.GET, "/company/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/company/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/company/**").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET, "/items").permitAll()
                    .antMatchers(HttpMethod.GET, "/items/*").hasRole("MANAGER")
                    .antMatchers(HttpMethod.POST, "/items/**").hasRole("MANAGER")
                    .antMatchers(HttpMethod.POST, "/items").hasRole("MANAGER")
                    .antMatchers(HttpMethod.DELETE, "items/**").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET, "/orders").hasRole("USER")
                    .antMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("USER", "MANAGER")
                    .antMatchers(HttpMethod.POST, "/orders").hasRole("USER")
                    .antMatchers(HttpMethod.DELETE, "orders/**").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET, "/delivery/my").hasAnyRole("USER", "MANAGER")
                    .antMatchers(HttpMethod.GET, "/delivery/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/delivery/**").hasRole("ADMIN")

                    .antMatchers(HttpMethod.POST, "/wallet/replenishByYourSelf").hasAnyRole("ADMIN", "MANAGER")

                    .antMatchers(HttpMethod.POST, "/visit/**").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET, "/categories").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, loginPath)
                .antMatchers(HttpMethod.POST, "/user/register")
                .antMatchers(HttpMethod.POST, "/user/recovery")
                .antMatchers(HttpMethod.POST, "/user/confirm");

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

