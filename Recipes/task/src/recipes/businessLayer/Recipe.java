package recipes.businessLayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recipe")
public class Recipe {

    @Id
    private long id;

    @Column(name="name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotNull
    @NotBlank
    private String description;

    @Column(name = "ingredients")
    @NotNull
    @Size(min=1)
    private String[] ingredients;

    @Column(name = "directions")
    @NotNull
    @Size(min=1)
    private String[] directions;

}
