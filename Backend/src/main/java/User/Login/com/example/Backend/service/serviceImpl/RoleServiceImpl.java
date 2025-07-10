package User.Login.com.example.Backend.service.serviceImpl;

import User.Login.com.example.Backend.constant.CommonStatus;
import User.Login.com.example.Backend.constant.ERole;
import User.Login.com.example.Backend.entity.Role;
import User.Login.com.example.Backend.entity.User;
import User.Login.com.example.Backend.repository.RoleRepository;
import User.Login.com.example.Backend.repository.UserRepository;
import User.Login.com.example.Backend.service.RoleService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @PostConstruct
    public void autoSaveRollAndAdminOnStartup() {

        if (roleRepository.findByRoleName(ERole.ADMIN).isEmpty()) {
                    Role newRole = new Role();
                    newRole.setRoleName(ERole.ADMIN);
                    newRole.setCommonStatus(CommonStatus.ACTIVE);
                    roleRepository.save(newRole);


                }

        if (roleRepository.findByRoleName(ERole.EMPLOYEE).isEmpty()) {
            Role employeeRole = new Role();
            employeeRole.setRoleName(ERole.EMPLOYEE);
            employeeRole.setCommonStatus(CommonStatus.ACTIVE);
            roleRepository.save(employeeRole);
        }
    }


}
