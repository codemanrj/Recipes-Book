package recipes.businessLayer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import recipes.persistenceLayer.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository repo) {
        this.recipeRepository = repo;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findRecipeByCategory(String category) {
        return recipeRepository.findRecipeByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findRecipeByName(String name) {
        return recipeRepository.findRecipeByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    //store
    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    //delete by id
    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }


}
