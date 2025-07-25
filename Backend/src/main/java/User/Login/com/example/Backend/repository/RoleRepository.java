package User.Login.com.example.Backend.repository;

import User.Login.com.example.Backend.constant.ERole;
import User.Login.com.example.Backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(ERole eRole);



}

