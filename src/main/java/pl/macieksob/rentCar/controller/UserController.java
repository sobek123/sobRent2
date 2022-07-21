package pl.macieksob.rentCar.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.service.MailService;
import pl.macieksob.rentCar.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public String registerUser(UserDTO user, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {

        String url = Utility.getURL(httpServletRequest);
        userService.sendVerificationEmail(user,url);
        user.setCreatedTime(LocalDateTime.now());
        user.setRoles(Set.of(new Role("LOGGED_USER")));
        userService.addUser(user);
        return "";
    }

    @PutMapping("/editUser/{id}")
    public String editUser(@PathVariable Long id, @RequestBody UserDTO newUser){
        userService.editUser(id,newUser);

        return "";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return "";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(UserDTO user){
        userService.deleteUser(user);

        return "";
    }
    @GetMapping("/verify")
    public String verifyAccount(String code){
        boolean verify = userService.verify(code);

        String pageTitle = verify ? "Weryfikacja przebiegła pomyślnie!" : "Weryfikacja nieudana.";
        return "";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request){
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token,email);
        String resetPasswordLink = Utility.getURL(request)+"/reset_password?token=" + token;
        try {
            mailService.sendMailResetPassword(email,resetPasswordLink);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(String token){
        UserDTO user = userService.getUserByResetPasswordToken(token);

        if(user == null){

        }
        return "";
    }

    @PostMapping("/reset_password")
    public String processResetPasswordForm(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");

        UserDTO user = userService.getUserByResetPasswordToken(token);

        if(user == null){

        }else {
            userService.updatePassword(user,password );
        }

        return "";
    }

    @GetMapping("/city")
    public String getUsersByCity(){
        userService.getAllUsersByCity();

        return "";
    }

    @GetMapping("/name")
    public String getUsersByName(){
        userService.getAllUsersByName();

        return "";
    }

    @GetMapping("/surname")
    public String getUsersBySurname(){
        userService.getAllUsersBySurname();

        return "";
    }

    @GetMapping("/email")
    public String getUsersByEmail(){
        userService.getAllUsersByUsername();

        return "";
    }

    @GetMapping("/pesel")
    public String getUsersByPesel(){
        userService.getAllUsersByPESEL();

        return "";
    }
}
