/**
 * Knight piece for java
 */

public class Knight extends Piece{

    public Knight(int x, int y, boolean color){
        super(x, y, color);
    }

    @Override
    public String getAbbr(){
        return "N";
    }

    @Override
    public int[][] ValidSquares(Tile[][] tiles){
        int[][] validSquares = new int[8][2];
        int i=0;
        for(int t = 0; t<validSquares.length; t++){
            validSquares[t][0] = -1; validSquares[t][1] = -1;
        }
            //Down Right
            if(isValid(this.x+1, this.y+2) && tiles[this.x+1][this.y+2].getPiece() == null){
                validSquares[i][0] = this.x+1; validSquares[i][1] = this.y+2;
                i++;
            } else if(isValid(this.x+1, this.y+2) && tiles[this.x+1][this.y+2].getPiece() != null){
                if(tiles[this.x+1][this.y+2].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x+1; validSquares[i][1] = this.y+2;
                    i++;
                }
            }
                //Down Left
            if(isValid(this.x-1, this.y+2) && tiles[this.x-1][this.y+2].getPiece() == null){
                validSquares[i][0] = this.x-1; validSquares[i][1] = this.y+2;
                i++;
            } else if(isValid(this.x-1, this.y+2) && tiles[this.x-1][this.y+2].getPiece() != null){
                if(tiles[this.x-1][this.y+2].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x-1; validSquares[i][1] = this.y+2;
                    i++;
                }
            }
                //Up Left
            if(isValid(this.x-1, this.y-2) && tiles[this.x-1][this.y-2].getPiece() == null){
                validSquares[i][0] = this.x-1; validSquares[i][1] = this.y-2;
                i++;
            } else if(isValid(this.x-1, this.y-2) && tiles[this.x-1][this.y-2].getPiece() != null){
                if(tiles[this.x-1][this.y-2].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x-1; validSquares[i][1] = this.y-2;
                    i++;
                }
            } 
                //Up Right
            if(isValid(this.x+1, this.y-2) && tiles[this.x+1][this.y-2].getPiece() == null){
                validSquares[i][0] = this.x+1; validSquares[i][1] = this.y-2;
                i++;
            } else if(isValid(this.x+1, this.y-2) && tiles[this.x+1][this.y-2].getPiece() != null){
                if(tiles[this.x+1][this.y-2].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x+1; validSquares[i][1] = this.y-2;
                    i++;
                }
            }
                //Left Down
            if(isValid(this.x-2, this.y+1) && tiles[this.x-2][this.y+1].getPiece() == null){
                validSquares[i][0] = this.x-2; validSquares[i][1] = this.y+1;
                i++;
            } else if(isValid(this.x-2, this.y+1) && tiles[this.x-2][this.y+1].getPiece() != null){
                if(tiles[this.x-2][this.y+1].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x-2; validSquares[i][1] = this.y+1;
                    i++;
                }
            }
                //Left Up
            if(isValid(this.x-2, this.y-1) && tiles[this.x-2][this.y-1].getPiece() == null){
                validSquares[i][0] = this.x-2; validSquares[i][1] = this.y-1;
                i++;
            } else if(isValid(this.x-2, this.y-1) && tiles[this.x-2][this.y-1].getPiece() != null){
                if(tiles[this.x-2][this.y-1].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x-2; validSquares[i][1] = this.y-1;
                    i++;
                }
            }
            //Right Down
            if(isValid(this.x+2, this.y+1) && tiles[this.x+2][this.y+1].getPiece() == null){
                validSquares[i][0] = this.x+2; validSquares[i][1] = this.y+1;
                i++;
            } else if(isValid(this.x+2, this.y+1) && tiles[this.x+2][this.y+1].getPiece() != null){
                if(tiles[this.x+2][this.y+1].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x+2; validSquares[i][1] = this.y+1;
                    i++;
                }
            }
            //Right Up
            if(isValid(this.x+2, this.y-1) && tiles[this.x+2][this.y-1].getPiece() == null){
                validSquares[i][0] = this.x+2; validSquares[i][1] = this.y-1;
                i++;
            } else if(isValid(this.x+2, this.y-1) && tiles[this.x+2][this.y-1].getPiece() != null){
                if(tiles[this.x+2][this.y-1].getPiece().getColor() != this.getColor()){
                    validSquares[i][0] = this.x+2; validSquares[i][1] = this.y-1;
                    i++;
                }
            }
        
        //implement knight movement here
        return validSquares;
    }

    @Override
    public String getName(){
        return "knight";
    }
}