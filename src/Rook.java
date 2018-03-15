/**
 * Rook piece for java
 */
public class Rook extends Piece{

    public Rook(int x, int y, boolean color){
        super(x, y, color);
    }

    @Override
    public String getName(){
        return "rook";
    }
}