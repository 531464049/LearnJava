package src;

import java.util.Scanner;

public class K01 {

    private Scanner iScanner;

    static public void test_01() {
        System.out.println("test_01");

        // K01 kk = new K01();
        // kk.iScanner = new Scanner(System.in);
        // kk.systemInPut();
        // kk.testLotteryDrawing();
        // kk.iScanner.close();
    }

    /**
     * 输入输出
     */
    private void systemInPut() {
        System.out.println("\n\nsystem in 输入：");
        String input = iScanner.nextLine();
        System.out.println("system out :" + input);
    }

    private void testLotteryDrawing() {
        System.out.println("\n\nplay lotteryDrawing: y or not ->");
        String yOrNot = iScanner.nextLine();
        if (yOrNot.equalsIgnoreCase("y")) {
            System.out.println("start play");

            System.out.println("how many numbers do you need to draw?");
            int drawNum = iScanner.nextInt();
            LotteryDrawing dLotteryDrawing = new LotteryDrawing();
            dLotteryDrawing.startPlay(drawNum);
        }
    }

}
