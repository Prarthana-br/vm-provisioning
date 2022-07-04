package com.ripple.vmprovisioning.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private char[] password;
    private String role;

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setName("Prarthana");
        user.setEmail("brprarthana@gmail.com");
        user.setMobile("8756827865");
        user.setPassword(new String("mysecret").toCharArray());
        user.setRole("Master");
        System.out.println(
        new ObjectMapper().writeValueAsString(user));
    }
}
