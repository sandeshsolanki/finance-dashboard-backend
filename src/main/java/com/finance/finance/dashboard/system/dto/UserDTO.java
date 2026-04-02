package com.finance.finance.dashboard.system.dto;




import com.finance.finance.dashboard.system.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
