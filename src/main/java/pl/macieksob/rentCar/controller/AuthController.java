//package pl.macieksob.rentCar.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import pl.macieksob.rentCar.exception.RoleNotFoundException;
//import pl.macieksob.rentCar.exception.UserDuplicateException;
//import pl.macieksob.rentCar.model.Role;
//import pl.macieksob.rentCar.model.User;
//import pl.macieksob.rentCar.repository.RoleRepository;
//import pl.macieksob.rentCar.repository.UserRepository;
//import pl.macieksob.rentCar.security.*;
//import pl.macieksob.rentCar.service.CustomUserDetails;
//import pl.macieksob.rentCar.service.CustomUserDetailsService;
////import pl.macieksob.rentCar.security.JWTTokenUtility;
//
//import javax.validation.Valid;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//public class AuthController {
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    private JWTTokenUtility jwtTokenUtility;
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest request){
//
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = jwtTokenUtility.generateAccessToken(authentication);
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
//
//
//
//            return ResponseEntity.ok(new JWTResponse(jwt,
//                    userDetails.getId(),
//                    userDetails.getEmail(),
//                    roles));
//
//
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
//        if (userRepository.existsByEmail(signupRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new UserDuplicateException("Email is already taken!"));
//        }
//
//        User user = new User(signupRequest.getEmail(),passwordEncoder.encode(signupRequest.getPassword()));
//
//        Set<String> strRoles = signupRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if(strRoles == null){
//            Role userRole =  roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//            roles.add(userRole);
//        }
//        else{
//            strRoles.forEach(role -> {
//                switch (role){
//                    case "admin":
//                        Role adminRole =  roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//                        roles.add(adminRole);
//                        break;
//                    case "logged_user":
//                        Role loggedUserRole = roleRepository.findByName("ROLE_LOGGEDUSER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//                        roles.add(loggedUserRole);
//                        break;
//                    default:
//                        Role unloggedUserRole = roleRepository.findByName("ROLE_UNLOGGEDUSER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//                        roles.add(unloggedUserRole);
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }
//}
