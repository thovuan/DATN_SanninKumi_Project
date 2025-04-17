package com.datn.doantotnghiep.Model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AuthResponse {
    private String message;
    private String token;
    private boolean authenticated;

    public AuthResponse(String message, String token, boolean authenticated) {
        this.message = message;
        this.token = token;
        this.authenticated = authenticated;
    }
}
