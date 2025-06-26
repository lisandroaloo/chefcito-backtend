package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.LoginDto;
import com.chefcito.chefcitobackend.dto.RequestUserDto;
import com.chefcito.chefcitobackend.dto.ResponseUserDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.User;
import com.chefcito.chefcitobackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  public ResponseUserDto login(LoginDto loginDto) {
    User user = userRepository.findByUs_alias(loginDto.getUsername());
    if (user.getUs_password() == loginDto.getPassword()) {
        return ResponseUserDto.toResponseUserDto(user);

    }
    return null;
  }


  public ResponseUserDto addUser(RequestUserDto userDto) {
    User parsedUser = RequestUserDto.toUser(userDto);
    User responseUser = userRepository.save(parsedUser);
    return ResponseUserDto.toResponseUserDto(responseUser);
  }

  public List<ResponseUserDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(ResponseUserDto::toResponseUserDto)
        .collect(Collectors.toList());
  }

  public ResponseUserDto getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", id));
    return ResponseUserDto.toResponseUserDto(user);
  }

  public ResponseUserDto updateUser(Long id, RequestUserDto userDto) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", id));
    
    existingUser.setUs_alias(userDto.getUs_alias());
    existingUser.setUs_email(userDto.getUs_email());
    existingUser.setUs_password(userDto.getUs_password());
    existingUser.setUs_password_salt(userDto.getUs_password_salt());
    User updatedUser = userRepository.save(existingUser);
    return ResponseUserDto.toResponseUserDto(updatedUser);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new ResourceNotFoundException("User", id);
    }
    userRepository.deleteById(id);
  }
}
