package com.example_microservices.Service;


import com.example_microservices.Dto.GetUserDto;
import com.example_microservices.Dto.UserAddDto;
import com.example_microservices.Entity.User;
import com.example_microservices.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveUserDetails(UserAddDto userAddDto) {

        try {
            User user = new User();
            user.setFirstName(userAddDto.getFirstName());
            user.setLastName(userAddDto.getLastName());
            user.setEmail(userAddDto.getEmail());
            user.setMobileNumber(userAddDto.getMobileNumber());
            user.setAddress(userAddDto.getAddress());
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while save user details - {}", e.getMessage());
            return "An error occurred while saving user details";
        }
        return "User details saved successfully" ;
    }

    @Override
    public Map<String, Object> findUserById(Long userId) {
        Map<String, Object> result = new HashMap<>();

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            GetUserDto userDto = modelMapper.map(user.get(), GetUserDto.class);

            Map<String, Object> userData = new HashMap<>();
            userData.put("userId", userDto.getUserId());
            userData.put("firstName", userDto.getFirstName());
            userData.put("lastName", userDto.getLastName());
            userData.put("mobileNumber", userDto.getMobileNumber());
            userData.put("email", userDto.getEmail());
            userData.put("address", userDto.getAddress());
            result.put("Success", "User details fetched successfully");
            result.put("Data", userData);
        } else {
            result.put("error", "User not found");
        }

        return result;
    }



}
