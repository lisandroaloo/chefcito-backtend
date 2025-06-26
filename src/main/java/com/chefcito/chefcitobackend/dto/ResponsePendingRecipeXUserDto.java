package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.PendingRecipeXUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsePendingRecipeXUserDto {
  private Long rxu_id;
  private Long rxu_us_id;
  private Long rxu_re_id;

  public static PendingRecipeXUser toPendingRecipeXUser(ResponsePendingRecipeXUserDto responsePendingRecipeXUserDto) {
    return PendingRecipeXUser.builder()
        .rxu_id(responsePendingRecipeXUserDto.getRxu_id())
        .build();
  }

  public static ResponsePendingRecipeXUserDto toResponsePendingRecipeXUserDto(PendingRecipeXUser pendingRecipeXUser) {
    return ResponsePendingRecipeXUserDto.builder()
        .rxu_id(pendingRecipeXUser.getRxu_id())
        .rxu_us_id(pendingRecipeXUser.getUser() != null ? pendingRecipeXUser.getUser().getUs_id() : null)
        .rxu_re_id(pendingRecipeXUser.getRecipe() != null ? pendingRecipeXUser.getRecipe().getRe_id() : null)
        .build();
  }
} 