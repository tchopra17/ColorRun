package byow.TileEngine;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final Color floorDot = Color.white;

    public static final TETile AVATAR = new TETile('o', Color.white, Color.black, "you");
    public static final TETile WALL = new TETile('#', new Color(71, 71, 71), new Color(71, 71, 71),
            "wall");
    public static final TETile FLOOR = new TETile('·', floorDot, Color.black,
            "floor");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");


    // CUSTOM TILES

    //Player 1
    public static final TETile BLACKWALL = new TETile('#', Color.black, Color.black,
            "wall");
    public static final TETile BLACKFLOOR = new TETile('·', Color.black, Color.black,
            "floor");
    public static final TETile FLOOR1 = new TETile('·', floorDot, new Color(0, 212, 255),
            "floor");
    public static final TETile FLOOR2 = new TETile('·', floorDot, new Color(62, 195, 255),
            "floor");
    public static final TETile FLOOR3 = new TETile('·', floorDot, new Color(92, 173, 255),
            "floor");
    public static final TETile FLOOR4 = new TETile('·', floorDot, new Color(107, 156, 255),
            "floor");
    public static final TETile FLOOR5 = new TETile('·', floorDot, new Color(122, 137, 255),
            "floor");
    public static final TETile FLOOR6 = new TETile('·', floorDot, new Color(135, 118, 255),
            "floor");
    public static final TETile FLOOR7 = new TETile('·', floorDot, new Color(147, 101, 255),
            "floor");
    public static final TETile FLOOR8 = new TETile('·', floorDot, new Color(157, 80, 255),
            "floor");
    public static final TETile FLOOR9 = new TETile('·', floorDot, new Color(174, 38, 255),
            "floor");
    public static final TETile FLOOR10 = new TETile('·', floorDot, new Color(181, 0, 255),
            "floor");

    public static final TETile AVATAR1 = new TETile('o', floorDot, new Color(0, 212, 255),
            "you");
    public static final TETile AVATAR2 = new TETile('o', floorDot, new Color(62, 195, 255),
            "you");
    public static final TETile AVATAR3 = new TETile('o', floorDot, new Color(92, 173, 255),
            "you");
    public static final TETile AVATAR4 = new TETile('o', floorDot, new Color(107, 156, 255),
            "you");
    public static final TETile AVATAR5 = new TETile('o', floorDot, new Color(122, 137, 255),
            "you");
    public static final TETile AVATAR6 = new TETile('o', floorDot, new Color(135, 118, 255),
            "you");
    public static final TETile AVATAR7 = new TETile('o', floorDot, new Color(147, 101, 255),
            "you");
    public static final TETile AVATAR8 = new TETile('o', floorDot, new Color(157, 80, 255),
            "you");
    public static final TETile AVATAR9 = new TETile('o', floorDot, new Color(174, 38, 255),
            "you");
    public static final TETile AVATAR10 = new TETile('o', floorDot, new Color(181, 0, 255),
            "you");

    //Player 2
    public static final TETile FLOOR11 = new TETile('·', floorDot, new Color(2, 185, 2),
            "floor");
    public static final TETile FLOOR12 = new TETile('·', floorDot, new Color(61, 191, 4),
            "floor");
    public static final TETile FLOOR13 = new TETile('·', floorDot, new Color(90, 195, 8),
            "floor");
    public static final TETile FLOOR14 = new TETile('·', floorDot, new Color(114, 200, 7),
            "floor");
    public static final TETile FLOOR15 = new TETile('·', floorDot, new Color(137, 206, 9),
            "floor");
    public static final TETile FLOOR16 = new TETile('·', floorDot, new Color(157, 209, 8),
            "floor");
    public static final TETile FLOOR17 = new TETile('·', floorDot, new Color(179, 215, 9),
            "floor");
    public static final TETile FLOOR18 = new TETile('·', floorDot, new Color(198, 220, 6),
            "floor");
    public static final TETile FLOOR19 = new TETile('·', floorDot, new Color(237, 230, 2),
            "floor");
    public static final TETile FLOOR20 = new TETile('·', floorDot, new Color(255, 233, 0),
            "floor");

    public static final TETile AVATAR11 = new TETile('∆', floorDot, new Color(2, 185, 2),
            "you");
    public static final TETile AVATAR12 = new TETile('∆', floorDot, new Color(61, 191, 4),
            "you");
    public static final TETile AVATAR13 = new TETile('∆', floorDot, new Color(90, 195, 8),
            "you");
    public static final TETile AVATAR14 = new TETile('∆', floorDot, new Color(114, 200, 7),
            "you");
    public static final TETile AVATAR15 = new TETile('∆', floorDot, new Color(137, 206, 9),
            "you");
    public static final TETile AVATAR16 = new TETile('∆', floorDot, new Color(157, 209, 8),
            "you");
    public static final TETile AVATAR17 = new TETile('∆', floorDot, new Color(179, 215, 9),
            "you");
    public static final TETile AVATAR18 = new TETile('∆', floorDot, new Color(198, 220, 6),
            "you");
    public static final TETile AVATAR19 = new TETile('∆', floorDot, new Color(237, 230, 2),
            "you");
    public static final TETile AVATAR20 = new TETile('∆', floorDot, new Color(255, 233, 0),
            "you");

    public static final TETile SWITCH = new TETile('.', floorDot, new Color(203, 110, 117, 242),
            "Switch");



}


