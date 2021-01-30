package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final long SEED = 42;
    private static final Random RANDOM = new Random(SEED);

    //init
    private static void init(TETile[][] world) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }
    private static int width(int sz,int i) {
        if(i>=sz) {
            return (2*sz-i-1)*2+sz;
        }
        return sz+2*i;
    }

    public static void addRow(TETile[][] world,int width,int x,int y,TETile t){
        for(int i=0;i<width;i++) {
            world[x+i][y] = TETile.colorVariant(t,32,32,32,RANDOM);
        }
    }
    public static int offset(int sz,int i) {
        if(i>=sz) {
            return -(2*sz-i-1);
        }
        return -i;
    }
    public static TETile getRandomTile() {
        int tileNum = RANDOM.nextInt(5);
        TETile tile;
        switch (tileNum) {
            case 0:
                tile = Tileset.WALL;
                break;
            case 1:
                tile = Tileset.GRASS;
                break;
            case 2:
                tile = Tileset.TREE;
                break;
            case 3:
                tile = Tileset.MOUNTAIN;
                break;
            case 4:
                tile = Tileset.PLAYER;
                break;
            default:
                tile = Tileset.NOTHING;
                break;
        }
        return tile;
    }

    public static void addHexagon(TETile[][] world,int sz,int x,int y,TETile t) {
        for(int i=0;i<2*sz;i++) {
            int yy = y+i;
            int xx = x+offset(sz,i);
            System.out.println(xx+" "+yy);
            int sz2 = width(sz,i);
            System.out.println(sz2);
            addRow(world,sz2,xx,yy,t);
        }
    }
    @Test
    public void testHexRowWidth() {
        assertEquals(3,width(3,5));
        assertEquals(7,width(3,3));
    }

    public static void draw(TETile[][] world,int sz,int x,int y) {//3,4,5,4,3
        for(int i=0;i<3;i++) {
            addHexagon(world,sz,x,y+sz*i*2,getRandomTile());
        }
        for(int i=0;i<4;i++) {
            addHexagon(world,sz,x+sz*2-1,y+sz*i*2-sz,getRandomTile());
        }
        for(int i=0;i<5;i++) {
            addHexagon(world,sz,x+sz*4-2,y+sz*i*2-sz*2,getRandomTile());
        }
        for(int i=0;i<4;i++) {
            addHexagon(world,sz,x+sz*6-3,y+sz*i*2-sz,getRandomTile());
        }
        for(int i=0;i<3;i++) {
            addHexagon(world,sz,x+sz*8-4,y+sz*i*2,getRandomTile());
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];
        init(hexWorld);
        draw(hexWorld,3,15,15);
        ter.renderFrame(hexWorld);
    }

}
