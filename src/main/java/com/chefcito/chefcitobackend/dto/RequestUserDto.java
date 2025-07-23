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
public class RequestUserDto {
  private String us_alias;
  private String us_email;
  private String us_password;
  private String us_password_salt;

  public static User toUser(RequestUserDto requestUserDto) {
    return new User(null, requestUserDto.getUs_alias(), requestUserDto.getUs_email(), 
        requestUserDto.getUs_password(), requestUserDto.getUs_password_salt(),null, null, null, null);
  }
} 