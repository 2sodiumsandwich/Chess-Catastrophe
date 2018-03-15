/**
 * Board - main game
 */

public class Board{
    private static Tile[][] tiles = new Tile[8][8];
    public static void main(String[] args){
        setUp();
    }

    public static void movePiece(int x1, int y1, int x2, int y2, GameGui gui, Piece p){
        if(tiles[x1][y1].getPiece().isValid(x1, y1, x2, y2)){
            String c;
            if(tiles[x1][y1].getPiece().getColor()){c = "b";}else{c = "w";}
            gui.texNull(x1, y1);
            gui.texChange(x2, y2, tiles[x1][y1].getPiece().getName(), c);
        }
    }

    public static void setUp(){
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                tiles[x][y] = new Tile(null, x, y);
            }
        }
        GameGui GUI = new GameGui(); // generate board

        //Add bishops
        GUI.texChange(2, 0, "bishop", "b");
        GUI.texChange(5, 0, "bishop", "b");
        GUI.texChange(2, 7, "bishop", "w");
        GUI.texChange(5, 7, "bishop", "w");

        //Add pawns
        GUI.texChange(0, 1, "pawn", "b"); tiles[0][1] = new Tile(new Pawn(0, 1, true),0,1);
        GUI.texChange(1, 1, "pawn", "b"); tiles[1][1] = new Tile(new Pawn(1, 1, true),1,1);
        GUI.texChange(2, 1, "pawn", "b"); tiles[2][1] = new Tile(new Pawn(2, 1, true),2,1);
        GUI.texChange(3, 1, "pawn", "b"); tiles[3][1] = new Tile(new Pawn(3, 1, true),3,1);
        GUI.texChange(4, 1, "pawn", "b"); tiles[4][1] = new Tile(new Pawn(4, 1, true),4,1);
        GUI.texChange(5, 1, "pawn", "b"); tiles[5][1] = new Tile(new Pawn(5, 1, true),5,1);
        GUI.texChange(6, 1, "pawn", "b"); tiles[6][1] = new Tile(new Pawn(6, 1, true),6,1);
        GUI.texChange(7, 1, "pawn", "b"); tiles[7][1] = new Tile(new Pawn(7, 1, true),7,1);
        GUI.texChange(0, 6, "pawn", "w"); tiles[0][6] = new Tile(new Pawn(0, 6, false),0,6);
        GUI.texChange(1, 6, "pawn", "w"); tiles[1][6] = new Tile(new Pawn(1, 6, false),1,6);
        GUI.texChange(2, 6, "pawn", "w"); tiles[2][6] = new Tile(new Pawn(2, 6, false),2,6);
        GUI.texChange(3, 6, "pawn", "w"); tiles[3][6] = new Tile(new Pawn(3, 6, false),3,6);
        GUI.texChange(4, 6, "pawn", "w"); tiles[4][6] = new Tile(new Pawn(4, 6, false),4,6);
        GUI.texChange(5, 6, "pawn", "w"); tiles[5][6] = new Tile(new Pawn(5, 6, false),5,6);
        GUI.texChange(6, 6, "pawn", "w"); tiles[6][6] = new Tile(new Pawn(6, 6, false),6,6);
        GUI.texChange(7, 6, "pawn", "w"); tiles[7][6] = new Tile(new Pawn(7, 6, false),7,6);
    }
}