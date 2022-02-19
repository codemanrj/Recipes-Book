package recipes.businessLayer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import recipes.persistenceLayer.RecipeRepository;

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
    //store
    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    //delete by id
    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }


}
