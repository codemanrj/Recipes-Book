package recipes.Controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.Models.*;
import recipes.Resources.IdGenerator;

import java.util.HashMap;
import java.util.Random;

@RestController
public class RecipeController {
    //hashmap to get recipes by name
    HashMap<Integer, Recipe> recipes = new HashMap();

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Integer id) {

        Recipe recipe = recipes.get(id);
        if(recipe==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) ;
        }
        else
            return recipe;
    }

    @PostMapping("/api/recipe/new")
    public IdResponse postRecipe(@RequestBody Recipe req) {
        int id = IdGenerator.generate(1000);
        recipes.put(id, req);
        return new IdResponse(id);
    }

    @Data
    @AllArgsConstructor
    class IdResponse {
        Integer id;
    }

}