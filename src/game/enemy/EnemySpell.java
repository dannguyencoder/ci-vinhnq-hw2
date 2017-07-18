package game.enemy;

import game.Utils;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by NHEM on 17/07/2017.
 */
public class EnemySpell {
    public Vector2D position;
    public ImageRenderer imageRenderer;
    public float dx = 0;
    public float dy = 0;

    public EnemySpell() {
            this.position = new Vector2D();
            this.imageRenderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/blue.png"));
    }

    public void move() {
        this.position.addUp( dx, dy);

    }

    public void render(Graphics2D g2d) {
        imageRenderer.render(g2d, this.position);
    }
}
