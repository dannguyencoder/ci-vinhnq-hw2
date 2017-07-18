package game.player;

import game.Utils;
import game.bases.Contraints;
import game.bases.FrameCounter;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Vinh on 11/07/2017.
 */
public class Player {

    //Properties: Thuoc tinh
    public Vector2D position;
    public ImageRenderer imageRenderer;
    Contraints contraints;

    FrameCounter coolDownCounter;
    boolean spellDisabled;

    public Player(){
        this.position = new Vector2D();
        this.coolDownCounter = new FrameCounter(17); // 17 = 300 miliseconds to cooldown
        this.imageRenderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
    }

    //Methods: Phuong thuc
    public void move(float dx, float dy) {
        this.position.addUp(dx, dy);
        contraints.make(this.position);
    }

    public void castSpell(ArrayList<PlayerSpell> playerSpells) {
        //cast spell
        if (!spellDisabled) {
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0, -20));
            playerSpells.add(playerSpell);
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

    //setter
    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    public void render(Graphics2D g2d) {
        imageRenderer.render(g2d, this.position);
    }
}
