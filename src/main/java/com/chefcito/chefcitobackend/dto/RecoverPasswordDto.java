package com.chefcito.chefcitobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecoverPasswordDto {

    private String email;
    private String code;
    private String password;
    
}
