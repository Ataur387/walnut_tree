package com.example.blog;

import com.example.blog.DTO.UserDTO.BillingAddressDTO;
import com.example.blog.DTO.UserDTO.CardInformationDTO;
import com.example.blog.DTO.UserDTO.UserCreateDTO;
import com.example.blog.Entity.RoleType;
import com.example.blog.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    //Pro Users with billing new
    @Test
    public void createProUserWithNewBillingAddressTest(){
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setFirstName("Ataur");
        createDTO.setLastName("Rahaman");
        createDTO.setUserName("Ataur387");
        createDTO.setPassword("aaaa");
        createDTO.setRepeatPassword("aaaa");
        createDTO.setDateOfBirth(OffsetDateTime.of(1997, 2, 13, 6, 13, 47, 13, ZoneOffset.ofHours(12)));
        BillingAddressDTO billingAddressDTO = new BillingAddressDTO("Mirpur 10", "Dhaka", "1234");
        createDTO.setBillingAddress(billingAddressDTO);
        createDTO.setRoleType(RoleType.BLOGGER);
        createDTO.setSubscriptionType("Pro");
        CardInformationDTO cardInformationDTO = new CardInformationDTO("Ataur Rahaman", "42424242421228123");
        createDTO.setCardInformation(cardInformationDTO);
        userService.createUser(createDTO);
    }
    //Pro User with existing Billing
    @Test
    public void createProUserWithExistingBillingAddressTest(){
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setFirstName("Ataur");
        createDTO.setLastName("Rahaman");
        createDTO.setUserName("Ataur387");
        createDTO.setPassword("aaaa");
        createDTO.setRepeatPassword("aaaa");
        createDTO.setDateOfBirth(OffsetDateTime.of(1997, 2, 13, 6, 13, 47, 13, ZoneOffset.ofHours(12)));
        BillingAddressDTO billingAddressDTO = new BillingAddressDTO("1");
        createDTO.setBillingAddress(billingAddressDTO);
        createDTO.setRoleType(RoleType.BLOGGER);
        createDTO.setSubscriptionType("Pro");
        CardInformationDTO cardInformationDTO = new CardInformationDTO("Ataur Rahaman", "42424242421228123");
        createDTO.setCardInformation(cardInformationDTO);
        userService.createUser(createDTO);
    }
    //Normal User
    @Test
    public void createNormalUserTest(){
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setFirstName("Ataur");
        createDTO.setLastName("Rahaman");
        createDTO.setUserName("Ataur387");
        createDTO.setPassword("aaaa");
        createDTO.setRepeatPassword("aaaa");
        createDTO.setRoleType(RoleType.BLOGGER);
        createDTO.setDateOfBirth(OffsetDateTime.of(1997, 2, 13, 6, 13, 47, 13, ZoneOffset.ofHours(12)));
        userService.createUser(createDTO);
    }
    //Upgrade Normal To Pro
    @Test
    public void updateNormalUserToPro(){
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setUuid("1");
        createDTO.setDateOfBirth(OffsetDateTime.of(1997, 2, 13, 6, 13, 47, 13, ZoneOffset.ofHours(12)));
        BillingAddressDTO billingAddressDTO = new BillingAddressDTO("Mirpur 10", "Dhaka", "1234");
        createDTO.setBillingAddress(billingAddressDTO);
        createDTO.setRoleType(RoleType.BLOGGER);
        createDTO.setSubscriptionType("Pro");
        CardInformationDTO cardInformationDTO = new CardInformationDTO("Ataur Rahaman", "42424242421228123");
        createDTO.setCardInformation(cardInformationDTO);
        userService.upgradeNormalUserToPro(createDTO);
    }
}
