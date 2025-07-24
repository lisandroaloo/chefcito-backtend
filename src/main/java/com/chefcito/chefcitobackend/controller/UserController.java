package com.chefcito.chefcitobackend.controller;

import com.chefcito.chefcitobackend.dto.EmailRequestDto;
import com.chefcito.chefcitobackend.dto.LoginDto;
import com.chefcito.chefcitobackend.dto.RecoverPasswordDto;
import com.chefcito.chefcitobackend.dto.RequestUserDto;
import com.chefcito.chefcitobackend.dto.ResponseUserDto;
import com.chefcito.chefcitobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<ResponseUserDto> login(@RequestBody LoginDto loginDto) {
    return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
  }

  @PostMapping("/recover-password")
  public ResponseEntity<?> recover(@RequestBody EmailRequestDto dto) {
    return new ResponseEntity<>(userService.recoverPassword(dto.getEmail()), HttpStatus.OK);
  }

  @PostMapping("check-password")
  public ResponseEntity<?> checkPassword(@RequestBody RecoverPasswordDto dto) {
    return new ResponseEntity<>(userService.checkPassword(dto), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ResponseUserDto> addUser(@RequestBody RequestUserDto user) {
    return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ResponseUserDto>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseUserDto> updateUser(@PathVariable Long id, @RequestBody RequestUserDto user) {
    return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
