/**
 * Spot on the board
 */

public class Tile{
    Piece piece;
    int x; int y;
    public Tile(Piece p, int xx, int yy){
        piece = p; x = xx; y = yy;
    }

    public Piece getPiece(){
        return piece;
    }
}