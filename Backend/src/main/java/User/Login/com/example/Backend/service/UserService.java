package User.Login.com.example.Backend.service;

import User.Login.com.example.Backend.utill.CommonResponse;

public interface UserService {
    CommonResponse getAllUsers( String commonStatus, int page, int size);

    CommonResponse updateStatus(String commonStatus,String userId);
}
