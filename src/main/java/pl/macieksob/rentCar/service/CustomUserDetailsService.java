//package pl.macieksob.rentCar.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pl.macieksob.rentCar.model.User;
//import pl.macieksob.rentCar.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username: "+username + "not found"));
//        Collection< SimpleGrantedAuthority > authorities = new ArrayList<>();
//        user.getRoles().forEach(role ->
//                authorities.add(new SimpleGrantedAuthority(role.getName())));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
//    }
//}
