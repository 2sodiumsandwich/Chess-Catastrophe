/**
 * Chess main gui
 * Kevin - CJ
 * 
 * uses java swing
 */


//Import classes
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import java.util.List;

/**
 * Constructer for main GUI
 * Each tile is a JButton 
 */
public class GameGui extends JFrame{
    private static JButton[][] buttonBoard = new JButton[8][8]; //2d array of buttons
    private int tileW = 100; int tileH = 100; //Tile dimensions in px
    private static Tile[][] tiles = new Tile[8][8]; //2d array of tiles
    private static int[][] coordbox = {{-2,-2},{-2,-2},{-2,-2},{-2,-2}}; //2d array of -2. This stores the previous coordinates of the indicator tiles so they can be erased

    private int[] previous = {-1,-1}; //previous tile clicked, for movement
    private int[] current = {-1,-1}; //current tile clicked for consistancy

    private List<String> moveSet = new ArrayList<>(); //turn saving, caps at 16
    

    boolean turn = false;
    boolean gameOver = false;
    
    /**
     * Load graphics for buttons
     * -i means there is an indicator on that square
     * 
     * scaleImage is a method written below that scales the image to button size
     */
    
        //Images for empty backdrop
        ImageIcon dark = scaleImage(new ImageIcon("resources/dark.png"));
        ImageIcon light = scaleImage(new ImageIcon("resources/light.png"));
        ImageIcon darki = scaleImage(new ImageIcon("resources/dark-i.png"));
        ImageIcon lighti = scaleImage(new ImageIcon("resources/light-i.png"));

        //Map of images for pieces
        //HashMap filled below in createMap()
        private final Map<String, ImageIcon> texMap = new HashMap<>();
        private final Map<String, String> horiz = new HashMap<>();
        private final Map<String, String> verti = new HashMap<>(); 

        //initiate turnC as an instance variable in order for it to be accessed in methods
        private JLabel turnC;
        private JLabel moveC;
        
    /**
     * End image loading
     */


    /**
     * Chess Gui
     * No params
     */
    public GameGui(){ 
        super("Chess Catastrophe"); //calls the superclass constructer (Jframe constructer)
        createMaps(); //asformentioned, filling hashmap of unit textures
        setBackground(new Color(153,102,51));
        //double forloop to fill buttonBoard with jbuttons
        for(int x = 0; x<buttonBoard.length; x++){
            for(int y = 0; y<buttonBoard.length; y++){
                buttonBoard[x][y] = new JButton(); //construct button
                buttonBoard[x][y].setBounds(tileW*x, tileH*y, tileW, tileH); //set dimensions and coords of button on jframe
                add(buttonBoard[x][y]); //adds jbutton to jframe
                
                buttonBoard[x][y].addActionListener(new ActionListener() { //ActionListener() - triggers an event when clicked
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < buttonBoard.length; i++){ //cannot use variables that are not final in lambda, must use for loop to retrieve coordinates of buttons
                            for (int j = 0; j < buttonBoard.length; j++) {
                                if(e.getSource()==buttonBoard[i][j]){ //gameButtons[i][j] was clicked
                                    buttonPressed(i, j); //calls buttonPressed, inserting the coordinates of the button pressed
                                }
                            }
                        }
                    }
                });

                texNull(x,y); //calls texNull method below (puts a graphic on the tile)

                buttonBoard[x][y].setMargin(new Insets(0, 0, 0, 0)); //insets bad
                buttonBoard[x][y].setBorder(null); //borderbad
            }    
        }

        //new game button
        JButton newgame = new JButton("New Game");
        newgame.setBounds(800, 700, 250, 100);
        add(newgame);
        newgame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setUp(); //calls setup again
            }
        });
        newgame.setFont(new Font("Roboto", Font.PLAIN, 40)); //makes font not tiny
        newgame.setBorder(null); // border bad
        newgame.setFocusPainted(false);// no more ugly focus box around "New Game" when clicked
        newgame.setOpaque(true); //in order for color to show for some reason
        newgame.setBackground(new Color(153,102,51)); //color of newgame to make it not completely 
        
        turnC = new JLabel("White's Turn", JLabel.CENTER);
        turnC.setFont(new Font("Roboto", Font.PLAIN, 40));
        turnC.setBounds(800, 0, 250, 100);
        add(turnC);
        turnC.setOpaque(true);
        turnC.setBackground(new Color(153,102,51));

        moveC = new JLabel();
        moveC.setFont(new Font("Roboto", Font.PLAIN, 31));
        moveC.setBounds(800, 100, 250, 600);
        add(moveC);
        moveC.setOpaque(true);
        moveC.setBackground(new Color(255,204,153));
        moveC.setHorizontalAlignment(JLabel.CENTER);
        moveC.setVerticalAlignment(JLabel.TOP);

        setSize(8 * tileW + 250, 8 * tileH); //size of JFrame to fit tile buttons + ui on the right
        setLayout(null); //no layout cause I started w/o one and I'm too lazy to change everything
        setVisible(true); //sets jframe to visible, idk why this isn't true by default
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUp(); //initial setup
    }

    /**
     * checkBackground checks current background if it is dark or light
     * 
     * @param int x - x coordinate of tile checked
     * @param int y - y coordinate of tile checked
     * 
     * @return true if dark, false if light
     */
    public boolean checkBackground(int x, int y){
        return((x % 2 == 1) && (y % 2 == 1)) || ((x % 2 == 0) && (y % 2 == 0));
    }

    /**
     * event for a tile being pressed
     * 
     * @param int x - x coordinate of tile checked
     * @param int y - y coordinate of tile checked
     */
    public void buttonPressed(int x, int y){
        clearIndicators(coordbox); //clears previous indicators

        if(current[0] != -1) previous[0] = current[0]; previous[1] = current[1];
        current[0] = x; current[1] = y;
        //checks if the tile clicked has a piece on it, if the game is over or not, or if the piece clicked is the same as the previous piece
        if(previous[0] != -1 && tiles[previous[0]][previous[1]].getPiece() != null && (previous[0] != x || previous[1] != y && gameOver == false)){
            for(int i = 0; i<tiles[previous[0]][previous[1]].getPiece().ValidSquares(tiles).length; i++){ //loops through valid squares of previous tiles
                if(x==tiles[previous[0]][previous[1]].getPiece().ValidSquares(tiles)[i][0] && y==tiles[previous[0]][previous[1]].getPiece().ValidSquares(tiles)[i][1] && tiles[previous[0]][previous[1]].getPiece().getColor() == turn){
                    if(tiles[previous[0]][previous[1]].getPiece().getColor()){ //if desired move space is in validsquares, move the tile
                        texChange(x, y, tiles[previous[0]][previous[1]].getPiece().getName(), "b", false); //set textures
                        if(tiles[x][y].getPiece() != null && tiles[x][y].getPiece().getName() == "king") gameOver = true; //check if king has been captured, end the game if so
                        if(tiles[previous[0]][previous[1]].getPiece().getName() == "pawn" && y == 7){
                            texChange(x, y, "queen", "b", false); tiles[x][y] = new Tile(new Queen(x, y, true),x,y);
                        } else {
                            tiles[x][y].setPiece(tiles[previous[0]][previous[1]].getPiece());
                        }
                        turn = !turn;
                    } else {
                        texChange(x, y, tiles[previous[0]][previous[1]].getPiece().getName(), "w", false);
                        if(tiles[x][y].getPiece() != null && tiles[x][y].getPiece().getName() == "king") gameOver = true;
                        if(tiles[previous[0]][previous[1]].getPiece().getName() == "pawn" && y == 0){
                            texChange(x, y, "queen", "w", false); tiles[x][y] = new Tile(new Queen(x, y, false),x,y);
                        } else {
                            tiles[x][y].setPiece(tiles[previous[0]][previous[1]].getPiece());
                        }
                        turn = !turn;
                    }
                    
                    moveSet.add(0, horiz.get(String.valueOf(previous[0])) + verti.get(String.valueOf(previous[1])) + " -> " + horiz.get(String.valueOf(x)) + verti.get(String.valueOf(y)));
                    if(moveSet.size() > 16){
                        moveSet.remove(moveSet.size() - 1);
                    }
                    updateMoveset();

                    tiles[previous[0]][previous[1]].setPiece(null); //previous space to null
                    texNull(previous[0], previous[1]); //previous space texture to default square
                    tiles[x][y].getPiece().setX(x);tiles[x][y].getPiece().setY(y); //set piece x y to new x y

                    if(turn==true){ //set turn counters
                        turnC.setText("Black's Turn");
                        turnC.setForeground(new Color(0,0,0));
                    } else {
                        turnC.setText("White's Turn");
                        turnC.setForeground(new Color(255,255,255));
                    }

                    current[0] = -1; current[1] = -1;
                    previous[0] = -1; previous[1] = -1; //does not show indicators after moving
                    break;
                }
            }      
        }
        

        if(gameOver == true){ //check win condition
            if(turn == true){
                turnC.setText("White wins");
                turnC.setForeground(new Color(255,255,255));
            } else {
                turnC.setText("Black wins");
                turnC.setForeground(new Color(0,0,0));
            }
        }

        if(tiles[x][y].getPiece() != null && current[0] != -1  && tiles[x][y].getPiece().getColor() == turn && gameOver == false){
            MoveIndicators(tiles[x][y].getPiece().ValidSquares(tiles)); //show moveindicators on click
        }
    }

    public void updateMoveset(){
        String mvs = "";
        for(int i=0; i<moveSet.size(); i++){
            mvs = mvs + moveSet.get(i) + "<br/>";
        }
        moveC.setText("<html>" + mvs + "</html>");
    }

    public ImageIcon scaleImage(ImageIcon x){
        Image i = x.getImage(); //scales image to button size
        return new ImageIcon(i.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
    }

    /**
     * Texnull sets the tile requested to default dark/light board textures
     * 
     * @param String piece ids - pawn bishop knight rook king queen
     * @param String color ids - w b
     */
    public void texNull(int x, int y){
        if(checkBackground(x, y)){
            buttonBoard[x][y].setIcon(light);
        } else {
            buttonBoard[x][y].setIcon(dark);
        }
    }

    public void texChange(int x, int y, String piece, String color, boolean attack){
        String c;
        if(checkBackground(x,y)) c = String.valueOf('l'); else c = String.valueOf('d');
            if(attack){
                buttonBoard[x][y].setIcon(scaleImage(texMap.get(c + color + piece + "i")));
            } else {
                buttonBoard[x][y].setIcon(scaleImage(texMap.get(c + color + piece)));
            }
    }

    //map of pawn textures
    public void createMaps(){
        //pawns
        texMap.put("dbpawn", new ImageIcon("resources/pawn/black-dark.png"));
        texMap.put("lbpawn", new ImageIcon("resources/pawn/black-light.png"));
        texMap.put("dwpawn", new ImageIcon("resources/pawn/white-dark.png"));
        texMap.put("lwpawn", new ImageIcon("resources/pawn/white-light.png"));

        texMap.put("dbpawni", new ImageIcon("resources/pawn/black-dark-i.png"));
        texMap.put("lbpawni", new ImageIcon("resources/pawn/black-light-i.png"));
        texMap.put("dwpawni", new ImageIcon("resources/pawn/white-dark-i.png"));
        texMap.put("lwpawni", new ImageIcon("resources/pawn/white-light-i.png"));

        //bishops
        texMap.put("dbbishop", new ImageIcon("resources/bishop/black-dark.png"));
        texMap.put("lbbishop", new ImageIcon("resources/bishop/black-light.png"));
        texMap.put("dwbishop", new ImageIcon("resources/bishop/white-dark.png"));
        texMap.put("lwbishop", new ImageIcon("resources/bishop/white-light.png"));

        texMap.put("dbbishopi", new ImageIcon("resources/bishop/black-dark-i.png"));
        texMap.put("lbbishopi", new ImageIcon("resources/bishop/black-light-i.png"));
        texMap.put("dwbishopi", new ImageIcon("resources/bishop/white-dark-i.png"));
        texMap.put("lwbishopi", new ImageIcon("resources/bishop/white-light-i.png"));

        //knights
        

        //rooks
        texMap.put("dbrook", new ImageIcon("resources/rook/black-dark.png"));
        texMap.put("lbrook", new ImageIcon("resources/rook/black-light.png"));
        texMap.put("dwrook", new ImageIcon("resources/rook/white-dark.png"));
        texMap.put("lwrook", new ImageIcon("resources/rook/white-light.png"));

        texMap.put("dbrooki", new ImageIcon("resources/rook/black-dark-i.png"));
        texMap.put("lbrooki", new ImageIcon("resources/rook/black-light-i.png"));
        texMap.put("dwrooki", new ImageIcon("resources/rook/white-dark-i.png"));
        texMap.put("lwrooki", new ImageIcon("resources/rook/white-light-i.png"));

        //queens
        texMap.put("dbqueen", new ImageIcon("resources/queen/black-dark.png"));
        texMap.put("lbqueen", new ImageIcon("resources/queen/black-light.png"));
        texMap.put("dwqueen", new ImageIcon("resources/queen/white-dark.png"));
        texMap.put("lwqueen", new ImageIcon("resources/queen/white-light.png"));

        texMap.put("dbqueeni", new ImageIcon("resources/queen/black-dark-i.png"));
        texMap.put("lbqueeni", new ImageIcon("resources/queen/black-light-i.png"));
        texMap.put("dwqueeni", new ImageIcon("resources/queen/white-dark-i.png"));
        texMap.put("lwqueeni", new ImageIcon("resources/queen/white-light-i.png"));

        //kings
        texMap.put("dbking", new ImageIcon("resources/king/black-dark.png"));
        texMap.put("lbking", new ImageIcon("resources/king/black-light.png"));
        texMap.put("dwking", new ImageIcon("resources/king/white-dark.png"));
        texMap.put("lwking", new ImageIcon("resources/king/white-light.png"));

        texMap.put("dbkingi", new ImageIcon("resources/king/black-dark-i.png"));
        texMap.put("lbkingi", new ImageIcon("resources/king/black-light-i.png"));
        texMap.put("dwkingi", new ImageIcon("resources/king/white-dark-i.png"));
        texMap.put("lwkingi", new ImageIcon("resources/king/white-light-i.png"));

        //Load coordinate maps
        horiz.put("0", "a");horiz.put("1", "b");horiz.put("2", "c");horiz.put("3", "d");
        horiz.put("4", "e");horiz.put("5", "f");horiz.put("6", "g");horiz.put("7", "h");
        
        verti.put("0", "8");verti.put("1", "7");verti.put("2", "6");verti.put("3", "5");
        verti.put("4", "4");verti.put("5", "3");verti.put("6", "2");verti.put("7", "1");
    }
    
    //clear all moveindicators, basically the opposite of moveindicators
    public void clearIndicators(int[][] coords){
        if(coordbox[0][0] > -2){
            for(int x=0; x<coordbox.length; x++){
                if(coordbox[x][0] != -1 && tiles[coordbox[x][0]][coordbox[x][1]].getPiece()==null){
                    if(checkBackground(coordbox[x][0], coordbox[x][1])){
                        buttonBoard[coordbox[x][0]][coordbox[x][1]].setIcon(light);
                    } else {
                        buttonBoard[coordbox[x][0]][coordbox[x][1]].setIcon(dark);
                    }
                }  else if(coords[x][0] != -1){
                    if(tiles[coords[x][0]][coords[x][1]].getPiece().getColor()){
                        texChange(coords[x][0], coords[x][1], tiles[coords[x][0]][coords[x][1]].getPiece().getName(), "b", false);
                    } else {
                        texChange(coords[x][0], coords[x][1], tiles[coords[x][0]][coords[x][1]].getPiece().getName(), "w", false);
                    }
                }
            }
        }
    }

    //loops through an objects validSquares[][] and changes textures
    public void MoveIndicators(int[][] coords){
        coordbox = coords;
        for(int x=0; x<coords.length; x++){
            if(coords[x][0] != -1 && tiles[coords[x][0]][coords[x][1]].getPiece()==null){
                if(checkBackground(coords[x][0], coords[x][1])){
                    buttonBoard[coords[x][0]][coords[x][1]].setIcon(lighti);
                } else {
                    buttonBoard[coords[x][0]][coords[x][1]].setIcon(darki);
                }
            } else if(coords[x][0] != -1){
                if(tiles[coords[x][0]][coords[x][1]].getPiece().getColor()){ //if there is a piece, change the piece texture to indicator
                    texChange(coords[x][0], coords[x][1], tiles[coords[x][0]][coords[x][1]].getPiece().getName(), "b", true);
                } else {
                    texChange(coords[x][0], coords[x][1], tiles[coords[x][0]][coords[x][1]].getPiece().getName(), "w", true);
                }
            }
        }
    }

    public void setUp(){
        gameOver = false;
        turnC.setText("White's turn"); //default chess game starts with white
        turnC.setForeground(new Color(255,255,255)); //turn color
        for(int x = 0; x < tiles.length; x++){ //reset all tiles
            for(int y = 0; y < tiles.length; y++){
                tiles[x][y] = new Tile(null, x, y);
            }
        }
        for (int i = 0; i < buttonBoard.length; i++){
            for (int j = 0; j < buttonBoard.length; j++) {
                texNull(i,j);
            }
        }

        //Add knights

        //Add bishops
        texChange(2, 0, "bishop", "b", false); tiles[2][0] = new Tile(new Bishop(2, 0, true),2,0);
        texChange(5, 0, "bishop", "b", false); tiles[5][0] = new Tile(new Bishop(5, 0, true),5,7);
        texChange(2, 7, "bishop", "w", false); tiles[2][7] = new Tile(new Bishop(2, 7, false),2,0);
        texChange(5, 7, "bishop", "w", false); tiles[5][7] = new Tile(new Bishop(5, 7, false),5,7);

        //Add rook
        texChange(0, 0, "rook", "b", false); tiles[0][0] = new Tile(new Rook(0, 0, true),0,0);
        texChange(7, 0, "rook", "b", false); tiles[7][0] = new Tile(new Rook(7, 0, true),7,0);
        texChange(0, 7, "rook", "w", false); tiles[0][7] = new Tile(new Rook(0, 7, false),0,7);
        texChange(7, 7, "rook", "w", false); tiles[7][7] = new Tile(new Rook(7, 7, false),7,7);

        //Add pawns
        texChange(0, 1, "pawn", "b", false); tiles[0][1] = new Tile(new Pawn(0, 1, true),0,1);
        texChange(1, 1, "pawn", "b", false); tiles[1][1] = new Tile(new Pawn(1, 1, true),1,1);
        texChange(2, 1, "pawn", "b", false); tiles[2][1] = new Tile(new Pawn(2, 1, true),2,1);
        texChange(3, 1, "pawn", "b", false); tiles[3][1] = new Tile(new Pawn(3, 1, true),3,1);
        texChange(4, 1, "pawn", "b", false); tiles[4][1] = new Tile(new Pawn(4, 1, true),4,1);
        texChange(5, 1, "pawn", "b", false); tiles[5][1] = new Tile(new Pawn(5, 1, true),5,1);
        texChange(6, 1, "pawn", "b", false); tiles[6][1] = new Tile(new Pawn(6, 1, true),6,1);
        texChange(7, 1, "pawn", "b", false); tiles[7][1] = new Tile(new Pawn(7, 1, true),7,1);
        texChange(0, 6, "pawn", "w", false); tiles[0][6] = new Tile(new Pawn(0, 6, false),0,6);
        texChange(1, 6, "pawn", "w", false); tiles[1][6] = new Tile(new Pawn(1, 6, false),1,6);
        texChange(2, 6, "pawn", "w", false); tiles[2][6] = new Tile(new Pawn(2, 6, false),2,6);
        texChange(3, 6, "pawn", "w", false); tiles[3][6] = new Tile(new Pawn(3, 6, false),3,6);
        texChange(4, 6, "pawn", "w", false); tiles[4][6] = new Tile(new Pawn(4, 6, false),4,6);
        texChange(5, 6, "pawn", "w", false); tiles[5][6] = new Tile(new Pawn(5, 6, false),5,6);
        texChange(6, 6, "pawn", "w", false); tiles[6][6] = new Tile(new Pawn(6, 6, false),6,6);
        texChange(7, 6, "pawn", "w", false); tiles[7][6] = new Tile(new Pawn(7, 6, false),7,6);

        //Add queens
        texChange(3, 0, "queen", "b", false); tiles[3][0] = new Tile(new Queen(3, 0, true),3,0);
        texChange(4, 7, "queen", "w", false); tiles[4][7] = new Tile(new Queen(4, 7, false),4,7);

        //Add kings
        texChange(4, 0, "king", "b", false); tiles[4][0] = new Tile(new King(4, 0, true),4,0);
        texChange(3, 7, "king", "w", false); tiles[3][7] = new Tile(new King(3, 7, false),3,7);

    }

    public static void main(String[] args){
        GameGui GUI = new GameGui(); // generate board
    }
}