package com.bng.ecommerce.user.service;

import com.bng.ecommerce.user.controller.dto.AddressDTO;
import com.bng.ecommerce.user.controller.dto.UserRequest;
import com.bng.ecommerce.user.controller.dto.UserResponse;
import com.bng.ecommerce.user.model.Address;
import com.bng.ecommerce.user.model.User;
import com.bng.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList<>();
//    private Long nextId = 1L;

    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){
//        user.setId(nextId++);

        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> fetchUser(String id) {
        return userRepository.findById(String.valueOf(id))
                .map(this::mapToUserResponse);
    }

    public boolean updateUser(String id, UserRequest updatedUserRequest) {
        return userRepository.findById(String.valueOf(id))
                .map(existingUser -> {
                    updateUserFromRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());
        if (userRequest.address() != null) {
            Address address = new Address();
            address.setStreet(userRequest.address().street());
            address.setState(userRequest.address().state());
            address.setZipcode(userRequest.address().zipcode());
            address.setCity(userRequest.address().city());
            address.setCountry(userRequest.address().country());
            user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user){

        AddressDTO addressDTO = user.getAddress() == null ? null : new AddressDTO(
                user.getAddress().getStreet(),
                user.getAddress().getCity(),
                user.getAddress().getState(),
                user.getAddress().getCountry(),
                user.getAddress().getZipcode()
        );
        return new UserResponse(
                String.valueOf(user.getId()),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                addressDTO

        );
    }
}