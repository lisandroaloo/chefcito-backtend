package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.RequestIngredientXRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseIngredientXRecipeDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.Ingredient;
import com.chefcito.chefcitobackend.model.IngredientXRecipe;
import com.chefcito.chefcitobackend.model.Recipe;
import com.chefcito.chefcitobackend.repository.IIngredientRepository;
import com.chefcito.chefcitobackend.repository.IIngredientXRecipeRepository;
import com.chefcito.chefcitobackend.repository.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientXRecipeService {

  @Autowired
  private IIngredientXRecipeRepository ingredientXRecipeRepository;

  @Autowired
  private IIngredientRepository ingredientRepository;

  @Autowired
  private IRecipeRepository recipeRepository;

  public ResponseIngredientXRecipeDto addIngredientXRecipe(RequestIngredientXRecipeDto ingredientXRecipeDto) {
    IngredientXRecipe parsedIngredientXRecipe = RequestIngredientXRecipeDto.toIngredientXRecipe(ingredientXRecipeDto);
    
    if (ingredientXRecipeDto.getIxr_in_id() != null) {
      Ingredient ingredient = ingredientRepository.findById(ingredientXRecipeDto.getIxr_in_id())
          .orElseThrow(() -> new ResourceNotFoundException("Ingredient", ingredientXRecipeDto.getIxr_in_id()));
      parsedIngredientXRecipe.setIngredient(ingredient);
    }
    
    if (ingredientXRecipeDto.getIxr_re_id() != null) {
      Recipe recipe = recipeRepository.findById(ingredientXRecipeDto.getIxr_re_id())
          .orElseThrow(() -> new ResourceNotFoundException("Recipe", ingredientXRecipeDto.getIxr_re_id()));
      parsedIngredientXRecipe.setRecipe(recipe);
    }
    
    IngredientXRecipe responseIngredientXRecipe = ingredientXRecipeRepository.save(parsedIngredientXRecipe);
    return ResponseIngredientXRecipeDto.toResponseIngredientXRecipeDto(responseIngredientXRecipe);
  }

  public List<ResponseIngredientXRecipeDto> getAllIngredientXRecipes() {
    List<IngredientXRecipe> ingredientXRecipes = ingredientXRecipeRepository.findAll();
    return ingredientXRecipes.stream()
        .map(ResponseIngredientXRecipeDto::toResponseIngredientXRecipeDto)
        .collect(Collectors.toList());
  }

  public ResponseIngredientXRecipeDto getIngredientXRecipeById(Long id) {
    IngredientXRecipe ingredientXRecipe = ingredientXRecipeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("IngredientXRecipe", id));
    return ResponseIngredientXRecipeDto.toResponseIngredientXRecipeDto(ingredientXRecipe);
  }

  public ResponseIngredientXRecipeDto updateIngredientXRecipe(Long id, RequestIngredientXRecipeDto ingredientXRecipeDto) {
    IngredientXRecipe existingIngredientXRecipe = ingredientXRecipeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("IngredientXRecipe", id));
    
    existingIngredientXRecipe.setQuantity(ingredientXRecipeDto.getQuantity());
    
    if (ingredientXRecipeDto.getIxr_in_id() != null) {
      Ingredient ingredient = ingredientRepository.findById(ingredientXRecipeDto.getIxr_in_id())
          .orElseThrow(() -> new ResourceNotFoundException("Ingredient", ingredientXRecipeDto.getIxr_in_id()));
      existingIngredientXRecipe.setIngredient(ingredient);
    }
    
    if (ingredientXRecipeDto.getIxr_re_id() != null) {
      Recipe recipe = recipeRepository.findById(ingredientXRecipeDto.getIxr_re_id())
          .orElseThrow(() -> new ResourceNotFoundException("Recipe", ingredientXRecipeDto.getIxr_re_id()));
      existingIngredientXRecipe.setRecipe(recipe);
    }
    
    IngredientXRecipe updatedIngredientXRecipe = ingredientXRecipeRepository.save(existingIngredientXRecipe);
    return ResponseIngredientXRecipeDto.toResponseIngredientXRecipeDto(updatedIngredientXRecipe);
  }

  public void deleteIngredientXRecipe(Long id) {
    if (!ingredientXRecipeRepository.existsById(id)) {
      throw new ResourceNotFoundException("IngredientXRecipe", id);
    }
    ingredientXRecipeRepository.deleteById(id);
  }
}
