package com.chefcito.chefcitobackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long re_id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "re_creator_us_id")
  private User user;

  private String re_picture;
  private String re_title;
  private Boolean re_suitable_for_vegan;
  private Boolean re_suitable_for_vegetarian;
  private Boolean re_suitable_for_celiac;
  private Boolean re_suitable_for_lactose_intolerant;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<IngredientXRecipe> ingredients;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<StepXRecipe> steps;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<PendingRecipeXUser> pendingUsers;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<ReviewXUserXRecipe> reviews;
}
