package src.firstPage;

import java.util.Arrays;

public class LotteryDrawing {
    private static final int KNUM = 100;
    private int[] numbers = new int[KNUM];

    public void startPlay(int num) {
        setupNumbers();
        int[] results = new int[num];
        for (int i = 0; i < results.length; i++) {
            int r = (int) (Math.random() * KNUM);
            results[i] = r;
        }
        Arrays.sort(results);
        for (int i = 0; i < results.length; i++) {
            System.out.println("result of " + i + "is " + results[i]);
        }
    }

    private void setupNumbers() {
        // 初始化数组
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
    }
}
