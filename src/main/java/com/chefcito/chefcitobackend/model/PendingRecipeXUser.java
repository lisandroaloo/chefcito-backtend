package com.chefcito.chefcitobackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "pending_recipe_x_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingRecipeXUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long rxu_id;

  @ManyToOne
  @JoinColumn(name = "rxu_us_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "rxu_re_id")
  private Recipe recipe;
}