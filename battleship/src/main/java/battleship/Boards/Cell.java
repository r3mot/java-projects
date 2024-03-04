package battleship.Boards;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {


    /**
     * 
     * @param row
     * @param col
     * @param size Size of Pane / Number of rows & columns
     * 
     * Formats each cell (rectangle) to fit perfectly on a pane
     */
    public Cell(int row, int col, int size){

        setX(row * size);
        setY(col * size);
        setWidth(size);
        setHeight(size);
        setFill(Color.LIGHTBLUE);
        setStroke(Color.BLACK);
        setId("empty");

    }


}
