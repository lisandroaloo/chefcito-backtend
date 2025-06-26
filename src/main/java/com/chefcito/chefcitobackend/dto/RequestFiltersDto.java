package com.chefcito.chefcitobackend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestFiltersDto {

    private List<String> ingredients;
    private String name;
    private String type;
    private String without;
    
}
