package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;

public class EnemySpell extends GameObject{
    public float dx;
    public float dy;

    public EnemySpell() {
        renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/cyan.png"));
    }

    public void run() {
        if (this.position.x >= 384 || this.position.x <=-1 || this.position.y <= 0 || this.position.y >= 600) {
            remove.add(this);
        } else {
            this.position.addUp(dx, dy);
        }
    }
}
