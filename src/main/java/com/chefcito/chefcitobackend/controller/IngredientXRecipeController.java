package com.chefcito.chefcitobackend.controller;

import com.chefcito.chefcitobackend.dto.RequestIngredientXRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseIngredientXRecipeDto;
import com.chefcito.chefcitobackend.service.IngredientXRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient-x-recipe")
public class IngredientXRecipeController {

  @Autowired
  private IngredientXRecipeService ingredientXRecipeService;

  @PostMapping
  public ResponseEntity<ResponseIngredientXRecipeDto> addIngredientXRecipe(@RequestBody RequestIngredientXRecipeDto ingredientXRecipe) {
    return new ResponseEntity<>(ingredientXRecipeService.addIngredientXRecipe(ingredientXRecipe), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ResponseIngredientXRecipeDto>> getAllIngredientXRecipes() {
    return new ResponseEntity<>(ingredientXRecipeService.getAllIngredientXRecipes(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseIngredientXRecipeDto> getIngredientXRecipeById(@PathVariable Long id) {
    return new ResponseEntity<>(ingredientXRecipeService.getIngredientXRecipeById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseIngredientXRecipeDto> updateIngredientXRecipe(@PathVariable Long id, @RequestBody RequestIngredientXRecipeDto ingredientXRecipe) {
    return new ResponseEntity<>(ingredientXRecipeService.updateIngredientXRecipe(id, ingredientXRecipe), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIngredientXRecipe(@PathVariable Long id) {
    ingredientXRecipeService.deleteIngredientXRecipe(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
