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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public RecipeResponseModel getRecipe(@PathVariable Long id) {

        Recipe recipe = recipeService.findRecipeById(id);
        //Recipe recipe = recipes.get(id);
        if(recipe==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else
            return new RecipeResponseModel(recipe.getName(),
                    recipe.getDescription(),
                    recipe.getCategory(),
                    recipe.getIngredients(),
                    recipe.getDirections(),
                    recipe.getDate());
    }

    @PostMapping("/api/recipe/new")
    public IdResponse postRecipe(@RequestBody Recipe req) {
        Long id = (long) IdGenerator.generate(1000);

        try {
            Recipe recipeCreate = recipeService.save(new Recipe(id,
                    req.getName(),
                    req.getDescription(),
                    req.getCategory(),
                    req.getIngredients(),
                    req.getDirections(),
                    LocalDateTime.now()));
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

    @PutMapping("/api/recipe/{id}")
    public void updateRecipe(@PathVariable Long id, @RequestBody Recipe req) {
        Recipe recipe;
        try {
            recipe = recipeService.findRecipeById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(recipe==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        try {
            //overwrite
            recipe.setDate(LocalDateTime.now());
            recipe.setName(req.getName());
            recipe.setDescription(req.getDescription());
            recipe.setCategory(req.getCategory());
            recipe.setIngredients(req.getIngredients());
            recipe.setDirections(req.getDirections());

            recipeService.save(recipe);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> searchRecipe(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if((category==null&&name==null) || (category!=null&&name!=null)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(category!=null)
            return recipeService.findRecipeByCategory(category);
        else
            return recipeService.findRecipeByName(name);
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
        private String category;
        private String[] ingredients;
        private String[] directions;
        private LocalDateTime date;
    }

}