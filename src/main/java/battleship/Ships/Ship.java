package battleship.Ships;

import battleship.Boards.Board;
import battleship.CustomArray.ListArray;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Ship {

    int startRow;
    int startColumn;
    boolean vertical;

    public abstract int getSize();
    public abstract String getID();
    public abstract String getName();
    public abstract int getHitCount();
    public abstract void updateHitCount();
    public abstract Image[] getImages();
    public abstract Image[] getRotatedImages();
    

    public void setRow(int startRow){
        this.startRow = startRow;
    }

    public int getRow(){
        return this.startRow;
    }

    public void setColumn(int startColumn){
        this.startColumn = startColumn;
    }

    public int getColumn(){
        return this.startColumn;
    }

    public void setVertical(boolean vertical){
        this.vertical = vertical;
    }

    public boolean isVertical(){
        return this.vertical;
    }

    public void placeShip(Board board, int row, int column, boolean vertical){

        setRow(row);
        setColumn(column);
        setVertical(vertical);

        ListArray<Ship> ships = board.getShips();
        ListArray<Rectangle> cells = board.getCells();

        if(vertical){
            placeVertical(ships, cells, row, column);
        }else{
            placeHorizontal(ships, cells, row, column);
        }

    }

    private void placeVertical(ListArray<Ship> ships, ListArray<Rectangle> cells, int row, int column){

        Image[] images = this.getImages();
        int count = 0;
        for(int r = row; r < row + getSize(); r++){
            cells.get(r, column).setFill(new ImagePattern(images[count]));
            cells.get(r, column).setId(getID());
            ships.add(this, r, column);
            count++;
        }
    }

    private void placeHorizontal(ListArray<Ship> ships, ListArray<Rectangle> cells, int row, int column){

        Image[] images = this.getRotatedImages();
        int count = 0;
        for(int c = column; c < column + getSize(); c++){
            cells.get(row, c).setFill(new ImagePattern(images[count]));
            cells.get(row, c).setId(getID());
            ships.add(this, row, c);
            count++;
        }
    }

    public boolean canPlaceAt(Board board, boolean vertical, int row, int column) {

        if(vertical){
            if(row + getSize() > 10){
                return false;
            }

            for(int i = row - 1; i <= row + getSize(); i++){
                if(verticalOccupied(row, column, i, board)){
                    return false;
                }
            }

            return true;

        }else{

            if(column + getSize() > 10){
                return false;
            }

            for(int i = column - 1; i <= column + getSize(); i++){
                if(horizontalOccupied(row, column, i, board)){
                    return false;
                }
            }

            return true;
        }

    }

    public boolean verticalOccupied(int row, int column, int i, Board board){

        if(row + getSize() > 10){
            return false;
        }
        
        return board.taken(i, column - 1) || board.taken(i, column) || board.taken(i, column + 1);
    }

    public boolean horizontalOccupied(int row, int column, int i, Board board){
        return board.taken(row-1, i) || board.taken(row, i) || board.taken(row+1, i);
    }

    public boolean shipSunk(){

        if(this.getHitCount() < this.getSize()){
            return false;
        }
        return true;
    }
   
}
