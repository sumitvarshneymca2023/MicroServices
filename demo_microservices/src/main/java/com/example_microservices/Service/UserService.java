package com.example_microservices.Service;

import com.example_microservices.Dto.GetUserDto;
import com.example_microservices.Dto.UserAddDto;

import java.util.Map;

public interface UserService {

    String saveUserDetails(UserAddDto userAddDto);

    Map<String, Object> findUserById(Long userId);
}
