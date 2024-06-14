package com.tamtvh.be.dto;

import com.tamtvh.be.model.Role;
import lombok.Data;

@Data
public class AccountDTO {
    private String username;
    private String password;
    private Role role;

    public static String convertRole(String role) {
        String roleConverted = "";
        switch (role) {
            case "ADMIN" :
                roleConverted = "staff";
                break;
            case "KHACHHANG":
                roleConverted = "customer";
                break;
            case "NVGH":
                roleConverted = "shipper";
                break;
        }
        return roleConverted;
    }
}
