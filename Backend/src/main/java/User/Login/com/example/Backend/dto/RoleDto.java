package User.Login.com.example.Backend.dto;

import User.Login.com.example.Backend.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDto {

    private String rollId;
    private String roleName;
    private CommonStatus commonStatus;

}
