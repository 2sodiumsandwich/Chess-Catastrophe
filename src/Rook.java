/**
 * Rook piece for java
 */
public class Rook extends Piece{

    public Rook(int x, int y, boolean color){
        super(x, y, color);
    }

    @Override
    public int[][] ValidSquares(Tile[][] tiles){
        int[][] validSquares = new int[14][2];
        int i = 0;
            for(int t = 0; t<validSquares.length; t++){
                validSquares[t][0] = -1; validSquares[t][1] = -1;
            }
            while(true){
                for(int x=1; x<8; x++){
                    if(isValid(this.x, this.y+x) && tiles[this.x][this.y+x].getPiece() == null){
                        validSquares[i][0] = this.x; validSquares[i][1] = this.y+x;
                        i++;
                    } else if(isValid(this.x, this.y+x) && tiles[this.x][this.y+x].getPiece() != null){
                        if(tiles[this.x][this.y+x].getPiece().getColor() != this.getColor()){
                            validSquares[i][0] = this.x; validSquares[i][1] = this.y+x;
                            i++;
                        }
                        break;
                    } else {
                        break;
                    }
                }
                for(int x=1; x<8; x++){
                    if(isValid(this.x, this.y-x) && tiles[this.x][this.y-x].getPiece() == null){
                        validSquares[i][0] = this.x; validSquares[i][1] = this.y-x;
                        i++;
                    } else if(isValid(this.x, this.y-x) && tiles[this.x][this.y-x].getPiece() != null){
                        if(tiles[this.x][this.y-x].getPiece().getColor() != this.getColor()){
                            validSquares[i][0] = this.x; validSquares[i][1] = this.y-x;
                            i++;
                        }
                        break;
                    } else {
                        break;
                    }
                }
                for(int x=1; x<8; x++){
                    if(isValid(this.x+x, this.y) && tiles[this.x+x][this.y].getPiece() == null){
                        validSquares[i][0] = this.x+x; validSquares[i][1] = this.y;
                        i++;
                    } else if(isValid(this.x+x, this.y) && tiles[this.x+x][this.y].getPiece() != null){
                        if(tiles[this.x+x][this.y].getPiece().getColor() != this.getColor()){
                            validSquares[i][0] = this.x+x; validSquares[i][1] = this.y;
                            i++;
                        }
                        break;
                    } else {
                        break;
                    }
                }
                for(int x=1; x<8; x++){
                    if(isValid(this.x-x, this.y) && tiles[this.x-x][this.y].getPiece() == null){
                        validSquares[i][0] = this.x-x; validSquares[i][1] = this.y;
                        i++;
                    } else if(isValid(this.x-x, this.y) && tiles[this.x-x][this.y].getPiece() != null){
                        if(tiles[this.x-x][this.y].getPiece().getColor() != this.getColor()){
                            validSquares[i][0] = this.x-x; validSquares[i][1] = this.y;
                            i++;
                        }
                        break;
                    } else {
                        break;
                    }
                }
                break;
            }
        return validSquares;
    }

    @Override
    public String getAbbr(){
        return "R";
    }

    @Override
    public String getName(){
        return "rook";
    }
}