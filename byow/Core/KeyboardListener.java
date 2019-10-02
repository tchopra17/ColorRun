package byow.Core;

import byow.InputDemo.InputSource;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.Stopwatch;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class KeyboardListener implements InputSource {
    private double mouseX = 0;
    private static final boolean PRINT_TYPED_KEYS = false;
    private TETile[][] world;
    private int player1Score;
    private int player2Score;
    private Stopwatch sw;
    private boolean started = false;
    public KeyboardListener(int w, int h) {
        Font font = new Font ("Helvetica", Font.BOLD, 60);
        int pixWidth = w * 16;
        int pixHeight = h * 16;
        StdDraw.setCanvasSize(pixWidth, pixHeight);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.8, "M3NGO");
        font = new Font("Helvetica", Font.PLAIN, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.text(0.5, 0.7, "follow me on SoundCloud: @M3NGO");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(0.5, 0.5, "New Game(n)");
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.text(0.5, 0.4, "Load(l)");
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.text(0.5, 0.3, "Quit(q)");
        font = new Font("Monaco", Font.BOLD, 14);
        StdDraw.setFont(font);
    }

    public char getNextKey() {

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(5, 43, "Player 1: "+ player1Score);
        StdDraw.show();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(35, 43, "Player 2: "+ player2Score);
        StdDraw.show();

        while (true) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(20, 1, 3, 1);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(20, 1, ""+getDescription());
            StdDraw.show();
            if (started) {
                if (60 - ((int) sw.elapsedTime()) == 0){
                   // winningScreen();
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledRectangle(20, 43, 10, 1);
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.text(20, 43, "Time Left: " + (60 - (int) sw.elapsedTime()));
                    StdDraw.show();
                }
            }
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (PRINT_TYPED_KEYS) {
                    System.out.print(c);
                }
                return c;
            }
        }
    }

    public boolean possibleNextInput() {
        return true;
    }
    public void setWOrld( TETile[][] world){
        this.world = world;
    }
    private String getDescription(){
        if (world != null) {
            int x = (int) StdDraw.mouseX();
            int y = (int) StdDraw.mouseY() - 3;
            if (y < 3){
                y = 0;
            } else if (y >= world.length){
                y = world.length - 1;
            }

            return world[x][y].description();
        }
        return "";
    }
    public void setStarted(){
        if (!started) {
            sw = new Stopwatch();
            started = true;
        }
    }
    public void setPlayer1Score(int score){
        player1Score = score;
    }
    public void setPlayer2Score(int score){
        player2Score = score;
    }
}
