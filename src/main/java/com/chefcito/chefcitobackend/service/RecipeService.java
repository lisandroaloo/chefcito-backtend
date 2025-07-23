package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.RequestFiltersDto;
import com.chefcito.chefcitobackend.dto.RequestRecipeByUserDto;
import com.chefcito.chefcitobackend.dto.RequestRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseRecipeDto;
import com.chefcito.chefcitobackend.dto.ResponseReviewDto;
import com.chefcito.chefcitobackend.dto.ReviewRecipeDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.*;
import com.chefcito.chefcitobackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

  @Autowired
  private IRecipeRepository recipeRepository;

  @Autowired
  private IStepXRecipe stepXRecipeRepository;

  @Autowired
  private IReviewXUserXRecipeRepository reviewXUserXRecipeRepository;

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

    // Guardar pasos
    responseRecipe.setSteps(recipeDto.getSteps().stream()
        .map(s -> stepXRecipeRepository.save(new StepXRecipe(null, s, responseRecipe))).toList());

    // Guardar la receta primero (responseRecipe)

    List<IngredientXRecipe> ingredientXRecipes = new ArrayList<>();

    for (RequestRecipeDto.ingredientsClass iDto : recipeDto.getIngredients()) {
      IngredientXRecipe ixr = IngredientXRecipe.builder()
          .recipe(responseRecipe)
          .name(iDto.getName())
          .quantity(iDto.getQuantity())
          .unit(iDto.getUnit())
          .build();

      ingredientXRecipes.add(ingredientXRecipeRepository.save(ixr));
    }

    responseRecipe.setIngredients(ingredientXRecipes);

    return ResponseRecipeDto.toResponseRecipeDto(responseRecipe);
  }

  public List<ResponseRecipeDto> getAllRecipes() {
    List<Recipe> recipes = recipeRepository.findAll();
    return recipes.stream()
        .map(ResponseRecipeDto::toResponseRecipeDto)
        .collect(Collectors.toList());
  }

  public List<ResponseReviewDto> getAllReviews(Long id) {
    List<ReviewXUserXRecipe> reviews = reviewXUserXRecipeRepository.findAllByReviewId(id);
    return reviews.stream()
        .map(ResponseReviewDto::toResponseDto)
        .collect(Collectors.toList());
  }

  public ResponseEntity<Boolean> checkReview(Long userId, Long recipeId) {
    ReviewXUserXRecipe reviews = reviewXUserXRecipeRepository.checkReview(recipeId, userId);

    if (reviews != null) {
      return ResponseEntity.ok().body(true); // ya hizo review
    } else {
      return ResponseEntity.ok().body(false); // no hizo review aún
    }

  }

  public ResponseRecipeDto getRecipeById(Long id) {
    Recipe recipe = recipeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Recipe", id));
    return ResponseRecipeDto.toResponseRecipeDto(recipe);
  }

  public ResponseReviewDto reviewRecipe(ReviewRecipeDto review) {

    ReviewXUserXRecipe reviewRecipe = new ReviewXUserXRecipe();
    reviewRecipe.setStars(review.getStars());
    reviewRecipe.setReview(review.getReview());
    Optional<User> user = userRepository.findById(review.getUs_id());
    reviewRecipe.setUser(user.get());
    Optional<Recipe> recipe = recipeRepository.findById(review.getRe_id());
    reviewRecipe.setRecipe(recipe.get());

    ReviewXUserXRecipe reviewXUserXRecipe = reviewXUserXRecipeRepository.save(reviewRecipe);

    return ResponseReviewDto.toResponseDto(reviewXUserXRecipe);
  }

  // TO DOOOOOO
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

    // Guardar pasos
    stepXRecipeRepository.deleteAllByRecipe(responseRecipe);
    responseRecipe.getSteps().clear();
    responseRecipe.getSteps().addAll(recipeDto.getSteps().stream()
        .map(s -> stepXRecipeRepository.save(new StepXRecipe(null, s, responseRecipe))).toList());

    // Guardar ingredientes
    ingredientXRecipeRepository.deleteAllByRecipe(responseRecipe);
    if (responseRecipe.getIngredients() != null) {
      responseRecipe.getIngredients().clear();
    }
    responseRecipe.getIngredients().addAll(
        recipeDto.getIngredients().stream()
            .map(i -> {
              IngredientXRecipe ixr = new IngredientXRecipe();
              ixr.setRecipe(responseRecipe);
              ixr.setName(i.getName());
              ixr.setQuantity(i.getQuantity());
              ixr.setUnit(i.getUnit());
              return ingredientXRecipeRepository.save(ixr);
            })
            .toList());

    return ResponseRecipeDto.toResponseRecipeDto(responseRecipe);
  }

  public void deleteRecipe(Long id) {
    if (!recipeRepository.existsById(id)) {
      throw new ResourceNotFoundException("Recipe", id);
    }
    recipeRepository.deleteById(id);
  }
}
