package src.book3.p02;

import src.book3.Print;

public class Music {

}

/**
 * 抽象乐器类
 */
abstract class Instrument {

    private int i;

    public abstract void play();

    public String what() {
        return "Instrument";
    }

    public abstract void adjust();
}

class Wind extends Instrument {
    @Override
    public void play() {
        Print.println("wind play");
    }

    @Override
    public void adjust() {
        // TODO Auto-generated method stub
    }

    @Override
    public String what() {
        return this.getClass().getSimpleName();
    }
}