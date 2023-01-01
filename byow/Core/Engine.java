package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.IOException;
import java.util.TreeMap;

public class Engine {
    TERenderer ter = new TERenderer();
    WorldGeneration world;
    InputSource inputSource = new KeyboardInputSource();
    public int colorScheme;
    public double curMouseX;
    public double curMouseY;
    public boolean disableThemeSelect;
    private boolean LightsOn = false;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 100;
    public static final int HEIGHT = 60;
    private final Font TEFont = new Font("Monaco", Font.BOLD, 14);

    public static final TreeMap<Character, Integer> KEYS_TO_DIRECTION= new TreeMap<>();

    public Engine() {
        ter.initialize(WIDTH, HEIGHT);
        KEYS_TO_DIRECTION.put('D', 0);
        KEYS_TO_DIRECTION.put('W', 1);
        KEYS_TO_DIRECTION.put('A', 2);
        KEYS_TO_DIRECTION.put('S', 3);
        StdDraw.show();
        curMouseX = 0.0;
        curMouseY = 0.0;
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.enableDoubleBuffering();
        colorScheme = 0;
        disableThemeSelect = false;
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() throws IOException {
        drawMenu();
        while (true) {
            if (this.world != null) {
                renderWorld();
            }

            if (StdDraw.hasNextKeyTyped()) {
                // maybe char c can represent mouse movements too!
                char c = inputSource.getNextKey();
                // Handle N key (automatically handles terminating S key too)
                if (c == 'N' || c == 'n') {
                    disableThemeSelect = true;
                    String longNum = "";
                    drawSeedInput(longNum);
                    while (inputSource.possibleNextInput()) {
                        char k = inputSource.getNextKey();
                        if (k == 's' || k == 'S') {
                            break;
                        } else if( k < '0' || k > '9') {
                            StdDraw.text(WIDTH/2, HEIGHT *3/4-5, "Please enter a digit!");
                            StdDraw.show();
                            continue;
                        } else {
                            longNum += k;
                        }

                        drawSeedInput(longNum);
                    }
                    long seed;
                    if(longNum.length() >0){
                        seed = Long.parseLong(longNum);
                    } else {
                        seed = 0;
                    }
                    world = new WorldGeneration(seed, WIDTH, HEIGHT, colorScheme);
                    renderWorld();
                }

                // Handle WASD keys
                if (KEYS_TO_DIRECTION.containsKey(c)) {
                    world.moveCharacter(KEYS_TO_DIRECTION.get(c));
                    renderWorld();
                }

                // Handle Q key
                if (c == 'Q' || c == 'q') {
                    // Write to file and quit game
                    // When writing to file, you only need to save seed, and avatar location?
                    world.save();
                    System.exit(0);
                }
                if (c == 'L' || c == 'l') {
                    disableThemeSelect = true;
                    // Write to file and quit game
                    // When writing to file, you only need to save seed, and avatar location?
                    world = new WorldGeneration(0, WIDTH, HEIGHT, 0);
                    world.load();
                    renderWorld();
                }
                if (c == 'B' || c == 'b') {
                    world.changeLights();
                    renderWorld();
                }

                if ( (c == 'T' || c == 't') && !disableThemeSelect) {
                    drawThemeSelect();
                    while (true) {
                        char k = inputSource.getNextKey();
                        if (k < '0' || k > '7') {
                            StdDraw.text(WIDTH/2, HEIGHT *3/4-24, "Please enter a digit!");
                            StdDraw.show();
                            continue;
                        } else {
                            colorScheme = Character.getNumericValue(k);
                            break;
                        }
                    }
                    drawMenu();
                }
            }

            StdDraw.pause(10);
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        int cnt = 0;
        while(cnt < input.length()) {
            char c = input.charAt(cnt++);

            // Handle N key (automatically handles terminating S key too)
            if (c == 'N' || c == 'n') {
                String longNum = "";
                drawSeedInput(longNum);
                while (true) {
                    char k = input.charAt(cnt++);
                    if (k == 's' || k == 'S') {
                        break;
                    }
                    else if( k < '0' || k > '9') {
                        StdDraw.text(WIDTH/2, HEIGHT *3/4-5, "You can only put in a digit");
                        StdDraw.show();
                        continue;
                    }
                    else {
                        longNum += k;
                    }

                    drawSeedInput(longNum);
                }
                long seed;
                if(longNum.length() >0){
                    seed = Long.parseLong(longNum);
                }
                else {
                    seed = 0;
                }
                world = new WorldGeneration(seed, WIDTH, HEIGHT, 0);
                renderWorld();
            }

            // Handle WASD keys
            if (KEYS_TO_DIRECTION.containsKey(c)) {
                world.moveCharacter(KEYS_TO_DIRECTION.get(c));
                renderWorld();
            }

            // Handle Q key
            if (c == 'Q' || c == 'q') {
                // Write to file and quit game
                // When writing to file, you only need to save seed, and avatar location?
                System.exit(0);
            }
            if (c == 'B' || c == 'b') {
                LightsOn = !LightsOn;
            }
            renderWorld();
        }


        return world.getWorld();
    }

    /**
     * Draws the main menu
     */
    private void drawMenu(){


        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(fontBig);

        StdDraw.text(WIDTH/2, HEIGHT * 3/4, "CS 61B: The Game");
        Font fontSmall = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(fontSmall);
        StdDraw.text(WIDTH/2, HEIGHT * 1/2, "New Game (N)");
        StdDraw.text(WIDTH/2, HEIGHT * 1/2-2, "Load Game (L)");
        StdDraw.text(WIDTH/2, HEIGHT * 1/2-4, "Set Theme (T)");
        StdDraw.text(WIDTH/2, HEIGHT * 1/2-6, "Quit (Q)");
        StdDraw.show();
    }

    /**
     * This will take in a string s and draw a menu asking for string input.
     * @param s
     */
    private void drawSeedInput(String s){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH/2, HEIGHT *3/4, "Please write your Seed");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-2, "Press s to start your game with your seed");
        StdDraw.text(WIDTH/2, HEIGHT/3, s);
        StdDraw.show();
    }
    private void drawThemeSelect() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH/2, HEIGHT *3/4, "Please type the number corresponding to your preferred theme");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-3, "0) Random    ");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-6, "1) Sakura    ");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-9, "2) Mint      ");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-12, "3) Solar     ");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-15,"4) Lavender  ");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-18,"5) Navy      ");
        StdDraw.text(WIDTH/2, HEIGHT *3/4-21,"6) Acacia    ");
        StdDraw.show();
    }
    private void renderWorld(){
        StdDraw.setFont(TEFont);
        ter.renderFrame(world.getWorld());
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(fontBig);
        StdDraw.textLeft(0, HEIGHT-1.1, "Coins: " + world.getCoinCount());
        StdDraw.text(WIDTH/2, HEIGHT-1.1, "Press B to toggle line of sight");
        // What tile you're on. Just change text
        StdDraw.textRight(WIDTH, HEIGHT-1.1, tileUnderMouse());
        StdDraw.show();

    }
    private String tileUnderMouse() {
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();
        String description = world.getWorld()[x % WIDTH][y % HEIGHT].description();
        return Character.toUpperCase(description.charAt(0)) + description.substring(1);
    }
}
