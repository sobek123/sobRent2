package pl.macieksob.rentCar.service;

import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.exception.UserDuplicateException;
import pl.macieksob.rentCar.exception.UserNotFoundException;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.UserRepository;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    private UserDTO mapToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByName(String name){
        return userRepository.findAllByName(name,PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByNameDesc(String name){
        return userRepository.findAllByName(name,PageRequest.of(0,10, Sort.by(Sort.Order.asc("name")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByNameAsc(String name){
        return userRepository.findAllByName(name,PageRequest.of(0,10,Sort.by(Sort.Order.asc("name")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersBySurname(String surname){
        return userRepository.findAllBySurname(surname,PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersBySurnameDesc(String surname){
        return userRepository.findAllBySurname(surname,PageRequest.of(0,10, Sort.by(Sort.Order.asc("surname")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersBySurnameAsc(String surname){
        return userRepository.findAllBySurname(surname,PageRequest.of(0,10,Sort.by(Sort.Order.asc("surname")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByCity(String city){
        return userRepository.findAllByCity(city,PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByCityDesc(String city){
        return userRepository.findAllByCity(city,PageRequest.of(0,10, Sort.by(Sort.Order.asc("city")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByCityAsc(String city){
        return userRepository.findAllByCity(city,PageRequest.of(0,10,Sort.by(Sort.Order.asc("city")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    private User mapToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO addUser(UserDTO user)  {
        if(userRepository.existsById(user.getId())){
            throw new UserDuplicateException("User already exists!");
        }
        User user1 = mapToEntity(user);
//        user1.setPassword(passwordEncoder.encode(user.getPassword()));


         userRepository.save(user1);
         return user;
    }

    public String manOrWoman(UserDTO user){
        if(user.getName().endsWith("a")){
            return "Szanowna Pani ";
        }
        else {
            return "Szanowny Panie ";
        }
    }
    public void sendVerificationEmail(UserDTO user, String url) throws MessagingException, UnsupportedEncodingException {
        String subject  = "Potwierdzenie rejestracji";
        String from = "RentCar";
        String message = "<p>" + manOrWoman(user) + user.getName() + " " + user.getSurname() + ",</p>";
        message += "<p>Aby korzystać z konta musisz potwierdzić rejestrację. Zrobisz to klikając w poniższy link.</p>";
        String verifyURL = url + "/verify?code=" + user.getVerificationCode();
        message += "<h4><a href=\""+verifyURL+"\">WERYFIKACJA</a></h4>";

        message += "<p>Pozdrawiamy, <br> zespół RentCar!</p>";


        mailService.sendMail(from,"macieksob25@gmail.com",user.getEmail(), message,subject,true);
    }

    public void deleteUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found!");
        }
        userRepository.deleteById(id);
    }

    public UserDTO editUser(Long id, UserDTO editUser){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });

        user.setCity(editUser.getCity());
        user.setEmail(editUser.getEmail());
        user.setNumberOfFlat(editUser.getNumberOfFlat());
        user.setPhoneNumber(editUser.getPhoneNumber());
        user.setStreet(editUser.getStreet());
        user.setPostCode(editUser.getStreet());
        user.setCreatedTime(LocalDateTime.now());
        String make = RandomString.make(64);
        user.setVerificationCode(make);
         userRepository.save(user);
         return mapToDTO(user);
    }

    public void deleteUser(UserDTO user){
        User user1 = mapToEntity(user);

        userRepository.delete(user1);
    }

//    public java.util.List<UserDTO> getByKeyword(String keyword){
//        return userRepository.findAllByKeyword(keyword, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

    public boolean verify(String verificationCode){
        User user = userRepository.findByVerificationCode(verificationCode);

        if(user == null || user.getEnabled()){
            return false;
        }else {
            userRepository.setEnable(user.getId());

            return true;
        }

    }

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException{
        User user = userRepository.findByEmail(email).orElseThrow(() -> {throw new UserNotFoundException("User not found!");});
        UserDTO userDTO = mapToDTO(user);
        if(user != null){
            user.setResetPasswordToken(token);
            addUser(userDTO);
        }else{
            throw new UserNotFoundException("User not found!");
        }
    }

    public UserDTO getUserByResetPasswordToken(String resetPasswordToken){
        return mapToDTO(userRepository.findByResetPasswordToken(resetPasswordToken));
    }

    public void updatePassword(UserDTO user, String newPassword){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);

        user.setPassword(encodePassword);
        user.setResetPasswordToken(null);

        addUser(user);
    }

    public UserDTO getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });

        return mapToDTO(user);
    }

    public UserDTO findByPassword(String password){
        User byPassword = userRepository.findByPassword(password).orElseThrow(() -> {throw new UserNotFoundException("User not found!");});
        return mapToDTO(byPassword);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
