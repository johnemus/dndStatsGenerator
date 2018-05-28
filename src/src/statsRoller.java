import java.util.Arrays;
import java.util.Scanner;
public class statsRoller {
    public static void main(String[] args) {
        boolean rerollOnes = false;
        int input = 0;
        Scanner s = new Scanner(System.in);
        String[] stats = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
        while (input >= 0) {
            System.out.println("Welcome!  How do you wish to generate your stats?");
            System.out.println("1) 3d6\n2) 4d6 highest 3\n3) Colvillian\n4) Enable/Disable Rerolling of Ones");
            System.out.println("input a negative number to quit\n");

            input = s.nextInt();

            if (input == 1) {
                System.out.println(Arrays.toString(stats));
                System.out.println(Arrays.toString(stats3d6(rerollOnes)));
            }else if (input == 2) {
                System.out.println(Arrays.toString(stats));
                System.out.println(Arrays.toString(stats4d6(rerollOnes)));
            }else if (input == 3) {
                System.out.println(Arrays.toString(stats));
                System.out.println(Arrays.toString(colvillian(rerollOnes)));
            }else if (input == 4) {
                if (rerollOnes) {
                    rerollOnes = false;
                    System.out.println("Rerolling ones is disabled.");
                }else {
                    rerollOnes = true;
                    System.out.println("Rerolling ones is enabled.");
                }
            }else if (input < 0) {
                System.out.println("Thank you for generating your stats with us!");
                break;
            }else {
                System.out.println("Please input a valid number;");
            }
        }
    }

    public static int[] stats3d6(boolean rOnes) {
        int[] matrix = new int[6];
        Die[] Dice = {new Die(6),new Die(6),new Die(6)};
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = 0;
            for (Die d6 : Dice) {
                int roll = d6.roll();
                if (rOnes) {
                    while (roll == 1) {
                        roll = d6.roll();
                    }
                }
                matrix[i] += roll;
            }
        }
        return matrix;
    }

    public static int[] stats4d6(boolean rOnes) {
        int[] matrix = new int[6];
        Arrays.fill(matrix, 0);
        int[] rolls = new int[4];
        Die die = new Die(6);

        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(rolls, 0);
            for (int j = 0; j < rolls.length; j++) {
                rolls[j] = die.roll();
                if (rOnes) {
                    while (rolls[j] == 1) {
                        rolls[j] = die.roll();
                    }
                }
            }
            Arrays.sort(rolls);
            matrix[i] = rolls[3] + rolls[2] + rolls[1];
        }
        return matrix;
    }

    public static int[] colvillian(boolean rOnes) {
        int[] matrix = stats4d6(rOnes);
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] >= 15) {
                count++;
            }
        }
        if (count >= 2) {
            return matrix;
        }else {
            return colvillian(rOnes);
        }
    }
}
