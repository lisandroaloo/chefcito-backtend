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
public class ResponseUserDto {
  private Long us_id;
  private String us_alias;
  private String us_email;
  private String us_password;
  private String us_password_salt;

  public static User toUser(ResponseUserDto responseUserDto) {
    return User.builder()
        .us_id(responseUserDto.getUs_id())
        .us_alias(responseUserDto.getUs_alias())
        .us_email(responseUserDto.getUs_email())
        .us_password(responseUserDto.getUs_password())
        .us_password_salt(responseUserDto.getUs_password_salt())
        .build();
  }

  public static ResponseUserDto toResponseUserDto(User user) {
    return ResponseUserDto.builder()
        .us_id(user.getUs_id())
        .us_alias(user.getUs_alias())
        .us_email(user.getUs_email())
        .us_password(user.getUs_password())
        .us_password_salt(user.getUs_password_salt())
        .build();
  }
} 