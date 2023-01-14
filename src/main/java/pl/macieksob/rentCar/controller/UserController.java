package pl.macieksob.rentCar.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.exception.UserNotFoundException;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.UserRepository;
import pl.macieksob.rentCar.service.MailService;
import pl.macieksob.rentCar.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("https://sobrent-front.herokuapp.com")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/workers")
    public List<UserDTO> getAllWorkers(){
        return userService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDTO registerUser(@RequestBody @Valid UserDTO user, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        String url = Utility.getURL(httpServletRequest);
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        userService.sendVerificationEmail(user,"https://sobrent.herokuapp.com");
//        user.setCreatedTime(LocalDateTime.now());
//        user.setRoles(Set.of(new Role("LOGGED_USER")));
        return userService.addUser(user);

    }


    @RequestMapping(value = "/registerWorker", method = RequestMethod.POST)
    public UserDTO registerWorker(@RequestBody @Valid UserDTO user, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        String url = Utility.getURL(httpServletRequest);
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
//        userService.sendVerificationEmail(user,url);
//        user.setCreatedTime(LocalDateTime.now());
//        user.setRoles(Set.of(new Role("LOGGED_USER")));
        return userService.addUser(user);

    }

    @RequestMapping(value="/changePassword", method = RequestMethod.PUT)
    public void changePassword(@RequestBody UserDTO userDTO){
        userService.changePassword(userDTO);
    }

    @RequestMapping(value="/makeCard", method=RequestMethod.PUT)
    public User makeCard(@RequestBody UserDTO userDTO){return userService.makeCard(userDTO);}
    @RequestMapping(value = "/editUser/{id}",method=RequestMethod.PUT)
    public UserDTO editUser(@PathVariable Long id, @Valid @RequestBody UserDTO newUser){
        return userService.editUser(id,newUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(UserDTO user){
        userService.deleteUser(user);

    }
    @GetMapping("/verify")
    public String verifyAccount(String code){
        boolean verify = userService.verify(code);

        String pageTitle = verify ? "Weryfikacja przebiegła pomyślnie!" : "Weryfikacja nieudana.";
        return pageTitle;
    }


    @RequestMapping(value = "/processEmail",method=RequestMethod.GET)
    public void processForgotPasswordForm(@RequestParam(value = "email") String email){
//        String email = request.getParameter("email");
        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token,email);
        String resetPasswordLink = "https://sobrent.herokuapp.com/reset_password/token=" + token;
        try {
            mailService.sendMailResetPassword(email,resetPasswordLink);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("/reset_password")
//    public boolean showResetPasswordForm(String token){
//        UserDTO user = userService.getUserByResetPasswordToken(token);
//
//        if(user == null){
//            throw new UserNotFoundException("User not found!");
//        }
//        return true;
//    }

    @RequestMapping(value = "/updatePassword",method=RequestMethod.PUT)
    public void processResetPasswordForm(@RequestBody Object object){

        String s = object.toString();
        String password = s.substring(28, s.indexOf("token") - 2);
        String token = s.substring(s.indexOf("token") + 6,s.length()-3);
        User user = userService.getUserByResetPasswordToken(token);

        if(user == null){
            throw new UserNotFoundException("User not found!");
        }else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodePassword = bCryptPasswordEncoder.encode(password);
            user.setPassword(encodePassword);
            user.setResetPasswordToken(null);

            userRepository.save(user);
        }
        
    }

    @GetMapping("/city")
    public List<UserDTO> getUsersByCity(@RequestParam(value = "city") String city){
        return userService.getAllUsersByCity(city);


    }

    @GetMapping("/name")
    public List<UserDTO> getUsersByName(@RequestParam(value = "name") String name){
        return userService.getAllUsersByName(name);


    }

    @GetMapping("/surname")
    public List<UserDTO> getUsersBySurname(@RequestParam(value = "surname") String surname){
        return userService.getAllUsersBySurname(surname);


    }

    @GetMapping("/findByPesel")
    public boolean findByPesel(@RequestParam("pesel") String pesel){
        return userService.findByPesel(pesel);
    }

    @GetMapping("/findByPhoneNumber")
    public User findByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber){
        return userService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/findByEmail")
    public UserDTO findByEmail(@RequestParam("email") String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/checkEmail")
    public boolean checkEmail(@RequestParam("email") String email){
        return userService.checkEmail(email);
    }

//    @GetMapping("/keyword")
//    public List<UserDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
//        return userService.getByKeyword(keyword);
//    }

    @RequestMapping(value="/minusPoints", method = RequestMethod.PUT)
    public void minusPoints(@RequestBody UserDTO userDTO, @RequestParam("points") int points){
        userService.minusPoints(userDTO,points);
    }

    @GetMapping(value="/checkPassword")
    public boolean checkPassword(@RequestParam("password") String password, @RequestParam("email") String email){

        return userService.findByPassword(password,email);
    }

    @GetMapping("/keyword")
    public List<UserDTO> getByKeyword(@RequestParam String keyword,@RequestParam("role") String role){
        return userService.getByKeyword(keyword,role);
    }

    @GetMapping("/sortUp")
    public List<UserDTO> sortByNameAndSurnameAsc(@RequestParam("role") String role){
        return userService.sortByNameAndSurnameAsc(role);
    }

    @GetMapping("/sortDown")
    public List<UserDTO> sortByNameAndSurnameDesc(@RequestParam("role") String role){
        return userService.sortByNameAndSurnameDesc(role);
    }

    @GetMapping("/respondTo")
    public void respondTo(@RequestParam("from") String from, @RequestParam("content") String content, @RequestParam("title") String title, @RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        userService.respondTo(from, content, title, email);
    }
}
