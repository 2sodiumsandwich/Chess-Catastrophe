/**
 * Bishop piece for java
 */

public class Bishop extends Piece{

    public Bishop(int x, int y, boolean color){
        super(x, y, color);
    }

    @Override
    public int[][] ValidSquares(Tile[][] tiles){
        int[][] validSquares = new int[14][2];

        return validSquares;
    }

    @Override
    public String getName(){
        return "bishop";
    }
}