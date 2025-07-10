package User.Login.com.example.Backend.dto;

import User.Login.com.example.Backend.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String userId;
    private String userName;
    private String password;
    private String email;
    private RoleDto roleDto;
    private CommonStatus commonStatus;

}
