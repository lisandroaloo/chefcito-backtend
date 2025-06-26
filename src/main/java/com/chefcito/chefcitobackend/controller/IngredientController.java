package com.chefcito.chefcitobackend.controller;

import com.chefcito.chefcitobackend.dto.RequestIngredientDto;
import com.chefcito.chefcitobackend.dto.ResponseIngredientDto;
import com.chefcito.chefcitobackend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

  @Autowired
  private IngredientService ingredientService;

  @PostMapping
  public ResponseEntity<ResponseIngredientDto> addIngredient(@RequestBody RequestIngredientDto ingredient) {
    return new ResponseEntity<>(ingredientService.addIngredient(ingredient), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ResponseIngredientDto>> getAllIngredients() {
    return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseIngredientDto> getIngredientById(@PathVariable Long id) {
    return new ResponseEntity<>(ingredientService.getIngredientById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseIngredientDto> updateIngredient(@PathVariable Long id, @RequestBody RequestIngredientDto ingredient) {
    return new ResponseEntity<>(ingredientService.updateIngredient(id, ingredient), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
    ingredientService.deleteIngredient(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
