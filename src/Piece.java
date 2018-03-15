/**
 * Piece parent class for Chess Catastrophe
 * APCSA 2018 - Kevin & CJ
 * 
 * Class is parent of all chess pieces
 */

public class Piece{
    int x; int y;
    boolean alive = true;
    boolean color;

    public Piece(int xx, int yy, boolean colorr){
        color = colorr;
        x = xx; y = yy;
    }

    public boolean getColor(){
        return color;
    }

    public String getName(){
        return "pawn"; //returns pawn for testing purposes
        //method overridden in classes/
    }

    public boolean getStatus(){
        return alive;
    }

    public void setStatus(boolean status){
        alive = status;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
    }

    public int[][] ValidSquares(Tile[][] tiles){
        return new int[0][0];
    }

}