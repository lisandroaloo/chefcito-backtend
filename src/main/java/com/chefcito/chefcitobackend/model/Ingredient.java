package com.chefcito.chefcitobackend.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long in_id;

  private String in_name;

  @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<IngredientXRecipe> recipes;
}
