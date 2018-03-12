/**
 * Piece parent class for Chess Catastrophe
 * APCSA 2018 - Kevin & CJ
 * 
 * Class is parent of all chess pieces
 */

public class Piece{
    int x; int y;
    boolean alive;

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
    
    public boolean isValid(int startX, int startY, int toX, int toY){
        return (startX > 7 || startX < 0 || startY > 7 || startX < 0 || toX > 7 || toX < 0 || toY > 7 || toX < 0 || (startX == toX) || (startY == toY));
        //checks if the move requested is within board boundaries
    }
    

}