package game.bases;

/**
 * Created by NHEM on 16/07/2017.
 */
public class FrameCounter {
    int count;
    int countMax;
    public FrameCounter(int countMax){
        this.countMax = countMax;
    }

    public void reset() {
        count = 0;
    }

    public boolean run() {
        if (count < countMax) {
            count++;
            return false;
        } else {
            return true;
        }
    }
}
