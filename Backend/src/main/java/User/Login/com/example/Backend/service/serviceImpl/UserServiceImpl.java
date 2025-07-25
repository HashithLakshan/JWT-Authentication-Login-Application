package User.Login.com.example.Backend.service.serviceImpl;

import User.Login.com.example.Backend.constant.CommonStatus;
import User.Login.com.example.Backend.constant.ERole;
import User.Login.com.example.Backend.dto.RoleDto;
import User.Login.com.example.Backend.dto.UserDto;
import User.Login.com.example.Backend.entity.Role;
import User.Login.com.example.Backend.entity.User;
import User.Login.com.example.Backend.repository.RoleRepository;
import User.Login.com.example.Backend.repository.UserRepository;
import User.Login.com.example.Backend.service.UserService;
import User.Login.com.example.Backend.utill.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public CommonResponse getAllUsers( String commonStatus, int page, int size) {
        CommonResponse commonResponse = new CommonResponse();
        Pageable pageable = PageRequest.of(page, size);
        List<UserDto> userDtoList = new ArrayList<>();
        Page<User> userPage;

        try {

            // Convert String to Enum
            CommonStatus commonStatusEnum = CommonStatus.valueOf(commonStatus);


            userPage = userRepository.findByCommonStatus(commonStatusEnum, pageable);



            // Convert Employee entities to DTOs
            userDtoList = userPage.getContent().stream()
                    .map(this::UserIntoUserDto)
                    .collect(Collectors.toList());

            // Prepare pagination details
            Map<String, Object> paginationDetails = new HashMap<>();
            paginationDetails.put("currentPage", userPage.getNumber());
            paginationDetails.put("totalItems", userPage.getTotalElements());
            paginationDetails.put("totalPages", userPage.getTotalPages());

            // Set response payload properly
            commonResponse.setStatus(true);
            commonResponse.setCommonMessage("User fetched successfully.");
            commonResponse.setPayload(Collections.singletonList(userDtoList)); // Employee list
            commonResponse.setPages(Collections.singletonList(paginationDetails));// Pagination details

        } catch (IllegalArgumentException e) {
            commonResponse.setStatus(false);
            commonResponse.setCommonMessage("Invalid Role name or common status.");
        } catch (Exception e) {
            commonResponse.setStatus(false);
            commonResponse.setCommonMessage("An error occurred while fetching users.");
        }

        return commonResponse;
    }

    @Override
    public CommonResponse updateStatus(String commonStatus,String userId) {
        CommonResponse commonResponse = new CommonResponse();
       try {
           CommonStatus commonStatusEnum = CommonStatus.valueOf(commonStatus);
           User user = userRepository.findById(Long.valueOf(userId)).get();
           if (commonStatusEnum.equals(CommonStatus.ACTIVE)) {
               user.setCommonStatus(commonStatusEnum);
               userRepository.save(user);
               commonResponse.setStatus(true);
               commonResponse.setCommonMessage("User recover successfully.");
               return commonResponse;
           } else if (commonStatusEnum.equals(CommonStatus.INACTIVE)) {
               user.setCommonStatus(commonStatusEnum);
               userRepository.save(user);
               commonResponse.setStatus(true);
               commonResponse.setCommonMessage("User delete successfully.");
               return commonResponse;
           } else {
               commonResponse.setStatus(false);
               commonResponse.setCommonMessage("An error occurred while updating user.");
               return commonResponse;
           }
       } catch (Exception e) {
           commonResponse.setStatus(false);
           commonResponse.setCommonMessage("********* UserStatusUpdating*********error******" + e.getMessage());
           return commonResponse;
       }

    }

    private UserDto UserIntoUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(String.valueOf(user.getUserId()));
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setCommonStatus(user.getCommonStatus());
        if (user.getRoll() != null && !user.getRoll().isEmpty()) {
            Role role = user.getRoll().iterator().next(); // gets the first role
            RoleDto roleDto = new RoleDto();
            roleDto.setRoleName(role.getRoleName().name()); // ERole -> String
            userDto.setRoleDto(roleDto);
        }
        return userDto;
    }
}
