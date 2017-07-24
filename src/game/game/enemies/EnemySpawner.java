package game.enemies;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.players.Player;

import java.util.ArrayList;

/**
 * Created by NHEM on 18/07/2017.
 */
public class EnemySpawner extends GameObject {
    FrameCounter coolDownCounter;
    Vector2D velocity;
    boolean spellDisabled;
    float alpha;

    ArrayList<EnemySpell> enemySpells = new ArrayList<>();
    public EnemySpawner() {
        this.velocity = new Vector2D();
        this.position.set(200, -30);
        this.coolDownCounter = new FrameCounter(17);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/0.png"));
    }

    @Override
    public void run() {
        this.move();
        this.castSpell(enemySpells);
        this.coolDown();
    }

    public void move() {
        if (this.position.y > 600) {
            this.position.set(200, -30);
        }
        this.position.addUp(0, 3);
    }

    private void castSpell() {
        EnemySpell enemySpells = new EnemySpell();
        enemySpells.position.set(this.position.add(0, 20));
        GameObject.add(enemySpells);
    }

    public void castSpell(ArrayList<EnemySpell> enemySpells) {
        //cast spell
        if (this.position.y > 600) {
            spellDisabled = true;
        } else if (!spellDisabled) {
            alpha = (float) Math.sqrt((Player.px - this.position.x) * (Player.px - this.position.x) + (Player.py - this.position.y) * (Player.py - this.position.y));
            EnemySpell enemySpell = new EnemySpell();
            enemySpell.position.set(this.position.add(0,15));
            if (Player.px == this.position.x) {
                enemySpell.dx = 0;
                enemySpell.dy = 5;
            } else if (Player.py == this.position.y) {
                enemySpell.dx = 5;
                enemySpell.dy = 0;
            } else {
                enemySpell.dx = 5 * (Player.px - this.position.x) / alpha;
                enemySpell.dy = enemySpell.dx * (Player.py - this.position.y) / (Player.px - this.position.x);
            }

            enemySpell.add(enemySpell);

            spellDisabled = true;
        }
    }

    public void coolDown() {
        //cooldown
        if (spellDisabled){
            boolean status = coolDownCounter.run();
            if (status) {
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }
}
