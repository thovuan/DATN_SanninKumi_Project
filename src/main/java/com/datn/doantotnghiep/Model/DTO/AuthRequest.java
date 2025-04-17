package com.datn.doantotnghiep.Model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthRequest {
    private String username;
    private String password;
}
