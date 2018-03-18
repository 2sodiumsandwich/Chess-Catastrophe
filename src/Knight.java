/**
 * Knight piece for java
 */

public class Knight extends Piece{

    public Knight(int x, int y, boolean color){
        super(x, y, color);
    }

    @Override
    public int[][] ValidSquares(Tile[][] tiles){
        int[][] validSquares = new int[8][2];
        int i=0;
        for(int t = 0; t<validSquares.length; t++){
            validSquares[t][0] = -1; validSquares[t][1] = -1;
        }
        //implement knight movement here
        return validSquares;
    }

    @Override
    public String getName(){
        return "knight";
    }
}