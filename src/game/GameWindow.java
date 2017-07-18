package game;

import game.bases.Contraints;
import game.bases.ImageRenderer;
import game.enemy.Enemy;
import game.enemy.EnemySpell;
import game.player.Player;
import game.player.PlayerSpell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Vinh on 09/07/2017.
 */
public class GameWindow extends JFrame {

    private BufferedImage background;

    private BufferedImage spells;
    private int spellsX;
    private int spellsY;

    private int backgroundY ;

    boolean rightPressed;
    boolean leftPressed;
    boolean upPressed;
    boolean downPressed;
    boolean xPressed;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    long timeNow;

    Player player = new Player();
    ArrayList <Enemy> enemies = new ArrayList<>();
    ArrayList <PlayerSpell> playerSpells = new ArrayList<>();
    ArrayList <EnemySpell> enemySpells = new ArrayList<>();

    public GameWindow() {
        setupWindow();
        loadImage();
        Contraints contraints = new Contraints(20, this.getHeight(), 0, background.getWidth());
        player.setContraints(contraints);
        player.position.set(background.getWidth() / 2, this.getHeight() - 50);

        backgroundY = this.getHeight() - background.getHeight();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
        this.setVisible(true);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = true;
                        break;
                    case KeyEvent.VK_X:
                        xPressed = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = false;
                        break;
                    case KeyEvent.VK_X:
                        xPressed = false;
                        break;
                    default:
                        break;
                }
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
            }
        }
    }

    public void run() {
        if (backgroundY < 0) {
            backgroundY++;
        }

        int dx = 0;
        int dy = 0;

        if (rightPressed) {
            dx += 5;
        }
        if (leftPressed) {
            dx += -5;
        }
        if (upPressed) {
            dy += -5;
        }
        if (downPressed) {
            dy += 5;
        }
        if (xPressed) {
            player.castSpell(playerSpells);
        }
        player.move(dx, dy);
        player.coolDown();
        for (PlayerSpell playerSpell : playerSpells) {
            playerSpell.move();
        }

        timeNow = System.currentTimeMillis();
        while (timeNow - Enemy.oldTime >= 3000) {
            for (int i = 0; i < 3; i++) {
                Enemy enemy = new Enemy();
                enemy.imageRenderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/0.png"));
                enemy.position.set(enemy.imageRenderer.image.getWidth() + ((background.getWidth() - enemy.imageRenderer.image.getWidth() * 3) / 4 +enemy.imageRenderer.image.getWidth() ) * i , -enemy.imageRenderer.image.getHeight());
                enemies.add(enemy);
            }
            Enemy.oldTime = timeNow;
        }
        for (Enemy enemy : enemies) {
            enemy.castSpell(enemySpells);
            enemy.move();
            enemy.coolDownSpell();
        }

        for (EnemySpell enemySpell : enemySpells) {
            enemySpell.move();
        }
    }

    public void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0 ,this.getWidth(), this.getHeight());

        backBufferGraphics2D.drawImage(background, 0, backgroundY, null);
        player.render(backBufferGraphics2D);
        for (PlayerSpell playerSpell : playerSpells){
            playerSpell.render(backBufferGraphics2D);
        }
        for (Enemy enemy : enemies ){
            enemy.render(backBufferGraphics2D);
            for (EnemySpell enemySpell : enemySpells ) {
                enemySpell.render(backBufferGraphics2D);
            }
        }

        Graphics2D g2d = (Graphics2D)this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void loadImage() {
            background = Utils.loadAssetImage( "background/0.png");
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
