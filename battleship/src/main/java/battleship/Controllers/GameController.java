package battleship.Controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import battleship.Boards.Board;
import battleship.Database.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GameController implements Initializable {

    @FXML private AnchorPane gameAnchor;
    @FXML private Pane computerBoard;
    @FXML private Pane computerHidden;
    @FXML private Button computerTurn;
    @FXML private Pane humanBoard;
    @FXML private Pane humanHidden;
    @FXML private Pane numbers;
    @FXML private Pane numbers2;
    @FXML private Pane numbers3;
    @FXML private Pane numbers4;
    @FXML private Pane letters;
    @FXML private Pane letters2;
    @FXML private Pane letters3;
    @FXML private Pane letters4;


    private Board human;                                // Human Board
    private Board computer;                             // Computer Board
    private Board hiddenH;                              // Board opponent sees. Updates alongside normal board
    private Board hiddenC;                              // Board opponent sees. Updates alongside normal board

    private int rows;
    private int columns;
    private int cellSize;
    private final int PANE_SIZE = 300;                  // Size of the pane that rectangles sit on


    /**
     * 
     * @param event
     * 
     * Gets current row / column selected by the user
     * Attemps shot at the forementioned position
     * 
     * Updates both the players board and the board the opponent sees
     */
    @FXML void getClickPosition(MouseEvent event) {

        int row     = (int) event.getX() / cellSize;
        int column  = (int) event.getY() / cellSize;
        
        int success = computer.shot(row, column) ? 0 : 1;

        switch(success){
            case 0 : computer.processHit(hiddenC, row, column); break;

            case 1 : computer.processMiss(row, column); 
                     hiddenC.processMiss(row, column); 
                     break;
        }

        if(computer.gameOver()){
            winnerDialog("human");
        }

        computerTurn();

    }

    /**
     * Generate random row / column to shoot at players ship
     * Keep trying until reached an unattempted cell
     * Process a hit or miss
     * Check if reached end of game
     */
    private void computerTurn(){

        Random random;
        int row;
        int column;
        
        while(true){

            random = new Random();
            row = random.nextInt(rows);
            column = random.nextInt(columns);

            if(!human.attemped(row, column)){
                break;
            }
        }

        int success = human.shot(row, column) ? 0 : 1;

        switch(success){
            case 0 : human.processHit(hiddenH, row, column); break;

            case 1 : human.processMiss(row, column);
                     hiddenH.processMiss(row, column); 
                     break;
        }

        if(human.gameOver()){
            winnerDialog("computer");
        }

    }


    /**
     * 
     * @param winner
     * 
     * Displays end of game dialog
     * Asks user if they would like to play again
     */
    private void winnerDialog(String winner){
        DB.Winner.display(winner);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        rows = DB.Boards.size;
        columns = DB.Boards.size;
        cellSize = PANE_SIZE / rows;


        setComputerBoard();
        setHiddenComputer();
        setHumanBoards();
        setHiddenHuman();
       
        addNumbers(numbers);
        addNumbers(numbers2);
        addNumbers(numbers3);
        addNumbers(numbers4);

        addLetters(letters);
        addLetters(letters2);
        addLetters(letters3);
        addLetters(letters4);
    
    }


    private void setHumanBoards(){
        human = DB.Human.getBoard();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                human.updateCell(i, j, cellSize);
            }
        }
    
        human.showBoard(humanBoard, cellSize);
    }

    private void setHiddenHuman(){
        hiddenH = new Board(rows, columns, PANE_SIZE);
        hiddenH.showBoard(humanHidden, cellSize);
    }

    private void setComputerBoard(){
        computer = DB.Computer.getBoard();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                computer.updateCell(i, j, cellSize);
            }
        }
        computer.showBoard(computerBoard, cellSize);
      
    }
    
    private void setHiddenComputer(){
        hiddenC = new Board(rows, columns, PANE_SIZE);
        hiddenC.showBoard(computerHidden, cellSize);
    }

    private void addNumbers(Pane pane){

        switch(human.getRows()){
            case 10 : makeNumbers(pane, 10); break;
            case 12 : makeNumbers(pane, 12); break;
            case 15 : makeNumbers(pane, 15); break;
        }
    }

    private void addLetters(Pane pane){

        switch(human.getRows()){
            case 10 : makeLetters(pane, 10); break;
            case 12 : makeLetters(pane, 12); break;
            case 15 : makeLetters(pane, 15); break;
        }
    }

    private void makeNumbers(Pane pane, int amount){

        Button button;
        int width = PANE_SIZE / human.getRows();

        for(int i = 0; i < amount; i++){
            button = new Button();
            button.setPrefWidth(width);
            button.setPrefHeight(30);
            button.setText(String.valueOf(i + 1));
            button.setTextFill(Color.BLACK);
            button.setStyle("-fx-background-color: rgb(171,176,178);");
            button.setLayoutX(i * width);
            button.setDisable(true);
            pane.getChildren().add(button);
        }
    }

    private void makeLetters(Pane pane, int amount){

        Button button;
        int width = PANE_SIZE / human.getRows();
        String[] ltrs = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};

        for(int i = 0; i < amount; i++){
            button = new Button();
            button.setPrefWidth(30);
            button.setPrefHeight(width);
            button.setText(ltrs[i]);
            button.setTextFill(Color.BLACK);
            button.setStyle("-fx-background-color: rgb(171,176,178);");
            button.setLayoutY(i * width);
            button.setDisable(true);
            pane.getChildren().addAll(button);
        }
    }

}