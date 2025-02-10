import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    public static void main(String[] args) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // 生成1到100之间的随机数
        int numberOfTries = 0;
        Scanner scanner = new Scanner(System.in);
        int guess;
        boolean win = false;

        System.out.println("欢迎来到猜数字游戏！");
        System.out.println("我已经想好了一个1到100之间的数字，请猜猜看是多少。");

        while (!win) {
            System.out.print("请输入你的猜测: ");
            guess = scanner.nextInt();
            numberOfTries++;

            if (guess == numberToGuess) {
                win = true;
            } else if (guess < numberToGuess) {
                System.out.println("太小了，再试一次！");
            } else if (guess > numberToGuess) {
                System.out.println("太大了，再试一次！");
            }
        }

        System.out.println("恭喜你，猜对了！");
        System.out.println("你一共猜了 " + numberOfTries + " 次。");
    }
}
