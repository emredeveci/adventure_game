import java.util.Random;

public class Snake extends Enemy {

    static Random attack = new Random();

    public Snake() {
        super(4, "Snake", attack.nextInt(3) + 4, 12, 0);
    }
}
