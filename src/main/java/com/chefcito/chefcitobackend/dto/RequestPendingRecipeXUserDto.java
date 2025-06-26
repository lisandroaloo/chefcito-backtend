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
public class RequestPendingRecipeXUserDto {
  private Long rxu_us_id;
  private Long rxu_re_id;

  public static PendingRecipeXUser toPendingRecipeXUser(RequestPendingRecipeXUserDto requestPendingRecipeXUserDto) {
    return new PendingRecipeXUser(null, null, null);
  }
} 