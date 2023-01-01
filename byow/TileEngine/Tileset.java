package byow.TileEngine;

import java.awt.Color;

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
    public static final TETile COIN = new TETile('$', Color.yellow, Color.black, "coin");

    // Default color scheme
    public static final TETile AVATAR = new TETile('ツ', Color.white, Color.black, "you");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall");


    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor");

    // Sakura color scheme
    public static final TETile SAKURA_AVATAR = new TETile('ツ', new Color(252,209,215), Color.black, "you");
    public static final TETile SAKURA_WALL = new TETile('#', new Color(86,33,53), new Color(195,130,158),
            "wall");
    public static final TETile SAKURA_FLOOR = new TETile('·', new Color(195,130,158), new Color(233,177,205),
            "floor");
    public static final TETile SAKURA_COIN = new TETile('$', Color.black, new Color(233,177,205),
            "coin");

    // Mint color scheme
    public static final TETile MINT_AVATAR = new TETile('ツ', new Color(189, 255, 253), Color.black, "you");
    public static final TETile MINT_WALL = new TETile('#', new Color(94,105,115), new Color(124,255,196),
            "wall");
    public static final TETile MINT_FLOOR = new TETile('·', new Color(106,190,167), new Color(159,255,245),
            "floor");
    public static final TETile MINT_COIN = new TETile('$', Color.black, new Color(159,255,245),
            "coin");

    // Solar color scheme
    public static final TETile SOLAR_AVATAR = new TETile('ツ', new Color(230, 201, 76), Color.black, "you");
    public static final TETile SOLAR_WALL = new TETile('#', new Color(51,15,10), new Color(239,221,141),
            "wall");
    public static final TETile SOLAR_FLOOR = new TETile('·', new Color(101,116,58), new Color(244,253,175),
            "floor");
    public static final TETile SOLAR_COIN = new TETile('$', Color.black, new Color(244,253,175),
            "coin");

    // Lavender
    public static final TETile LAVENDER_AVATAR = new TETile('ツ', new Color(208,171,160), Color.black, "you");
    public static final TETile LAVENDER_WALL = new TETile('#', new Color(237,207,142), new Color(194,140,174),
            "wall");
    public static final TETile LAVENDER_FLOOR = new TETile('·', new Color(186,123,161), new Color(222,196,161),
            "floor");
    public static final TETile LAVENDER_COIN = new TETile('$', Color.black, new Color(222,196,161),
            "coin");

    // Navy
    public static final TETile NAVY_AVATAR = new TETile('ツ', new Color(175,192,212), Color.black, "you");
    public static final TETile NAVY_WALL = new TETile('#', new Color(91,192,190), new Color(58,80,107),
            "wall");
    public static final TETile NAVY_FLOOR = new TETile('·', new Color(28,37,65), new Color(194,255,246),
            "floor");
    public static final TETile NAVY_COIN = new TETile('$', Color.black, new Color(194,255,246),
            "coin");

    // Acacia
    public static final TETile ACACIA_AVATAR = new TETile('ツ', new Color(198,128,138), Color.black, "you");
    public static final TETile ACACIA_WALL = new TETile('#', new Color(55,37,73), new Color(183,93,105),
            "wall");
    public static final TETile ACACIA_FLOOR = new TETile('·', new Color(119,76,96), new Color(234,205,194),
            "floor");
    public static final TETile ACACIA_COIN = new TETile('$', Color.black, new Color(234,205,194), "coin");

}

