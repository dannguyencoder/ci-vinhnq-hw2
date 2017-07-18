package game.player;

import game.Utils;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;

/**
 * Created by NHEM on 11/07/2017.
 */
public class PlayerSpell {
    public Vector2D position;
    public ImageRenderer imageRenderer;

    public PlayerSpell() {
        position = new Vector2D();
        imageRenderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/1.png"));
    }

    public void move() {
        this.position.addUp(0, -20);
    }

    public void render(Graphics2D g2d) {
        imageRenderer.render(g2d, this.position);
    }
}
