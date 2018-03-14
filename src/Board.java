/**
 * Board - main game
 */

public class Board{
    public static void main(String[] args){
        Tile[][] tiles = new Tile[8][8];
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                tiles[x][y] = new Tile(null, x, y);
            }
        }
        GameGui GUI = new GameGui(); // generate board
        GUI.texChange(0,0, "pawn", "w");
        GUI.texChange(4,3, "pawn", "b");
        GUI.texChange(7,2, "pawn", "w");
        GUI.texChange(2,5, "pawn", "w");
        GUI.texChange(5,4, "pawn", "b");

    }


    public void changeIcon(int x, int y, GameGui gui, Piece p){

    }
}