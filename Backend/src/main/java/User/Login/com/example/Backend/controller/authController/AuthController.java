package User.Login.com.example.Backend.controller.authController;

import User.Login.com.example.Backend.constant.ERole;
import User.Login.com.example.Backend.dto.authDto.JwtResponse;
import User.Login.com.example.Backend.dto.authDto.LoginRequest;
import User.Login.com.example.Backend.dto.authDto.MessageResponse;
import User.Login.com.example.Backend.dto.authDto.SignupRequest;
import User.Login.com.example.Backend.entity.Role;
import User.Login.com.example.Backend.entity.User;
import User.Login.com.example.Backend.repository.RoleRepository;
import User.Login.com.example.Backend.repository.UserRepository;
import User.Login.com.example.Backend.security.authService.UserDetailsImpl;
import User.Login.com.example.Backend.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    RoleRepository rollRepository;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            if (userRepository.existsByUserName(signUpRequest.getUserName())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Username is already taken!", null)
                );
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Email is already in use!", null)
                );
            }

            // Create new user's account
            User user = new User();
            user.setUserName(signUpRequest.getUserName());
            user.setEmail(signUpRequest.getEmail());
            user.setCommonStatus(signUpRequest.getCommonStatus());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(encoder.encode(signUpRequest.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role userRole = rollRepository.findByRoleName(ERole.valueOf(signUpRequest.getRoleDto().getRoleName()))
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            user.setRoll(roles);
            userRepository.save(user);

            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "User Registered Success", user), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "User Register UnSuccess", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/isHaveAdmin")
    public Boolean isHaveAdmin(@RequestParam String eRole) {
        return userRepository.existsByRollRoleName(ERole.valueOf(eRole));
    }

}
