package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Enums.Status;
import TamilovKulanbek.FinalProject.Exception.UserNotFoundException;
import TamilovKulanbek.FinalProject.Models.JwtTokenRequest;
import TamilovKulanbek.FinalProject.Models.JwtTokenResponse;
import TamilovKulanbek.FinalProject.Services.AuthLogService;
import TamilovKulanbek.FinalProject.Util.JwtUtil;
import TamilovKulanbek.FinalProject.jwt.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    @Autowired
    private AuthLogService authLogService;

    @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody JwtTokenRequest jwtTokenRequest) throws UserNotFoundException {
        try {
            authenticationService.authenticate(jwtTokenRequest.getEmail(), jwtTokenRequest.getPassword());

            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtTokenRequest.getEmail());
            final String token = jwtUtil.generateToken(userDetails);

            authLogService.create(jwtTokenRequest.getEmail(), Status.OK);
            return ResponseEntity.ok(new JwtTokenResponse(token));
        } catch (Exception e) {
            authLogService.create(jwtTokenRequest.getEmail(), Status.FAILED);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
