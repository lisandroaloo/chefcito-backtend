package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.RequestIngredientDto;
import com.chefcito.chefcitobackend.dto.ResponseIngredientDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.Ingredient;
import com.chefcito.chefcitobackend.repository.IIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

  @Autowired
  private IIngredientRepository ingredientRepository;

  public ResponseIngredientDto addIngredient(RequestIngredientDto ingredient) {
    Ingredient parsedIngredient = RequestIngredientDto.toIngredient(ingredient);
    Ingredient responseIngredient = ingredientRepository.save(parsedIngredient);
    return ResponseIngredientDto.toResponseIngredientDto(responseIngredient);
  }

  public List<ResponseIngredientDto> getAllIngredients() {
    List<Ingredient> ingredients = ingredientRepository.findAll();
    return ingredients.stream()
        .map(ResponseIngredientDto::toResponseIngredientDto)
        .collect(Collectors.toList());
  }

  public ResponseIngredientDto getIngredientById(Long id) {
    Ingredient ingredient = ingredientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Ingredient", id));
    return ResponseIngredientDto.toResponseIngredientDto(ingredient);
  }

  public ResponseIngredientDto updateIngredient(Long id, RequestIngredientDto ingredientDto) {
    Ingredient existingIngredient = ingredientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Ingredient", id));
    
    existingIngredient.setIn_name(ingredientDto.getIn_name());
    Ingredient updatedIngredient = ingredientRepository.save(existingIngredient);
    return ResponseIngredientDto.toResponseIngredientDto(updatedIngredient);
  }

  public void deleteIngredient(Long id) {
    if (!ingredientRepository.existsById(id)) {
      throw new ResourceNotFoundException("Ingredient", id);
    }
    ingredientRepository.deleteById(id);
  }
}
