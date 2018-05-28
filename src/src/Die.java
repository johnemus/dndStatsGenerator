import java.util.Random;
public class Die {
    private int sides;

    public Die(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public int roll() {
        Random die = new Random();
        int roll = die.nextInt(sides) + 1;
        return roll;
    }
}
