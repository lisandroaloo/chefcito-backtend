package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.LoginDto;
import com.chefcito.chefcitobackend.dto.RequestUserDto;
import com.chefcito.chefcitobackend.dto.ResponseUserDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.User;
import com.chefcito.chefcitobackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private EmailService emailService;

  public ResponseUserDto login(LoginDto loginDto) {
    User user = userRepository.findByUsernameee(loginDto.getUsername());
    if (user.getUs_password().equals(loginDto.getPassword())) {
      return ResponseUserDto.toResponseUserDto(user);
    }
    return null;
  }

  public ResponseEntity<?> recoverPassword(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      return ResponseEntity.ok().build();
    }
    
    String recoveryCode = String.valueOf((int) (Math.random() * 900000) + 100000); // ejemplo: 6 dígitos

    // 2. Guardarlo en el usuario o en algún lugar (opcional)
    user.setUs_recovery_code(recoveryCode);
    userRepository.save(user); // si lo guardás en el modelo

    // 3. Enviar el mail
    emailService.sendRecoveryEmail(email, recoveryCode);

    return ResponseEntity.ok().build();

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

    existingUser.setUsAlias(userDto.getUs_alias());
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
