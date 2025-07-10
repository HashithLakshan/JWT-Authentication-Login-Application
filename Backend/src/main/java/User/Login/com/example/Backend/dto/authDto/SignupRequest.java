package User.Login.com.example.Backend.dto.authDto;

import User.Login.com.example.Backend.constant.CommonStatus;
import User.Login.com.example.Backend.dto.RoleDto;
import lombok.Data;

@Data
public class SignupRequest {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private RoleDto roleDto;
    private CommonStatus commonStatus;

}
