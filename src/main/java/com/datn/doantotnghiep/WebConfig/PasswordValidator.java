package com.datn.doantotnghiep.WebConfig;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    // Regex kiểm tra mật khẩu 8-16 ký tự, ít nhất 1 ký tự đặc biệt, 1 số, 1 chữ hoa
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!*()\\-])(?=\\S+$).{8,16}$";

    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(String password) {
        if (password == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String getPasswordRequirementsMessage() {
        return "Mật khẩu phải có độ dài từ 8-16 ký tự, bao gồm ít nhất 1 ký tự đặc biệt, 1 ký tự số và 1 ký tự hoa";
    }
}
