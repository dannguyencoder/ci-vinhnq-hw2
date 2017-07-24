package game;

import game.bases.Constraints;
import game.bases.GameObject;
import game.enemies.EnemySpawner;
import game.enemies.EnemySpell;
import game.inputs.InputManager;
import game.players.Player;
import game.players.PLayerSpell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Vinh on 09/07/2017.
 */
public class GameWindow extends JFrame {

    private BufferedImage spells;
    private int spellsX;
    private int spellsY;

    boolean rightPressed;
    boolean leftPressed;
    boolean upPressed;
    boolean downPressed;
    boolean xPressed;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    InputManager inputManager = new InputManager();

    long timeNow;

    Player player = new Player();
    ArrayList <EnemySpawner> enemies = new ArrayList<>();
    ArrayList <PLayerSpell> playerSpells = new ArrayList<>();
    ArrayList <EnemySpell> enemySpells = new ArrayList<>();

//    Background background;

    public GameWindow() {



        setupWindow();

        addBackground();
        addPlayer();
        addEnemySpawner();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
        this.setVisible(true);
    }

    private void addBackground() {
        Background background = new Background();
        background.position.set(background.renderer.image.getWidth() / 2, this.getHeight());
        GameObject.add(background);
    }

    private void addEnemySpawner() {
        ArrayList<EnemySpawner> enemySpells = new ArrayList<>();
        GameObject.add(new EnemySpawner());
    }

    private void addPlayer() {
        Player player = new Player();
        player.setConstraints(new Constraints(20, this.getHeight(), 0, 369));
        player.setInputManager(inputManager);
        player.position.set(369/2, this.getHeight() - 69);
        GameObject.add(player);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    long lastUpdateTime;
    public void loop() {
        while(true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > 17) {
                lastUpdateTime = currentTime;
                run();
                render();
//                addEnemy();
            }
        }
    }

    public void run() {
//        if (backgroundY < 0) {
//            backgroundY++;
//        }

        GameObject.runAll();
    }

    public void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0 ,this.getWidth(), this.getHeight());

//        backBufferGraphics2D.drawImage(background, 0, backgroundY, null);

        GameObject.renderAll(backBufferGraphics2D);

        Graphics2D g2d = (Graphics2D)this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void setupWindow() {
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Tohou - Remade by Vinh");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //System.out.println("Closing");
                System.exit(0);
                //super.windowClosing(e);
            }
        });
    }

}
