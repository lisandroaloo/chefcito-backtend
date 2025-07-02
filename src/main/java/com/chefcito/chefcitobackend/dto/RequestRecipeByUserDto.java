package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRecipeByUserDto {
  private String us_name;

} 