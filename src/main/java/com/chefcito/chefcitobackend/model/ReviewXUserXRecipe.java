package com.chefcito.chefcitobackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_x_user_x_recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewXUserXRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long rxuxr_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rxuxr_us_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rxuxr_re_id")
    private Recipe recipe;

    private Integer stars;

    private String review;
}
