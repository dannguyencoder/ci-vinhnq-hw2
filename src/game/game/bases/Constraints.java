package game.bases;

/**
 * Created by NHEM on 16/07/2017.
 */
public class Constraints {
    float top;
    float bottom;
    float left;
    float right;

    public Constraints(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public void make(Vector2D positon) {
        positon.x = Mathx.clamp(positon.x, left, right);
        positon.y = Mathx.clamp(positon.y, top, bottom);
    }

}
