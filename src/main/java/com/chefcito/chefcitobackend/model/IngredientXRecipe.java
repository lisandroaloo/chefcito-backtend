package com.chefcito.chefcitobackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "ingredient_x_recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientXRecipe {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long ixr_id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ixr_in_id")
  private Ingredient ingredient;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ixr_re_id")
  private Recipe recipe;

  private Integer quantity;
}