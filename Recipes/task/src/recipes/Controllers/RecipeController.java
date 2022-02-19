package recipes.Controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.businessLayer.*;
import recipes.Resources.IdGenerator;
import recipes.businessLayer.RecipeService;

import javax.persistence.Column;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public RecipeResponseModel getRecipe(@PathVariable Long id) {

        Recipe recipe = recipeService.findRecipeById(id);
        //Recipe recipe = recipes.get(id);
        if(recipe==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) ;
        }
        else
            return new RecipeResponseModel(recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
    }

    @PostMapping("/api/recipe/new")
    public IdResponse postRecipe(@RequestBody Recipe req) {
        Long id = (long) IdGenerator.generate(1000);

        try {
            Recipe recipeCreate = recipeService.save(new Recipe(id,
                    req.getName(),
                    req.getDescription(),
                    req.getIngredients(),
                    req.getDirections()));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //recipes.put(id, req);
        return new IdResponse(id);
    }

    @DeleteMapping("/api/recipe/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.delete(id);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Data
    @AllArgsConstructor
    class IdResponse {
        Long id;
    }

    @Data
    @AllArgsConstructor
    class RecipeResponseModel {
        private String name;
        private String description;
        private String[] ingredients;
        private String[] directions;
    }

}