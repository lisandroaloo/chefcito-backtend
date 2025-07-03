package com.chefcito.chefcitobackend.service;

import com.chefcito.chefcitobackend.dto.RequestPendingRecipeXUserDto;
import com.chefcito.chefcitobackend.dto.ResponsePendingRecipeXUserDto;
import com.chefcito.chefcitobackend.exception.ResourceNotFoundException;
import com.chefcito.chefcitobackend.model.PendingRecipeXUser;
import com.chefcito.chefcitobackend.model.Recipe;
import com.chefcito.chefcitobackend.model.User;
import com.chefcito.chefcitobackend.repository.IPendingRecipeXUserRepository;
import com.chefcito.chefcitobackend.repository.IRecipeRepository;
import com.chefcito.chefcitobackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PendingRecipeXUserService {

  @Autowired
  private IPendingRecipeXUserRepository pendingRecipeXUserRepository;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IRecipeRepository recipeRepository;

  public ResponsePendingRecipeXUserDto addPendingRecipeXUser(RequestPendingRecipeXUserDto pendingRecipeXUserDto) {
    PendingRecipeXUser parsedPendingRecipeXUser = RequestPendingRecipeXUserDto
        .toPendingRecipeXUser(pendingRecipeXUserDto);

    if (pendingRecipeXUserDto.getRxu_us_id() != null) {
      User user = userRepository.findById(pendingRecipeXUserDto.getRxu_us_id())
          .orElseThrow(() -> new ResourceNotFoundException("User", pendingRecipeXUserDto.getRxu_us_id()));
      parsedPendingRecipeXUser.setUser(user);
    }

    if (pendingRecipeXUserDto.getRxu_re_id() != null) {
      Recipe recipe = recipeRepository.findById(pendingRecipeXUserDto.getRxu_re_id())
          .orElseThrow(() -> new ResourceNotFoundException("Recipe", pendingRecipeXUserDto.getRxu_re_id()));
      parsedPendingRecipeXUser.setRecipe(recipe);
    }

    PendingRecipeXUser responsePendingRecipeXUser = pendingRecipeXUserRepository.save(parsedPendingRecipeXUser);
    return ResponsePendingRecipeXUserDto.toResponsePendingRecipeXUserDto(responsePendingRecipeXUser);
  }

  public List<ResponsePendingRecipeXUserDto> getAllPendingRecipeXUsers() {
    List<PendingRecipeXUser> pendingRecipeXUsers = pendingRecipeXUserRepository.findAll();
    return pendingRecipeXUsers.stream()
        .map(ResponsePendingRecipeXUserDto::toResponsePendingRecipeXUserDto)
        .collect(Collectors.toList());
  }

  public ResponsePendingRecipeXUserDto getPendingRecipeXUserById(Long id) {
    PendingRecipeXUser pendingRecipeXUser = pendingRecipeXUserRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("PendingRecipeXUser", id));
    return ResponsePendingRecipeXUserDto.toResponsePendingRecipeXUserDto(pendingRecipeXUser);
  }

  public ResponsePendingRecipeXUserDto getPendingRecipeXUserByUserAndRecipeId(Long id, Long re_id) {
    PendingRecipeXUser pendingRecipeXUser = pendingRecipeXUserRepository.findByUserIdAndRecipeId(id, re_id);
    if (pendingRecipeXUser == null) {
      return null;
    }

    return ResponsePendingRecipeXUserDto.toResponsePendingRecipeXUserDto(pendingRecipeXUser);
  }

  public List<ResponsePendingRecipeXUserDto> getPendingRecipeXUserByUserId(Long id) {
    List<PendingRecipeXUser> pendingRecipeXUser = pendingRecipeXUserRepository.findByUserId(id);

    return pendingRecipeXUser.stream()
        .map(ResponsePendingRecipeXUserDto::toResponsePendingRecipeXUserDto)
        .collect(Collectors.toList());
  }

  public ResponsePendingRecipeXUserDto updatePendingRecipeXUser(Long id,
      RequestPendingRecipeXUserDto pendingRecipeXUserDto) {
    PendingRecipeXUser existingPendingRecipeXUser = pendingRecipeXUserRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("PendingRecipeXUser", id));

    if (pendingRecipeXUserDto.getRxu_us_id() != null) {
      User user = userRepository.findById(pendingRecipeXUserDto.getRxu_us_id())
          .orElseThrow(() -> new ResourceNotFoundException("User", pendingRecipeXUserDto.getRxu_us_id()));
      existingPendingRecipeXUser.setUser(user);
    }

    if (pendingRecipeXUserDto.getRxu_re_id() != null) {
      Recipe recipe = recipeRepository.findById(pendingRecipeXUserDto.getRxu_re_id())
          .orElseThrow(() -> new ResourceNotFoundException("Recipe", pendingRecipeXUserDto.getRxu_re_id()));
      existingPendingRecipeXUser.setRecipe(recipe);
    }

    PendingRecipeXUser updatedPendingRecipeXUser = pendingRecipeXUserRepository.save(existingPendingRecipeXUser);
    return ResponsePendingRecipeXUserDto.toResponsePendingRecipeXUserDto(updatedPendingRecipeXUser);
  }

  public void deletePendingRecipeXUser(Long id) {
    if (!pendingRecipeXUserRepository.existsById(id)) {
      throw new ResourceNotFoundException("PendingRecipeXUser", id);
    }
    pendingRecipeXUserRepository.deleteById(id);
  }
}
