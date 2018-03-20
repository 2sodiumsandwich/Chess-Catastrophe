/**
 * King piece for java
 */

public class King extends Piece{

    public King(int x, int y, boolean color){
        super(x, y, color);
    }

    @Override
    public String getAbbr(){
        return "K";
    }

    @Override
    public int[][] ValidSquares(Tile[][] tiles){
        int[][] validSquares = new int[10][2];
        int i=0;
        for(int t = 0; t<validSquares.length; t++){
            validSquares[t][0] = -1; validSquares[t][1] = -1;
        }
        if(isValid(this.x, this.y+1) && tiles[this.x][this.y+1].getPiece() == null){
            validSquares[i][0] = this.x; validSquares[i][1] = this.y+1;
            i++;
        } else if(isValid(this.x, this.y+1) && tiles[this.x][this.y+1].getPiece() != null){
            if(tiles[this.x][this.y+1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x; validSquares[i][1] = this.y+1;
                i++;
            }
        }  
        if(isValid(this.x+1, this.y+1) && tiles[this.x+1][this.y+1].getPiece() == null){
            validSquares[i][0] = this.x+1; validSquares[i][1] = this.y+1;
            i++;
        } else if(isValid(this.x+1, this.y+1) && tiles[this.x+1][this.y+1].getPiece() != null){
            if(tiles[this.x+1][this.y+1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x+1; validSquares[i][1] = this.y+1;
                i++;
            }
        }
        if(isValid(this.x-1, this.y+1) && tiles[this.x-1][this.y+1].getPiece() == null){
            validSquares[i][0] = this.x-1; validSquares[i][1] = this.y+1;
            i++;
        } else if(isValid(this.x-1, this.y+1) && tiles[this.x-1][this.y+1].getPiece() != null){
            if(tiles[this.x-1][this.y+1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x-1; validSquares[i][1] = this.y+1;
                i++;
            }
        } 
        if(isValid(this.x, this.y-1) && tiles[this.x][this.y-1].getPiece() == null){
            validSquares[i][0] = this.x; validSquares[i][1] = this.y-1;
            i++;
        } else if(isValid(this.x, this.y-1) && tiles[this.x][this.y-1].getPiece() != null){
            if(tiles[this.x][this.y-1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x; validSquares[i][1] = this.y-1;
                i++;
            }
        }
        if(isValid(this.x+1, this.y-1) && tiles[this.x+1][this.y-1].getPiece() == null){
            validSquares[i][0] = this.x+1; validSquares[i][1] = this.y-1;
            i++;
        } else if(isValid(this.x+1, this.y-1) && tiles[this.x+1][this.y-1].getPiece() != null){
            if(tiles[this.x+1][this.y-1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x+1; validSquares[i][1] = this.y-1;
                i++;
            }
        }
        if(isValid(this.x-1, this.y-1) && tiles[this.x-1][this.y-1].getPiece() == null){
            validSquares[i][0] = this.x-1; validSquares[i][1] = this.y-1;
            i++;
        } else if(isValid(this.x-1, this.y-1) && tiles[this.x-1][this.y-1].getPiece() != null){
            if(tiles[this.x-1][this.y-1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x-1; validSquares[i][1] = this.y-1;
                i++;
            }
        }
        if(isValid(this.x+1, this.y) && tiles[this.x+1][this.y].getPiece() == null){
            validSquares[i][0] = this.x+1; validSquares[i][1] = this.y;
            i++;
        } else if(isValid(this.x+1, this.y) && tiles[this.x+1][this.y].getPiece() != null){
            if(tiles[this.x+1][this.y].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x+1; validSquares[i][1] = this.y;
                i++;
            }
        }
        if(isValid(this.x-1, this.y) && tiles[this.x-1][this.y].getPiece() == null){
            validSquares[i][0] = this.x-1; validSquares[i][1] = this.y;
            i++;
        } else if(isValid(this.x-1, this.y) && tiles[this.x-1][this.y].getPiece() != null){
            if(tiles[this.x-1][this.y].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x-1; validSquares[i][1] = this.y;
                i++;
            }
        }
        return validSquares;
    } 

    @Override
    public String getName(){
        return "king";
    }
}