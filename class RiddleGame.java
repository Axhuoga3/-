import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RiddleGame {

    // 谜语和答案的映射
    private static final Map<String, String> riddles = new HashMap<>();

    static {
        riddles.put("什么东西越洗越脏？", "水");
        riddles.put("什么东西不能吃？", "亏");
        riddles.put("什么东西越用越有钱？", "存钱罐");
        riddles.put("什么东西看不见摸不着但能感觉到？", "风");
        riddles.put("什么东西越分越多？", "知识");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 将谜语转换为数组以便随机选择
        String[] riddleArray = riddles.keySet().toArray(new String[0]);

        // 随机选择一个谜语
        String selectedRiddle = riddleArray[random.nextInt(riddleArray.length)];
        String correctAnswer = riddles.get(selectedRiddle);

        System.out.println("欢迎来到猜谜语游戏！");
        System.out.println("谜语是: " + selectedRiddle);
        System.out.print("请输入你的答案: ");

        String userAnswer = scanner.nextLine();

        if (userAnswer.equals(correctAnswer)) {
            System.out.println("恭喜你，答对了！");
        } else {
            System.out.println("很遗憾，答错了。正确答案是: " + correctAnswer);
        }

        scanner.close();
    }
}
