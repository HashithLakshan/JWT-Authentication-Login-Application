package User.Login.com.example.Backend.entity;

import User.Login.com.example.Backend.constant.CommonStatus;
import User.Login.com.example.Backend.constant.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roll")
public class Role {
    @Id
    @Column(name = "roll_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollId;

    @Enumerated(EnumType.STRING)
    private ERole roleName;

    @Enumerated
    private CommonStatus commonStatus;
}
