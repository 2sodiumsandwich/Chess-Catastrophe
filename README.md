# Chess-Catastrophe
CJ-Kevin for APCSA 2018

# Implementing the Knight class


The framework of the knight class has been already copypasta'd into the class file


All you need to do is


* Create Knight textures
* Putting Knight textures into hashmap
* Implementing Knight's validSquares() method
* Creating 4 knight objects on the board

## Creating Knight Textures


You probably already know how to create the different textures for each of the pieces


Simply create a folder called Knight in the resources folder, and add the 8 needed textures


* white-light
* black-light
* white-dark
* black-dark
* white-light-i
* black-light-i
* white-dark-i
* black-dark-i

black or white represents the color of the piece


light or dark represents the background it is standing on


-i represents if there is an indicator on the piece or not




Reference the other piece textures if you need help

## Adding the piece textures to the hashmap


You set the hashmap references in the createMaps() methods in the GameGui class


```java
texMap.put("dbpawn", new ImageIcon("resources/pawn/black-dark.png"));
```
Example of adding pawn to the hashmap





first character represents the background


* d for dark
* l for light
second character represents the color of the piece


* b for black
* w for white
third character represents the piece name


change the PATH of the file as well


```java
texMap.put("lwpawni", new ImageIcon("resources/pawn/white-light-i.png"));
```
For pieces with an indicator, tack an 'i' to the end of the map reference



## Implementing the knights ValidSquares() method


the framework of the ValidSquares method is already there from me copypasting the class skeletons


```java
//validSquares is a 2d array of integers
//validSquares[i][0] is x
//validSquares[i][1] is y
//i represents the indices of each available movement square

int[][] validSquares = new int[8][2];
```


The forloop at the beginning defaults the squares to -1


The function that checks for the validSquares ignores -1 values



```java
if(isValid(this.x, this.y+1) && tiles[this.x][this.y+1].getPiece() == null){
            validSquares[i][0] = this.x; validSquares[i][1] = this.y+1;
            i++;
        } else if(isValid(this.x, this.y+1) && tiles[this.x][this.y+1].getPiece() != null){
            if(tiles[this.x][this.y+1].getPiece().getColor() != this.getColor()){
                validSquares[i][0] = this.x; validSquares[i][1] = this.y+1;
                i++;
            }
        }  
```
example of checking a square


isValid() checks to see if the square is within the gameboard


**This is very important, will get indexOutOfBounds 90% of the time without it


Then it checks if the piece is null or has a piece on it


if it is null, that square is safe


if there is a piece, it must check to see if said pieces color is different from the current piece


## Creating a knight piece on the GameGui

Fairly simple 2 liner


```java
texChange(2, 0, "bishop", "b", false); tiles[2][0] = new Tile(new Bishop(2, 0, true),2,0);
```
This is all you have to do.


* texChange() changes the texture of the button at the knights coords
* then sets the tile at the knight's coordinates' Piece instance variable to a new Knight object


I know that you have to enter the coords 4 times per constructer, but that's just because my code is a complete mess ~~just like my life~~


Remember that arrays start at 0, and 0,0 is at the top left corner


## Pushing the changes to github


After you get it to work and fully test **both sides**, you can push the changes to github




I am not too familiar with the github client, as I use the git command line, but Willson should be able to help


* add all files changed (should be only Knight and GameGui)
* commit files
* push to remote



glhf :) - kev