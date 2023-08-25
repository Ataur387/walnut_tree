package com.example.blog.Services;

import com.example.blog.DTO.UserDTO.UserCreateDTO;
import com.example.blog.DTO.UserDTO.UserResponseDTO;
import com.example.blog.Entity.*;
import com.example.blog.Repositories.BillingAddressRepository;
import com.example.blog.Repositories.CardInformationRepository;
import com.example.blog.Repositories.RoleRepository;
import com.example.blog.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BillingAddressRepository billingAddressRepo;
    private final CardInformationRepository cardInformationRepo;
    private final EmailService emailService;
    ModelMapper modelMapper = new ModelMapper();

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BillingAddressRepository billingAddress, CardInformationRepository cardInformation, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.billingAddressRepo = billingAddress;
        this.cardInformationRepo = cardInformation;
        this.emailService = emailService;
    }
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        if(userCreateDTO.getSubscriptionType() != null)
        {
            if(userCreateDTO.getSubscriptionType().equals("Pro") && validateAge(userCreateDTO)){
                ProUserEntity proUser = new ProUserEntity();
                bindUserData(userCreateDTO, proUser);
                //Setting Up billing Address and Card Information
                BillingAddressEntity billingAddress = new BillingAddressEntity();
                if(userCreateDTO.getBillingAddress() != null){
                    if(userCreateDTO.getBillingAddress().getId() != null){
                        billingAddress = billingAddressRepo.findById(userCreateDTO.getBillingAddress().getId()).orElse(null);
                    }
                    else{
                        modelMapper.map(userCreateDTO.getBillingAddress(), billingAddress);
                    }
                }
                billingAddress = billingAddressRepo.save(billingAddress);
                List<BillingAddressEntity> billingAddressList = new ArrayList<>();
                billingAddressList.add(billingAddress);
                proUser.setBillingAddress(billingAddressList);

                CardInformationEntity cardInformation = new CardInformationEntity();
                if(userCreateDTO.getCardInformation() != null){
                    modelMapper.map(userCreateDTO.getCardInformation(), cardInformation);
                }
                cardInformation = cardInformationRepo.save(cardInformation);
                List<CardInformationEntity> cardInformationEntities = new ArrayList<>();
                cardInformationEntities.add(cardInformation);
                proUser.setCardInformation(cardInformationEntities);

                proUser = userRepository.save(proUser);

                cardInformation.setCardHolder(proUser);
                cardInformationRepo.save(cardInformation);
                List<ProUserEntity> proUsers = new ArrayList<>();
                proUsers.add(proUser);
                billingAddress.setProUsers(proUsers);
                billingAddressRepo.save(billingAddress);

                modelMapper.map(proUser, userResponseDTO);
            }
        }
        else{
            UserEntity newUser = new UserEntity();
            bindUserData(userCreateDTO, newUser);
            newUser = userRepository.save(newUser);
            modelMapper.map(newUser, userResponseDTO);
            sendConfirmationMail(newUser);
        }
        return userResponseDTO;
    }

    private void sendConfirmationMail(UserEntity newUser) {
        String registrationLink = "http://127.0.0.1:8080/users/email-confirmation/" + newUser.getEmail();
        String disguisedLinkText = "Click here to validate your email: " + registrationLink;

        String emailText = "Hello " + newUser.getFirstName() + ",\n\n"
                + "Thank you for registering with our platform. "
                + "To complete your registration, please " + disguisedLinkText + "\n\n"
                + "If you did not request this registration, please ignore this email.";
        emailService.sendEmail(newUser.getEmail(), "Registration", emailText);
    }

    private boolean validateAge(UserCreateDTO dto) {
        return dto.getDateOfBirth().getYear() + 15 < Year.now().getValue();
    }

    private void bindUserData(UserCreateDTO userCreateDTO, UserEntity user) {
        user.setUserName(userCreateDTO.getUserName());
        user.setPassword(userCreateDTO.getPassword());
        user.setUserStatus(userCreateDTO.getStatus());
        if(userCreateDTO.getDateOfBirth() != null)user.setDateOfBirth(userCreateDTO.getDateOfBirth().toLocalDate());
        user.setEmail(userCreateDTO.getEmail());
        user.setLastName(userCreateDTO.getLastName());
        user.setFirstName(userCreateDTO.getFirstName());
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity newRole = new RoleEntity();
        newRole.setRoleType(userCreateDTO.getRoleType());
        newRole = roleRepository.save(newRole);
        roleEntityList.add(newRole);
        user.setRolesList(roleEntityList);
    }

    public UserResponseDTO getUser(String uuid) {
        UserEntity user = userRepository.findById(uuid).orElse(null);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        modelMapper.map(user, userResponseDTO);
        return userResponseDTO;
    }
    public UserResponseDTO updateUser(String uuid, UserCreateDTO dto){
        UserEntity user = userRepository.findById(uuid).orElse(null);
        if(dto.getSubscriptionType().equals("Pro")){
            if(validateAge(dto)){
                ProUserEntity pro = new ProUserEntity();
                modelMapper.map(pro, user);
                modelMapper.map(pro, dto);
                //Setting Up billing Address and Card Information
                BillingAddressEntity billingAddress = new BillingAddressEntity();
                if(dto.getBillingAddress().getId() == null){
                    modelMapper.map(dto.getBillingAddress(), billingAddress);
                    billingAddress = billingAddressRepo.save(billingAddress);
                    List<BillingAddressEntity> billingAddressList = new ArrayList<>();
                    billingAddressList.add(billingAddress);
                    pro.setBillingAddress(billingAddressList);
                }
                else{
                    billingAddress = billingAddressRepo.findById(dto.getBillingAddress().getId()).orElse(null);
                    List<BillingAddressEntity> billingAddressList = new ArrayList<>();
                    billingAddressList.add(billingAddress);
                    pro.setBillingAddress(billingAddressList);
                }
                pro = userRepository.save(pro);
                List<ProUserEntity> proUsers = new ArrayList<>();
                proUsers.add(pro);
                billingAddress.setProUsers(proUsers);
                billingAddressRepo.save(billingAddress);

                CardInformationEntity cardInformation = new CardInformationEntity();
                cardInformation = cardInformationRepo.save(cardInformation);
                List<CardInformationEntity> cardInformationEntities = new ArrayList<>();
                cardInformationEntities.add(cardInformation);
                pro.setCardInformation(cardInformationEntities);

                pro = userRepository.save(pro);

                cardInformation.setCardHolder(pro);
                cardInformationRepo.save(cardInformation);
                UserResponseDTO userResponseDTO = new UserResponseDTO();
                modelMapper.map(pro, userResponseDTO);
                return userResponseDTO;
            }else{
                throw new IllegalArgumentException("Your Age is less than required for Pro subscription.");
            }
        }
        else{
            modelMapper.map(user, dto);
            user = userRepository.save(user);
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            modelMapper.map(user, userResponseDTO);
            return userResponseDTO;
        }
    }
    public UserResponseDTO upgradeNormalUserToPro(UserCreateDTO createDTO) {
        UserEntity existingUser = userRepository.findById(createDTO.getUuid()).orElse(null);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        if (createDTO.getSubscriptionType() != null) {
            if (createDTO.getSubscriptionType().equals("Pro") && validateAge(createDTO)) {
                ProUserEntity proUser = new ProUserEntity();
                convertNormalUserToPro(existingUser, proUser);
                proUser.setDateOfBirth(createDTO.getDateOfBirth().toLocalDate());
                //Setting Up billing Address and Card Information
                BillingAddressEntity billingAddress = new BillingAddressEntity();
                if (createDTO.getBillingAddress() != null) {
                    if (createDTO.getBillingAddress().getId() != null) {
                        billingAddress = billingAddressRepo.findById(createDTO.getBillingAddress().getId()).orElse(null);
                    } else {
                        modelMapper.map(createDTO.getBillingAddress(), billingAddress);
                    }
                }
                billingAddress = billingAddressRepo.save(billingAddress);
                List<BillingAddressEntity> billingAddressList = new ArrayList<>();
                billingAddressList.add(billingAddress);
                proUser.setBillingAddress(billingAddressList);

                CardInformationEntity cardInformation = new CardInformationEntity();
                if (createDTO.getCardInformation() != null) {
                    modelMapper.map(createDTO.getCardInformation(), cardInformation);
                }
                cardInformation = cardInformationRepo.save(cardInformation);
                List<CardInformationEntity> cardInformationEntities = new ArrayList<>();
                cardInformationEntities.add(cardInformation);
                proUser.setCardInformation(cardInformationEntities);

                proUser = userRepository.save(proUser);

                cardInformation.setCardHolder(proUser);
                cardInformationRepo.save(cardInformation);
                List<ProUserEntity> proUsers = new ArrayList<>();
                proUsers.add(proUser);
                billingAddress.setProUsers(proUsers);
                billingAddressRepo.save(billingAddress);

                modelMapper.map(proUser, userResponseDTO);
            }

        }
        return userResponseDTO;
    }

    private void convertNormalUserToPro(UserEntity existingUser, ProUserEntity proUser) {
        proUser.setUserName(existingUser.getUserName());
        proUser.setLastName(existingUser.getLastName());
        proUser.setUserStatus(existingUser.getUserStatus());
        proUser.setFirstName(existingUser.getFirstName());
        proUser.setRolesList(existingUser.getRolesList());
        proUser.setPassword(existingUser.getPassword());
    }

    public void updateStatus(String address) {
        UserEntity userEntity = userRepository.findByEmail(address);
        userEntity.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(userEntity);
    }

//    public void authenticate(LoginDTO loginDTO) {
//      //  UserEntity user = userRepository.findBy(loginDTO.getUserName()).orElse(null);
//    }
}
