package game;

import java.awt.image.BufferedImage;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import javax.rmi.CORBA.Util;

/**
 * Created by Administrator on 7/22/2017.
 */
public class Background extends GameObject {
    public Vector2D velocity;

//    GameWindow gameWindow;

    public Background()
    {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
    }

    @Override
    public void run()
    {
        move();
    }

    private void move() {
        this.velocity.set(0, 0);
        if (this.position.y < this.renderer.image.getHeight() / 2)
        {
            this.velocity.y++;
        }
        this.position.addUp(velocity);
    }
}
