package game.bases;

import java.awt.*;
import java.util.Vector;

/**
 * Created by NHEM on 18/07/2017.
 */
public class GameObject {

    public Vector2D position;
    public ImageRenderer renderer;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();
    public static Vector<GameObject> remove = new Vector<>();

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public static void renderAll(Graphics2D g2d) {
        for(GameObject gameObject : gameObjects) {
            gameObject.render(g2d);
        }
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            gameObject.run();
        }
        gameObjects.addAll(newGameObjects);
        gameObjects.removeAll(remove);
        newGameObjects.clear();
        remove.clear();
    }

    public GameObject() {
        this.position = new Vector2D();
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run () {

    }


}
