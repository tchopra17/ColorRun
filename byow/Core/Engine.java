package byow.Core;

import byow.InputDemo.InputSource;
import byow.TileEngine.TERenderer;//comment

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.Stopwatch;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Engine {
    TERenderer ter = new TERenderer(); //comment
    /* Feel free to change the width and height. */
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    private Random rand;
    private List<Room> roomList = new ArrayList<>();
    private double numFilledTiles;
    private TETile[][] world;
    private int player1X;
    private int player1Y;
    private int player2X;
    private int player2Y;
    private KeyboardListener inputSource;
    private HashMap<String, Position> player1Tiles;
    private HashMap<String, Position> player2Tiles;
    String tushar;
    private String inputs;
    private int player1Score;
    private int player2Score;
    private Position portal;
    private Position portal2;
    private Position colorSwitch;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        inputSource = new KeyboardListener(WIDTH, HEIGHT);
        inputs = "";
        String s = "";
        tushar = "";
        while (inputSource.possibleNextInput()) {
            if (inputSource.getNextKey() == 'N') {
                inputs += 'n';
                s = getSeed();
                tushar = s;
                System.out.println(s);
                break;
            } else if (inputSource.getNextKey() == 'L') {
                loadWorld();
            } else if (inputSource.getNextKey() == 'Q') {
                System.exit(0);
            } else {
                continue;
            }
        }
        interactWithInputString(s);
        initializePlayers();
    }

    // Starts the world with two players in two different rooms, and rest of world is dark
    private void initializePlayers() {
        inputSource.setWOrld(world);
        int room = 0;
        int room2 = 3;
        int room3 = 2;
        int room4 = 4;

        Room r = roomList.get(room);
        Position center = r.center;

        Room r2 = roomList.get(room2);
        Position center2 = r2.center;

        Room r3 = roomList.get(room3);
        portal = r3.center;

        Room r4 = roomList.get(room4);
        portal2 = r4.center;

        Room r5 = roomList.get(1);
        colorSwitch = r5.center;

        player1X = center.x;
        player1Y = center.y;

        player2X = center2.x;
        player2Y = center2.y;

        player1Score = 0;
        player2Score = 0;

        player1Tiles = new HashMap<>();
        player2Tiles = new HashMap<>();

        String positionString = "" + player1X + player1Y;
        player1Tiles.put(positionString, new Position(player1X, player1Y));
        letThereBeLight();

        String positionString2 = "" + player2X + player2Y;
        player2Tiles.put(positionString2, new Position(player2X, player2Y));
        letThereBeLight2();


        setPlayerGradient1(player1X);
        setPlayerGradient2(player2X);
        world[colorSwitch.x][colorSwitch.y] = Tileset.SWITCH;

        world[portal.x][portal.y] = Tileset.FLOWER;
        world[portal2.x][portal2.y] = Tileset.FLOWER;


        render(world);
        getInputs();
    }

    private void loadPlayers() {
        int room = 0;
        int room2 = 3;
        int room3 = 2;
        int room4 = 4;

        Room r = roomList.get(room);
        Position center = r.center;

        Room r2 = roomList.get(room2);
        Position center2 = r2.center;

        Room r3 = roomList.get(room3);
        portal = r3.center;

        Room r4 = roomList.get(room4);
        portal2 = r4.center;

        Room r5 = roomList.get(1);
        colorSwitch = r5.center;

        player1X = center.x;
        player1Y = center.y;

        player2X = center2.x;
        player2Y = center2.y;

        player1Score = 0;
        player2Score = 0;

        player1Tiles = new HashMap<>();
        player2Tiles = new HashMap<>();

        String positionString = "" + player1X + player1Y;
        player1Tiles.put(positionString, new Position(player1X, player1Y));
        letThereBeLight();

        String positionString2 = "" + player2X + player2Y;
        player2Tiles.put(positionString2, new Position(player2X, player2Y));
        letThereBeLight2();


        setPlayerGradient1(player1X);
        setPlayerGradient1(player2X);
        world[colorSwitch.x][colorSwitch.y] = Tileset.SWITCH;
        
        world[portal.x][portal.y] = Tileset.FLOWER;
        world[portal2.x][portal2.y] = Tileset.FLOWER;

        render(world); //comment
    }
    private void switcharoo() {
        HashMap<String, Position> temp1 = new HashMap<>();
        HashMap<String, Position> temp2 = new HashMap<>();
        for (String k: player1Tiles.keySet()) {
            Position val1 = player1Tiles.get(k);
            temp2.put(k, val1);
        }
        for (String k2: player2Tiles.keySet() ) {
            Position val2 = player2Tiles.get(k2);
            temp1.put(k2, val2);
        }
        player1Tiles = temp1;
        player2Tiles = temp2;
        for (String k3: player1Tiles.keySet()) {
            Position pos1 = player1Tiles.get(k3);
            setFloorGradient1(pos1.x, pos1.y);
        }
        for (String k4: player2Tiles.keySet()) {
            Position pos2 = player2Tiles.get(k4);
            setFloorGradient2(pos2.x, pos2.y);
        }
        int tempScore = player1Score;
        player1Score = player2Score;
        player2Score = tempScore;
        colorSwitch.x = -1;
        colorSwitch.y = -1;
    }
    private void removePortal(){
        portal.x = -1;
        portal.y = -1;
        portal2.x = -1;
        portal2.y = -1;
    }
    //handles keyboard inputs and moves players or quits
    private void getInputs() {
        while (true) {
            char c = inputSource.getNextKey();
            movePlayers(c);
            render(world); //comment
        }
    }

    private void movePlayers(char c) {
        inputSource.setStarted();
        setFloorGradient1(player1X, 0);
        setFloorGradient2(player2X, 0);

        if (c == 'W') {
            inputs += 'w';
            if (!world[player1X][player1Y + 1].description().equals("wall")) {
                player1Y++;
                letThereBeLight();
            }
        } else if (c == 'A') {
            inputs += 'a';
            if (!world[player1X - 1][player1Y].description().equals("wall")) {
                player1X--;
                letThereBeLight();
            }
        } else if (c == 'S') {
            inputs += 's';
            if (!world[player1X][player1Y - 1].description().equals("wall")) {
                player1Y--;
                letThereBeLight();
            }
        } else if (c == 'D') {
            inputs += 'd';
            if (!world[player1X + 1][player1Y].description().equals("wall")) {
                player1X++;
                letThereBeLight();
            }
        } else if (c == 'I') {
            inputs += 'i';
            if (!world[player2X][player2Y + 1].description().equals("wall")) {
                player2Y++;
                letThereBeLight2();
            }
        } else if (c == 'J') {
            inputs += 'j';
            if (!world[player2X - 1][player2Y].description().equals("wall")) {
                player2X--;
                letThereBeLight2();
            }
        } else if (c == 'K') {
            inputs += 'k';
            if (!world[player2X][player2Y - 1].description().equals("wall")) {
                player2Y--;
                letThereBeLight2();
            }
        } else if (c == 'L') {
            inputs += 'l';
            if (!world[player2X + 1][player2Y].description().equals("wall")) {
                player2X++;
                letThereBeLight2();
            }
        } else if (c == ':') {
            while (inputSource.possibleNextInput()) {
                char ch = inputSource.getNextKey();
                if (ch == 'Q') {
                    saveWorld(inputs);
                    System.exit(0);
                } else {
                    break;
                }
            }
        }
        if (player1X == portal.x && player1Y == portal.y){
            player1Y = portal2.y;
            player1X = portal2.x;
            removePortal();
        } else if (player1X == portal2.x && player1Y == portal2.y) {
            player1Y = portal.y;
            player1X = portal.x;
            removePortal();
        } else if (player2X == portal.x && player2Y == portal.y){
            player2Y = portal2.y;
            player2X = portal2.x;
            removePortal();
        } else if (player1X == portal2.x && player1Y == portal2.y) {
            player2Y = portal.y;
            player2X = portal.x;
            removePortal();
        }
        if (player1X == colorSwitch.x && player1Y == colorSwitch.y) {
            switcharoo();
        } else if (player2X == colorSwitch.x && player2Y == colorSwitch.y) {
            switcharoo();
        }
        player1Score = player1Tiles.size();
        inputSource.setPlayer1Score(player1Score);
        player2Score = player2Tiles.size();
        inputSource.setPlayer2Score(player2Score);
        setPlayerGradient1(player1X);
        setPlayerGradient2(player2X);

    }
    // Renders world as you move along
    private void letThereBeLight() {
        String positionString = "" + player1X + player1Y;
        if (player2Tiles.containsKey(positionString)) {
            player2Tiles.remove(positionString);
        }
        player1Tiles.put(positionString, new Position(player1X, player1Y));
        int radius = 3;

        for (int i = player1X - radius; i < player1X + radius; i++) {
            for (int j = player1Y - radius; j < player1Y + radius; j++) {
                if (!(j >= HEIGHT) && !(i >= WIDTH) && !(i < 0) && !(j < 0)) {
                    String s = "" + i + j;
                    // if tile is not already colored
                    if (!player1Tiles.containsKey(s) && !player2Tiles.containsKey(s)) {
                        String tile = world[i][j].description();
                        if (tile.equals("wall")) {
                            world[i][j] = Tileset.WALL;
                        } else if (tile.equals("floor")) {
                            world[i][j] = Tileset.FLOOR;
                        }
                    } else {
                        // otherwise set to old color
                        int oldX = player1Tiles.get(positionString).x;
                        setFloorGradient1(oldX, 0);
                    }
                }
            }
        }
    }

    private void letThereBeLight2() {
        String positionString = "" + player2X + player2Y;
        if (player1Tiles.containsKey(positionString)) {
            player1Tiles.remove(positionString);
        }
        player2Tiles.put(positionString, new Position(player2X, player2Y));
        int radius = 3;
        for (int i = player2X - radius; i < player2X + radius; i++) {
            for (int j = player2Y - radius; j < player2Y + radius; j++) {
                if (!(j >= HEIGHT) && !(i >= WIDTH) && !(i < 0) && !(j < 0)) {
                    String s = "" + i + j;
                    if (!player2Tiles.containsKey(s) && !player1Tiles.containsKey(s)) {
                        String tile = world[i][j].description();
                        if (tile.equals("wall")) {
                            world[i][j] = Tileset.WALL;
                        } else if (tile.equals("floor")) {
                            world[i][j] = Tileset.FLOOR;
                        }
                    } else {
                        int oldX = player2Tiles.get(positionString).x;
                        setFloorGradient2(oldX, 0);
                    }
                }
            }
        }
    }


    // gets which section the player is in
    private int getFloorColor(int x) {
        double pos = (double) x / (double) WIDTH;
        pos *= 10;
        return (int) pos;
    }

    //gets the corresponding color for each section
    private void setFloorGradient1(int x, int why) {
        int ex = x;
        int y;
        if (why > 0) {
            y = why;
        } else {
            y = player1Y;
        }
        int a = getFloorColor(x);
        switch (a) {
            case 0:
                world[ex][y] = Tileset.FLOOR1;
                break;
            case 1:
                world[ex][y] = Tileset.FLOOR2;
                break;
            case 2:
                world[ex][y] = Tileset.FLOOR3;
                break;
            case 3:
                world[ex][y] = Tileset.FLOOR4;
                break;
            case 4:
                world[ex][y] = Tileset.FLOOR5;
                break;
            case 5:
                world[ex][y] = Tileset.FLOOR6;
                break;
            case 6:
                world[ex][y] = Tileset.FLOOR7;
                break;
            case 7:
                world[ex][y] = Tileset.FLOOR8;
                break;
            case 8:
                world[ex][y] = Tileset.FLOOR9;
                break;
            case 9:
                world[ex][y] = Tileset.FLOOR10;
                break;
        }
    }

    private void setPlayerGradient1(int x) {
        int ex = player1X;
        int y = player1Y;
        int a = getFloorColor(x);
        switch (a) {
            case 0:
                world[ex][y] = Tileset.AVATAR1;
                break;
            case 1:
                world[ex][y] = Tileset.AVATAR2;
                break;
            case 2:
                world[ex][y] = Tileset.AVATAR3;
                break;
            case 3:
                world[ex][y] = Tileset.AVATAR4;
                break;
            case 4:
                world[ex][y] = Tileset.AVATAR5;
                break;
            case 5:
                world[ex][y] = Tileset.AVATAR6;
                break;
            case 6:
                world[ex][y] = Tileset.AVATAR7;
                break;
            case 7:
                world[ex][y] = Tileset.AVATAR8;
                break;
            case 8:
                world[ex][y] = Tileset.AVATAR9;
                break;
            case 9:
                world[ex][y] = Tileset.AVATAR10;
                break;
        }
    }

    private void setFloorGradient2(int x, int why) {
        int ex = x;
        int y;
        if (why > 0) {
            y = why;
        } else {
            y = player2Y;
        }
        int a = getFloorColor(x);
        switch (a) {
            case 0:
                world[ex][y] = Tileset.FLOOR11;
                break;
            case 1:
                world[ex][y] = Tileset.FLOOR12;
                break;
            case 2:
                world[ex][y] = Tileset.FLOOR13;
                break;
            case 3:
                world[ex][y] = Tileset.FLOOR14;
                break;
            case 4:
                world[ex][y] = Tileset.FLOOR15;
                break;
            case 5:
                world[ex][y] = Tileset.FLOOR16;
                break;
            case 6:
                world[ex][y] = Tileset.FLOOR17;
                break;
            case 7:
                world[ex][y] = Tileset.FLOOR18;
                break;
            case 8:
                world[ex][y] = Tileset.FLOOR19;
                break;
            case 9:
                world[ex][y] = Tileset.FLOOR20;
                break;
        }
    }

    private void setPlayerGradient2(int x) {
        int ex = player2X;
        int y = player2Y;
        int a = getFloorColor(x);
        switch (a) {
            case 0:
                world[ex][y] = Tileset.AVATAR11;
                break;
            case 1:
                world[ex][y] = Tileset.AVATAR12;
                break;
            case 2:
                world[ex][y] = Tileset.AVATAR13;
                break;
            case 3:
                world[ex][y] = Tileset.AVATAR14;
                break;
            case 4:
                world[ex][y] = Tileset.AVATAR15;
                break;
            case 5:
                world[ex][y] = Tileset.AVATAR16;
                break;
            case 6:
                world[ex][y] = Tileset.AVATAR17;
                break;
            case 7:
                world[ex][y] = Tileset.AVATAR18;
                break;
            case 8:
                world[ex][y] = Tileset.AVATAR19;
                break;
            case 9:
                world[ex][y] = Tileset.AVATAR20;
                break;
        }
    }

    //gets seed thats typed in main menu
    private String getSeed() {
        Font font = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(font);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.text(0.5, 0.4, "^^Type Seed^^");
        StdDraw.text(0.5, 0.3, "TYPE S WHEN READY TO START");
        String seed = "";
        while (inputSource.possibleNextInput()) {
            char c = inputSource.getNextKey();
            inputs += Character.toLowerCase(c);
            seed += c;
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.text(0.5, 0.5, seed);
            StdDraw.text(0.5, 0.4, "^^Type Seed^^");
            StdDraw.text(0.5, 0.3, "TYPE S WHEN READY TO START");
            if (c == 'S') {
                font = new Font("Monaco", Font.BOLD, 14);
                StdDraw.setFont(font);

                return seed;
            }
        }
        return seed;
    }
    private void loadWorld(){
        File f = new File("./lastWorld.txt");
        System.out.println("loading");
        String input = "";
        if (f.exists()) {
            try {
                //@source javatpoint.com
                FileInputStream fs = new FileInputStream(f);
                int i = 0;
                boolean gotSeed = false;
                while((i = fs.read()) != -1){
                    if ((char) i == 's' && !gotSeed){
                        gotSeed = true;
                        tushar += (char) i;
                    }
                    if (!gotSeed && (char) i != 'n'){
                        tushar += (char) i;
                    } else{
                        input += (char) i;
                    }
                }
                interactWithInputString(tushar);
                loadPlayers();
                input = input.toUpperCase();
                for (char c : input.toCharArray()){
                    movePlayers(c);
                }
                render(world); //comment
                getInputs();
                fs.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            }
        }

    }
    private void saveWorld(String keystrokes) {
        File f = new File("./lastWorld.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            fs.write(inputs.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, both of these calls:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        input = input.substring(1, input.length() - 1);
        Long seed = Long.parseLong(input);
        rand = new Random(seed);
        numFilledTiles = 0;
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initialize(finalWorldFrame);
        randomize(finalWorldFrame);
        render(finalWorldFrame);//comment
        world = finalWorldFrame;
        return finalWorldFrame;
    }

    private void render(TETile[][] world) {
        ter.renderFrame(world);//comment
    }

    private void initialize(TETile[][] world) {
        ter.initialize(WIDTH, HEIGHT); //comment
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void randomize(TETile[][] world) {
        double density = numFilledTiles / (WIDTH * HEIGHT);
        while (density < 0.4) {
            createRandRoom(world);
            density = numFilledTiles / (WIDTH * HEIGHT);
        }
        drawHallways(world);
    }

    private void createRandRoom(TETile[][] world) {
        int randX = rand.nextInt(WIDTH - 5);
        int randY = rand.nextInt(HEIGHT - 5);
        int randWidth = rand.nextInt(WIDTH / 4);
        while (randWidth < 4) {
            randWidth = rand.nextInt(WIDTH / 4);
        }
        int randHeight = rand.nextInt(HEIGHT / 4);
        while (randHeight < 4) {
            randHeight = rand.nextInt(HEIGHT / 4);
        }
        int topX = randX + randWidth - 1;
        int topY = randY + randHeight - 1;
        Room created = new Room(randX, randY, topX, topY);
        boolean overlap = false;
        boolean overEdge = false;
        if (topX >= WIDTH || topY >= HEIGHT) {
            overEdge = true;
        }
        for (Room r : roomList) {
            if (r.isOverlap(r, created)) {
                overlap = true;
                break;
            }
        }
        if (!overlap && !overEdge) {
            createRoom(created, randWidth, randHeight, world);
        }

    }

    private void createRoom(Room created, int width, int height, TETile[][] world) {
        roomList.add(created);
        numFilledTiles += (width * height);
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                int ex = created.x1 + x;
                int why = created.y1 + y;
                if (ex == created.x1 || ex == created.x2
                        || why == created.y1 || why == created.y2) {
                    world[ex][why] = Tileset.BLACKWALL;
                } else {
                    world[ex][why] = Tileset.BLACKFLOOR;
                }

            }
        }
    }

    private void drawHallways(TETile[][] world) {
        int nextRoom = 1;
        for (Room r : roomList) {
            if (nextRoom == roomList.size()) {
                break;
            }
            int currentX = r.center.x;
            int currentY = r.center.y;
            Room r2 = roomList.get(nextRoom);
            int nextX = r2.center.x;
            int nextY = r2.center.y;
            if (currentY < nextY) {
                vertHall(world, currentY, nextY, nextX);
            } else {
                vertHall(world, nextY, currentY, nextX);
            }
            if (currentX < nextX) {
                horiHall(world, currentX, nextX, currentY);
            } else {
                horiHall(world, nextX, currentX, currentY);
            }
            nextRoom += 1;
        }
    }

    private void vertHall(TETile[][] world, int startY, int endY, int x) {
        for (int y = startY; y <= endY; y++) {
            world[x][y] = Tileset.BLACKFLOOR;
            if ((x + 1) < WIDTH) {
                if (world[x + 1][y].equals(Tileset.NOTHING)) {
                    world[x + 1][y] = Tileset.BLACKWALL;
                }
            }
            if ((x - 1) >= 0) {
                if (world[x - 1][y].equals(Tileset.NOTHING)) {
                    world[x - 1][y] = Tileset.BLACKWALL;
                }
            }
        }
    }

    private void horiHall(TETile[][] world, int startX, int endX, int y) {
        for (int x = startX; x <= endX; x++) {
            world[x][y] = Tileset.BLACKFLOOR;
            if ((y + 1) < HEIGHT) {
                if (world[x][y + 1].equals(Tileset.NOTHING)) {
                    world[x][y + 1] = Tileset.BLACKWALL;
                }
            }
            if ((y - 1) >= 0) {
                if (world[x][y - 1].equals(Tileset.NOTHING)) {
                    world[x][y - 1] = Tileset.BLACKWALL;
                }
            }
        }
    }


    private class Room {
        private int x1;
        private int x2;
        private int y1;
        private int y2;
        private Position center;

        Room(int botX, int botY, int topX, int topY) {
            x1 = botX;
            y1 = botY;
            x2 = topX;
            y2 = topY;
            int midX = (x1 + x2) / 2;
            int midY = (y1 + y2) / 2;
            center = new Position(midX, midY);
        }

        private boolean isOverlap(Room existing, Room newroom) {
            if (newroom.y2 < existing.y1 || newroom.y1 > existing.y2) {
                return false;
            }
            if (newroom.x2 < existing.x1 || newroom.x1 > existing.x2) {
                return false;
            }
            return true;
        }

    }

    private class Position {
        private int x;
        private int y;

        Position(int ex, int why) {
            x = ex;
            y = why;
        }
    }
}
