package com.ripple.vmprovisioning.security.model;

import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String name;
}
