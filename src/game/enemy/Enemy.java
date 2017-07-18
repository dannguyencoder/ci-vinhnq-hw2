package game.enemy;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Vinh on 17/07/2017.
 */
public class Enemy {
    public Vector2D position;
    public ImageRenderer imageRenderer;
    public static long oldTime;

    FrameCounter cooldownCounter;
    boolean spellDiasabled;

    public Enemy() {
        this.position = new Vector2D();
        this.cooldownCounter = new FrameCounter(40);
        this.imageRenderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/0.png"));
    }

    public void move() {
            this.position.addUp(0, (float) 1.5);
    }

    public void castSpell(ArrayList<EnemySpell> enemySpells) {
        //cast spell
        if (!spellDiasabled) {
            for (int i = -1; i<=1 ; i++) {
                EnemySpell enemySpell = new EnemySpell();
                enemySpell.position.set(this.position.add( 0 , 20));
                enemySpell.dx = (float) i*0.5f;
                enemySpell.dy = (float) 3;
                enemySpells.add(enemySpell);
                spellDiasabled = true;
            }
        }
    }

    public void coolDownSpell() {
        if (spellDiasabled) {
            boolean status = cooldownCounter.run();
            if (status) {
                spellDiasabled = false;
                cooldownCounter.reset();
            }
        }
    }

    public void render(Graphics2D g2d) {
        imageRenderer.render(g2d, this.position);
    }
}
