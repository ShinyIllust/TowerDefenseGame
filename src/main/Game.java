package main;

import helpz.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;


public class Game extends JFrame implements Runnable {

    private final double FPS_SET = 120;
    private final double UPS_SET = 60;

    private GameScreen gameScreen;

    private Thread gameThread;

    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private TileManager tileManager;

    public Game(){
        initClasses();

        createDefaultLevel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        editing = new Editing(this);

    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for(int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }

        LoadSave.createLevel("new_level", arr);
    }

    private void start(){
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    public void updateGame(){

    }

    public static void main(String[] args){
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run(){
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while(true){

            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame){
                lastFrame = now;
                repaint();
                frames++;
            }
            if(now - lastUpdate >= timePerUpdate){
                lastUpdate = now;
                updateGame();
                updates++;
            }

            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                // System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    //Getters and setters
    public Render getRender(){
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Editing getEditor(){
        return editing;
    }

    public TileManager getTileManager(){
        return tileManager;
    }
}
