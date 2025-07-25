package User.Login.com.example.Backend.repository;

import User.Login.com.example.Backend.constant.CommonStatus;
import User.Login.com.example.Backend.constant.ERole;
import User.Login.com.example.Backend.entity.Role;
import User.Login.com.example.Backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long> {


     Optional<User> findByUserName(String username);


     boolean existsByUserName(String userName);

     boolean existsByEmail(String email);

     boolean existsByRollRoleName(ERole role);


     Page<User> findByCommonStatus(CommonStatus commonStatusEnum, Pageable pageable);
}
