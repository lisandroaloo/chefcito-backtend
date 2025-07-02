package com.chefcito.chefcitobackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "step_x_recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepXRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long sxr_id;

    private String sxr_description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sxr_re_id")
    private Recipe recipe;
}
