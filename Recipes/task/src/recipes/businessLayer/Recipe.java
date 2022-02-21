package recipes.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


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

    @Column(name = "category")
    @NotNull
    @NotBlank
    private String category; //might need to turn to an enum later

    @Column(name = "ingredients")
    @NotNull
    @Size(min=1)
    private String[] ingredients;

    @Column(name = "directions")
    @NotNull
    @Size(min=1)
    private String[] directions;

    //@Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDateTime date;

    @JsonIgnore
    public long getId() {
        return id;
    }

}
