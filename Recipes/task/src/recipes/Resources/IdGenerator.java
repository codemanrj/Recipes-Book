package recipes.Resources;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {

    private static final Random random = new Random();

    public static Integer generate(int n) {
        return random.nextInt(n);
    }

}
