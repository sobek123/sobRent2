package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.security.AuthRequest;
import pl.macieksob.rentCar.security.AuthResponse;
//import pl.macieksob.rentCar.security.JWTTokenUtility;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JWTTokenUtility jwtTokenUtility;

//    @PostMapping("/auth/login")
//    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
//        try{
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
//            );
//            User user = (User) authentication.getPrincipal();
//
//            String accessToken = jwtTokenUtility.generateAccessToken(user);
//            AuthResponse response = new AuthResponse(user.getEmail(),accessToken);
//
//            return ResponseEntity.ok(response);
//
//        }catch (BadCredentialsException ex){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
}
