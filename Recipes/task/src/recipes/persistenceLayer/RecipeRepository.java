package recipes.persistenceLayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.businessLayer.Recipe;
//import repository, Crud repository and the business layer


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Recipe findRecipeById(Long id);

}
