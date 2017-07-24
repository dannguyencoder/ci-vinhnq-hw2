package game.players;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 7/11/2017.
 */
public class PLayerSpell extends GameObject {
    public int x;
    public int y;
    public BufferedImage image;

    public PLayerSpell()
    {
        renderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/0.png"));
    }

    public void run()
    {
        this.position.addUp(0,-20);
    }

//    public void render(Graphics2D g2d)
//    {
//        double rotationRequired = Math.toRadians(45);
//        double locationX = image.getWidth();
//        double locationY = image.getHeight();
//        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX,locationY);
//        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//
//        g2d.drawImage(op.filter(image, null), x - 8, y - 8, null);
//    }

}
