package com.chefcito.chefcitobackend.controller;

import com.chefcito.chefcitobackend.dto.RequestFiltersDto;
import com.chefcito.chefcitobackend.dto.RequestRecipeByUserDto;
import com.chefcito.chefcitobackend.dto.RequestRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseReviewDto;
import com.chefcito.chefcitobackend.dto.ReviewRecipeDto;
import com.chefcito.chefcitobackend.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  @PostMapping
  public ResponseEntity<ResponseRecipeDto> addRecipe(@RequestBody RequestRecipeDto recipe) {
    return new ResponseEntity<>(recipeService.addRecipe(recipe), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ResponseRecipeDto>> getAllRecipes() {
    return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
  }

  @GetMapping("reviews/{id}")
  public ResponseEntity<List<ResponseReviewDto>> getAllReviews(@PathVariable Long id) {
    return new ResponseEntity<>(recipeService.getAllReviews(id), HttpStatus.OK);
  }

  @GetMapping("check-review/{recipeId}/user/{userId}")
  public ResponseEntity<?> checkReview(@PathVariable Long userId, @PathVariable Long recipeId) {
  
    return new ResponseEntity<>(recipeService.checkReview(userId,recipeId), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseRecipeDto> getRecipeById(@PathVariable Long id) {
    return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
  }

  @PostMapping("/user")
  public ResponseEntity<List<ResponseRecipeDto>> getRecipeByUserId(@RequestBody RequestRecipeByUserDto userDto) {
    return new ResponseEntity<>(recipeService.getRecipeByUserId(userDto), HttpStatus.OK);
  }

  @PostMapping("/review")
  public ResponseEntity<ResponseReviewDto> reviewRecipe(@RequestBody ReviewRecipeDto review) {
    return new ResponseEntity<>(recipeService.reviewRecipe(review), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseRecipeDto> updateRecipe(@PathVariable Long id, @RequestBody RequestRecipeDto recipe) {
    return new ResponseEntity<>(recipeService.updateRecipe(id, recipe), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
    recipeService.deleteRecipe(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
