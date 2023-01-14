package pl.macieksob.rentCar.service;

import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.exception.UserDuplicateException;
import pl.macieksob.rentCar.exception.UserNotFoundException;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.Contact;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.UserRepository;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;
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

    @Autowired
    private CardService cardService;

    private Contact mapToEntity(ContactDTO contactDTO){
        return modelMapper.map(contactDTO, Contact.class);
    }

    public void changePassword(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }

//    private ContactDTO mapToDTO(Contact contact){
//        return modelMapper.map(contact, ContactDTO.class);
//    }

    private UserDTO mapToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        List<User> all = userRepository.findAll();
        List<UserDTO> res = new ArrayList<>();
        for (User user : all) {
            List<Role> roles = (List<Role>) user.getRoles();
            for (Role role : roles) {
                if (role.getName().equals("ROLE_USER")) {
                    res.add(mapToDTO(user));
                }
            }
        }
        return res;
    }

    public List<UserDTO> getAllWorkers(){
        List<User> all = userRepository.findAll();
        List<UserDTO> res = new ArrayList<>();
        for (User user : all) {
            List<Role> roles = (List<Role>) user.getRoles();
            for (Role role : roles) {
                if (role.getName().equals("ROLE_WORKER")) {
                    res.add(mapToDTO(user));
                }
            }
        }

        return res;
    }

    public List<UserDTO> getAllUsersByName(String name){
        return userRepository.findAllByName(name, Sort.by(Sort.Order.asc("name"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByNameDesc(String name){
        return userRepository.findAllByName(name, Sort.by(Sort.Order.asc("name"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByNameAsc(String name){
        return userRepository.findAllByName(name,Sort.by(Sort.Order.asc("name"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersBySurname(String surname){
        return userRepository.findAllBySurname(surname, Sort.by(Sort.Order.asc("surname"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersBySurnameDesc(String surname){
        return userRepository.findAllBySurname(surname, Sort.by(Sort.Order.asc("surname"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersBySurnameAsc(String surname){
        return userRepository.findAllBySurname(surname,Sort.by(Sort.Order.asc("surname"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByCity(String city){
        return userRepository.findAllByCity(city, Sort.by(Sort.Order.asc("city"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByCityDesc(String city){
        return userRepository.findAllByCity(city, Sort.by(Sort.Order.asc("city"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByCityAsc(String city){
        return userRepository.findAllByCity(city,Sort.by(Sort.Order.asc("city"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }



    private User mapToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    @Transactional
    public UserDTO addUser(UserDTO user)  {
//        if(userRepository.existsById(user.getId())){
//            throw new UserDuplicateException("User already exists!");
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(new Role("ROLE_USER")));

        user.setCreatedTime(LocalDateTime.now());
        user.setEnabled(true);
        User user1 = mapToEntity(user);
        if(user1.getCard() != null){cardService.addPoints(user1.getCard());}

         userRepository.save(user1);
         return user;
    }

    @Transactional
    public UserDTO addWorker(UserDTO user)  {
//        if(userRepository.existsById(user.getId())){
//            throw new UserDuplicateException("User already exists!");
//        }
        User user1 = mapToEntity(user);
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRoles(Set.of(new Role("ROLE_WORKER")));
        String randomCode = RandomString.make(64);
        user1.setVerificationCode(randomCode);
        user1.setCreatedTime(LocalDateTime.now());
        cardService.addPoints(user1.getCard());
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
        String from = "SobRent";
        String message = "<p>" + manOrWoman(user) + user.getName() + " " + user.getSurname() + ",</p>";
        message += "<p>Aby korzystać z konta musisz potwierdzić rejestrację. Zrobisz to klikając w poniższy link.</p>";
        String verifyURL = url + "/verify/code=" + user.getVerificationCode();
        message += "<h4><a href=\""+verifyURL+"\">WERYFIKACJA</a></h4>";

        message += "<p>Pozdrawiamy, <br> zespół SobRent!</p>";


        mailService.sendMail(from,"macieksob25@gmail.com",user.getEmail(), message,subject,true);
    }
    @Transactional
    public void deleteUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found!");
        }
        userRepository.deleteById(id);
    }

    public void minusPoints(UserDTO userDTO,int points){
        User user = mapToEntity(userDTO);
        User userFound = userRepository.findById(user.getId()).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });

        Integer points1 = userFound.getCard().getPoints();
        userFound.getCard().setPoints(points1-points);

        userRepository.save(userFound);
    }


    @Transactional
    public UserDTO editUser(Long id, UserDTO editUser){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });

        user.setCity(editUser.getCity());
        user.setEmail(editUser.getEmail());
        user.setNumberOfFlat(editUser.getNumberOfFlat());
        user.setPhoneNumber(editUser.getPhoneNumber());
        user.setStreet(editUser.getStreet());
        user.setPostCode(editUser.getPostCode());
        user.setEnabled(editUser.getEnabled());
//        user.setCreatedTime(LocalDateTime.now());
//        String make = RandomString.make(64);
//        user.setVerificationCode(make);
         userRepository.save(user);
         return mapToDTO(user);
    }
    public List<UserDTO> sortByNameAndSurnameAsc(String role){
        List<UserDTO> all = userRepository.findAll(Sort.by("name", "surname").ascending()).stream().map(this::mapToDTO).collect(Collectors.toList());
        return getLists(role, all);
    }

    public List<UserDTO> sortByNameAndSurnameDesc(String role){
        List<UserDTO> all = userRepository.findAll(Sort.by("name", "surname").descending()).stream().map(this::mapToDTO).collect(Collectors.toList());
        return getLists(role, all);
    }

    public List<UserDTO> getLists(String role, List<UserDTO> all) {
        List<UserDTO> res = new ArrayList<>();

        if(role.equals("ROLE_WORKER")) {
            for (UserDTO user : all) {
                List<Role> roles = (List<Role>) user.getRoles();
                for (Role role2 : roles) {
                    if (role2.getName().equals("ROLE_WORKER")) {
                        res.add(user);
                    }
                }
            }
        }else{
            for (UserDTO user : all) {
                List<Role> roles = (List<Role>) user.getRoles();
                for (Role role2 : roles) {
                    if (role2.getName().equals("ROLE_USER")) {
                        res.add(user);
                    }
                }
            }
        }
        return res;
    }

    @Transactional
    public void deleteUser(UserDTO user){
        User user1 = mapToEntity(user);

        userRepository.delete(user1);
    }

    public java.util.List<UserDTO> getByKeyword(String keyword, String role){
        List<UserDTO> all = userRepository.getByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
        return getLists(role, all);

    }

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
            userRepository.save(user);
        }else{
            throw new UserNotFoundException("User not found!");
        }
    }

    public User getUserByResetPasswordToken(String resetPasswordToken){
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public boolean findByPesel(String pesel){
        Optional<User> byPesel = userRepository.findByPesel(pesel);
        return byPesel.isPresent();
    }

    public User findByPhoneNumber(String pesel) {
        Optional<User> byEmail = userRepository.findByPhoneNumber(pesel);

        return byEmail.get();
    }

    public boolean checkEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    public UserDTO findByEmail(String pesel) {
        User user = userRepository.findByEmail(pesel).orElseThrow();
        UserDTO userDTO = mapToDTO(user);
        userDTO.setRole(user.getRoles().stream().findFirst().orElseThrow().getName());
        return userDTO;
    }

    @Transactional
    public void updatePassword(UserDTO user, String newPassword){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);
        User user1 = mapToEntity(user);
        user1.setPassword(encodePassword);
        user1.setResetPasswordToken(null);
        
        userRepository.save(user1);
    }

    public UserDTO getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });

        return mapToDTO(user);
    }

    public boolean findByPassword(String password,String email){
        User byPassword = userRepository.findByEmail(email).orElseThrow();
        return passwordEncoder.matches(password, byPassword.getPassword());
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

//    public void addContact(ContactDTO contactDTO){
//        User user = userRepository.findByEmail(contactDTO.getEmail()).orElseThrow(() -> {
//            throw new UserNotFoundException("User not found!");
//        });
//
//        Set<Contact> contact = user.getContact();
//        Contact contact1 = mapToEntity(contactDTO);
//        contact1.setDate(LocalDateTime.now());
//        contact.add(contact1);
//        user.setContact(contact);
//        userRepository.save(user);
//    }


//    public List<UserDTO> getByKeyword(String keyword) {
//        return userRepository.getByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
    public User makeCard(UserDTO user){
        User user1 = mapToEntity(user);
        User user2 = userRepository.findById(user.getId()).orElseThrow(() -> {
            throw new UserNotFoundException("User not found!");
        });
        user2.setCard(new Card());
        return userRepository.save(user2);
    }


    public Optional<User> validUsernameAndPassword(String username, String password) {
        return userRepository.findByEmail(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    public void respondTo(String from,String content, String title, String email ) throws MessagingException, UnsupportedEncodingException {
        mailService.sendMail("SobRent",from,email, content,title,true);
    }

}
