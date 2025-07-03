package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.RequestFiltersDto;
import com.chefcito.chefcitobackend.dto.RequestRecipeByUserDto;
import com.chefcito.chefcitobackend.dto.RequestRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseRecipeDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.*;
import com.chefcito.chefcitobackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

  @Autowired
  private IRecipeRepository recipeRepository;

  @Autowired
  private IStepXRecipe stepXRecipeRepository;

  @Autowired
  private IIngredientXRecipeRepository ingredientXRecipeRepository;

  @Autowired
  private IUserRepository userRepository;

  public ResponseRecipeDto addRecipe(RequestRecipeDto recipeDto) {
    Recipe parsedRecipe = RequestRecipeDto.toRecipe(recipeDto);
    
    if (recipeDto.getRe_creator_us_id() != null) {
      User user = userRepository.findById(recipeDto.getRe_creator_us_id())
          .orElseThrow(() -> new ResourceNotFoundException("User", recipeDto.getRe_creator_us_id()));
      parsedRecipe.setUser(user);
    }
    Recipe responseRecipe = recipeRepository.save(parsedRecipe);

    //Guardar pasos
    responseRecipe.setSteps(recipeDto.getSteps().stream().map(s -> stepXRecipeRepository.save(new StepXRecipe(null, s, responseRecipe))).toList());

    //Guardar ingredients
    responseRecipe.setIngredients(recipeDto.getIngredients().stream().map(i -> ingredientXRecipeRepository.save(new IngredientXRecipe(null, responseRecipe, i))).toList());

    return ResponseRecipeDto.toResponseRecipeDto(responseRecipe);
  }

  public List<ResponseRecipeDto> getAllRecipes() {
    List<Recipe> recipes = recipeRepository.findAll();
    return recipes.stream()
        .map(ResponseRecipeDto::toResponseRecipeDto)
        .collect(Collectors.toList());
  }

  public ResponseRecipeDto getRecipeById(Long id) {
    Recipe recipe = recipeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Recipe", id));
    return ResponseRecipeDto.toResponseRecipeDto(recipe);
  }
  

  //TO DOOOOOO
  public List<ResponseRecipeDto> getRecipeByUserId(RequestRecipeByUserDto userdto) {
    User user = userRepository.findByUsernameee(userdto.getUs_name());

    List<Recipe> recipes = recipeRepository.findAllByUserId(user.getUs_id());
  
   return recipes.stream()
        .map(ResponseRecipeDto::toResponseRecipeDto)
        .collect(Collectors.toList()); 
       }

  public ResponseRecipeDto updateRecipe(Long id, RequestRecipeDto recipeDto) {
    Recipe existingRecipe = recipeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Recipe", id));



    existingRecipe.setRe_picture(recipeDto.getRe_picture());
    existingRecipe.setRe_title(recipeDto.getRe_title());
    existingRecipe.setRe_suitable_for_vegan(recipeDto.getRe_suitable_for_vegan());
    existingRecipe.setRe_suitable_for_vegetarian(recipeDto.getRe_suitable_for_vegetarian());
    existingRecipe.setRe_suitable_for_celiac(recipeDto.getRe_suitable_for_celiac());
    existingRecipe.setRe_suitable_for_lactose_intolerant(recipeDto.getRe_suitable_for_lactose_intolerant());
    
    if (recipeDto.getRe_creator_us_id() != null) {
      User user = userRepository.findById(recipeDto.getRe_creator_us_id())
          .orElseThrow(() -> new ResourceNotFoundException("User", recipeDto.getRe_creator_us_id()));
      existingRecipe.setUser(user);
    }

    System.out.println(existingRecipe.getRe_title());
    Recipe responseRecipe = recipeRepository.save(existingRecipe);

    //Guardar pasos
    stepXRecipeRepository.deleteAllByRecipe(responseRecipe);
    responseRecipe.getSteps().clear();
    responseRecipe.getSteps().addAll(recipeDto.getSteps().stream().map(s -> stepXRecipeRepository.save(new StepXRecipe(null, s, responseRecipe))).toList());

    //Guardar ingredients
    ingredientXRecipeRepository.deleteAllByRecipe(responseRecipe);
    responseRecipe.getIngredients().clear();
    responseRecipe.getIngredients().addAll(recipeDto.getIngredients().stream().map(i -> ingredientXRecipeRepository.save(new IngredientXRecipe(null, responseRecipe, i))).toList());

    return ResponseRecipeDto.toResponseRecipeDto(responseRecipe);
  }

  public void deleteRecipe(Long id) {
    if (!recipeRepository.existsById(id)) {
      throw new ResourceNotFoundException("Recipe", id);
    }
    recipeRepository.deleteById(id);
  }
}
