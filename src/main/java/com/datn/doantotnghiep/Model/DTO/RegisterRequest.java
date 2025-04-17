package com.datn.doantotnghiep.Model.DTO;

import com.datn.doantotnghiep.WebConfig.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterRequest {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;

    @ValidPassword
    private String password;
}
