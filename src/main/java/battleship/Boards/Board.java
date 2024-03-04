package battleship.Boards;

import java.util.Random;
import battleship.CustomArray.ListArray;
import battleship.Database.DB;
import battleship.Ships.EmptyShip;
import battleship.Ships.Ship;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    private int rows;                                           // Number of rows on the grid
    private int columns;                                        // Number of columns on the grid
    private int size;                                           // Size of pane / number of rows (or columns)
    
    private ListArray<Rectangle> cells;                         // 2D ArrayList of Rectangles
    private ListArray<Ship> ships;                              // 2D ArrayList of Ships
    private ListArray<Boolean> attemptedShots;                  // Stores each attempted shot location
    private int sinks;


    /**
     * 
     * @param rows
     * @param columns
     * @param size
     * 
     * Constructor
     * Initializes arrays
     * Fills the board with empty cells
     */
    public Board(int rows, int columns, int size){

        this.rows = rows;
        this.columns = columns;
        this.size = size / rows;

        cells = new ListArray<Rectangle>(rows, columns, new Rectangle());
        ships = new ListArray<Ship>(rows, columns, new EmptyShip());
        attemptedShots = new ListArray<Boolean>(rows, columns, false);

        makeBoard();

    }

    /**
     * 
     * @param pane
     * @param size
     * 
     * Adds rectangles to the pane
     */
    public void showBoard(Pane pane, int size){

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                pane.getChildren().addAll(cells.get(i, j));
            }
        }
    }

    /**
     * 
     * @param row
     * @param column
     * @param inSize
     * 
     * Reusing board created in different scenes
     * This is to resize the cells and ensure they fit on the new grid
     */
    public void updateCell(int row, int column, int inSize){

        cells.get(row, column).setX(row * inSize);
        cells.get(row, column).setY(column * inSize);
        cells.get(row, column).setWidth(inSize);
        cells.get(row, column).setHeight(inSize);
    
       
    }

    /**
     * Initializes the cells array with empty cells
     */
    private void makeBoard()
    {
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                cells.add(new Cell(row, col, size), row, col);
            }
        }

    }

    // public void update(Pane pane, int row, int column){
    //     pane.getChildren().remove(cells.get(row, column));
    //     pane.getChildren().addAll(cells.get(row, column));
    // }

    /**
     * 
     * @param row
     * @param column
     * @param color
     * 
     * Updates the color of the rectangle
     * Used in processing hits and misses
     */
    public void setFill(int row, int column, Color color){
        cells.get(row, column).setFill(color);
    }

    /**
     * 
     * @param row
     * @param column
     * @return
     * 
     * Checks if a shit being placed if inside the board,
     * and 'EmptyShip' which represents open water
     */
    public boolean taken(int row, int column){

        int size = rows-1;

        if(row > size || column > size || row < 0 || column < 0){
            return false;
        }

        if(ships.get(row, column) instanceof EmptyShip){
            return false;
        }

        return true;
 
    }

    /**
     * 
     * @param shipTypes
     * 
     * Randomly place ships
     * Places the proper number of ships according to the ship frequencies
     * For each ship to place, pick a random row / column
     * 
     * If it's possible to place at this location,
     * place the ship.
     * 
     * Method continues until the ships have all been placed
     * 
     */
    public void placeRandom(Ship[] shipTypes){

        int start = 0;
        int end = 5;

        Random random;

        for(int frequency = 0; frequency < DB.ShipFrequency.getFrequency(); frequency++)
        {
            for(int i = start; i < end; i++){
                Ship ship = shipTypes[i];

                while(true){
    
                    random = new Random();
                    int row = random.nextInt(rows);
                    int col = random.nextInt(columns);
                    boolean vertical = random.nextBoolean();
    
                    if(ship.canPlaceAt(this, vertical, row, col)){
                        ship.placeShip(this, row, col, vertical);
                        break;
                    }
    
                }
            }

            start += 5;
            end += 5;

        }
        
    }

    /**
     * 
     * @param row
     * @param column
     * @return
     * 
     * Checks if ship has been shot.
     * If shot, updates the hitcount
     */
    public boolean shot(int row, int column){

        attemptedShots.add(true, row, column);  

        if(ships.get(row, column) instanceof EmptyShip){
            return false;
        }
        ships.get(row, column).updateHitCount();
        return true;
    }

    /**
     * 
     * @param row
     * @param column
     * @return
     * 
     * Keeps track of the row / column of each shot
     * Ensures each shot is at a new location
     */
    public boolean attemped(int row, int column){
        return attemptedShots.get(row, column);
    }

    /**
     * 
     * @param other Hidden Board
     * @param row
     * @param column
     * 
     * Updates cells to display a hit
     */
    public void processHit(Board other, int row, int column){

        cells.get(row, column).setFill(Color.BLACK);
        other.get(row, column).setFill(Color.BLACK);

        if(ships.get(row, column).shipSunk()){
            processSink(other, row, column);
        }        
    }

    /**
     * 
     * @param row
     * @param column
     * 
     * Updates cells to display a miss
     */
    public void processMiss(int row, int column){
        cells.get(row, column).setFill(Color.GRAY);
    }

    /**
     * 
     * @param other
     * @param row
     * @param column
     * 
     * Processes the sinking of a ship
     * Outlines the ship in red
     */
    private void processSink(Board other, int row, int column){

        Ship ship = ships.get(row, column);
        int startRow = ship.getRow();
        int startCol = ship.getColumn();
        boolean vertical = ship.isVertical();

        if(vertical){
            for(int i = startRow; i < startRow + ship.getSize(); i++){
                cells.get(i, column).setStroke(Color.RED);
                other.get(i, column).setStroke(Color.RED);
            }
        }else{
            for(int i = startCol; i < startCol + ship.getSize(); i++){
                cells.get(row, i).setStroke(Color.RED);
                other.get(row, i).setStroke(Color.RED);
            }
        }

        shipSunkDialog(ship.getName());
        sinks++;
    }

    /**
     * 
     * @param name Name of ship
     * 
     * Displays a popup that a ship sunk
     */
    private void shipSunkDialog(String name){

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("You Sunk A Ship!");
        alert.setHeaderText(name + " has been sunk!");
        ButtonType yes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(yes);
        alert.showAndWait().ifPresent(type ->{
            if(type == yes);
        });
    }


     // Getters
    //===================================================================================================================

    public boolean gameOver(){
        return sinks == 5;
    }

    public ListArray<Rectangle> getCells(){
        return this.cells;
    }

    public Rectangle get(int row, int column){
        return cells.get(row, column);
    }

    public int getRows(){
        return this.rows;
    }

    public int getColumns(){
        return this.columns;
    }

    public Rectangle getCell(int row, int column){
        return cells.get(row, column);
    }

    public Ship getShip(int row, int column){
        return ships.get(row, column);
    }

    public ListArray<Ship> getShips(){
        return this.ships;
    }
}
