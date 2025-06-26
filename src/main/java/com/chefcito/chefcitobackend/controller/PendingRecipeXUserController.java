package com.chefcito.chefcitobackend.controller;

import com.chefcito.chefcitobackend.dto.RequestPendingRecipeXUserDto;
import com.chefcito.chefcitobackend.dto.ResponsePendingRecipeXUserDto;
import com.chefcito.chefcitobackend.service.PendingRecipeXUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pending-recipe-x-user")
public class PendingRecipeXUserController {

  @Autowired
  private PendingRecipeXUserService pendingRecipeXUserService;

  @PostMapping
  public ResponseEntity<ResponsePendingRecipeXUserDto> addPendingRecipeXUser(@RequestBody RequestPendingRecipeXUserDto pendingRecipeXUser) {
    return new ResponseEntity<>(pendingRecipeXUserService.addPendingRecipeXUser(pendingRecipeXUser), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ResponsePendingRecipeXUserDto>> getAllPendingRecipeXUsers() {
    return new ResponseEntity<>(pendingRecipeXUserService.getAllPendingRecipeXUsers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponsePendingRecipeXUserDto> getPendingRecipeXUserById(@PathVariable Long id) {
    return new ResponseEntity<>(pendingRecipeXUserService.getPendingRecipeXUserById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponsePendingRecipeXUserDto> updatePendingRecipeXUser(@PathVariable Long id, @RequestBody RequestPendingRecipeXUserDto pendingRecipeXUser) {
    return new ResponseEntity<>(pendingRecipeXUserService.updatePendingRecipeXUser(id, pendingRecipeXUser), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePendingRecipeXUser(@PathVariable Long id) {
    pendingRecipeXUserService.deletePendingRecipeXUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
