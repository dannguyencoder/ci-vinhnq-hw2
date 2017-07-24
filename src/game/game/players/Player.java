package game.players;

import game.Utils;
import game.bases.*;
import game.inputs.InputManager;
import game.players.PLayerSpell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Administrator on 7/11/2017.
 */
public class Player extends GameObject {

    //Properties: Thuoc tinh (khai bao truc tiep ra ngoai)
    Constraints constraints;

    InputManager inputManager;

    FrameCounter coolDownCounter;
    boolean spellDisabled;
    Vector2D velocity;

    public static float px;
    public static float py;
    ArrayList<PLayerSpell>pLayerSpells = new ArrayList<>();

    public Player()
    {
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(17);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
    }

    @Override
    public void run()
    {
        move();
        castSpell(pLayerSpells);
        coolDownSpell();
    }

    private void castSpell(ArrayList<PLayerSpell> pLayerSpells) {
        if (inputManager.xPressed)
        {
            if (!spellDisabled)
            {
                PLayerSpell playerSpell = new PLayerSpell();
                playerSpell.position.set(this.position.add(0, -20));
                GameObject.add(playerSpell);

                spellDisabled = true;
            }
        }
    }

    //Methods: Phuong thuc
    public void move()
    {
        this.velocity.set(0,0);

        if (inputManager.leftPressed)
            this.velocity.x -= 10;
        if (inputManager.rightPressed)
            this.velocity.x += 10;
        if (inputManager.upPressed)
            this.velocity.y -= 10;
        if (inputManager.downPressed)
            this.velocity.y += 10;


        px = this.position.x;
        py = this.position.y;

        this.position.addUp(velocity);
        this.constraints.make(this.position);
    }

    public void coolDownSpell()
    {
        if (spellDisabled)
        {
            boolean status = coolDownCounter.run();
            if (status)
            {
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

    public void setConstraints(Constraints constraints)
    {
        this.constraints = constraints;
    }

    public void setInputManager(InputManager inputManager)
    {
        this.inputManager = inputManager;
    }
}
