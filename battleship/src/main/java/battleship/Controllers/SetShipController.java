package battleship.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import battleship.App;
import battleship.Boards.Board;
import battleship.Database.DB;
import battleship.Ships.Battleship;
import battleship.Ships.Carrier;
import battleship.Ships.Destroyer;
import battleship.Ships.PatrolBoat;
import battleship.Ships.Ship;
import battleship.Ships.Submarine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetShipController implements Initializable {

    @FXML private AnchorPane anchor;
    @FXML private Pane humanBoard;
    @FXML private Button randomButton;
    @FXML private Button startButton;
    @FXML private ComboBox<String> comboBox; 

    private final Ship[] shipList = { new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine() };
                                                            
    private ObservableList<String> values;                      // String values shown in the dropdown menu
    private int dropDownIndex;                                  // Index of the dropdown values

    private int[] shipFrequencies;                              // Frequency of each ship type

    private int paneSize = 500;                                 // Size of the Pane (holds rectangles)
    private int width;                                          // Size of each rectangle (used to place)
    private Board board;   
    private Board computer;                                     // Grid where user places ships
    private int numberOfShips;                                  // Total number of ships being placed
    private int gridSize;                                       // Number of rows / columns



    /**
     * 
     * @param event
     * @throws IOException
     * 
     * User wants to place their ships randomly
     */
    @FXML void placeRandom(ActionEvent event) throws IOException {
        board.placeRandom(DB.ShipList.human);
        DB.Human.setBoard(board);
        App.setRoot("Game");
    }

    /**
     * 
     * @param event
     * @throws IOException
     * 
     * All ships have been placed.
     * Send information to database
     * Start the game
     */
    @FXML void startGame(ActionEvent event) throws IOException {
        DB.Human.setBoard(board);
        App.setRoot("Game");


    }

    /**
     * 
     * @param event
     * 
     * Gets the current row / column selected by the user
     * Ensures the user has not exceeded the frequency of the ship type
     * If the ship can be placed (according to the rules of battleship)
     * Place the ship
     */
    @FXML void getBoardPosition(MouseEvent event) {

        int row             = (int) event.getX() / width;
        int column          = (int) event.getY() / width;
        boolean vertical    = getVertical(event);
        Ship ship           = shipList[dropDownIndex];

        if(shipAvailable(dropDownIndex)){

            if(ship.canPlaceAt(board, vertical, row, column)){
                ship.placeShip(board, row, column, vertical);
                updateFreqeuncy(dropDownIndex);
                updateComboBox();
    
            }
        }

        if(allShipsPlaced()){
            startButton.setDisable(false);
        }

    }

   
    /**
     * 
     * @param event
     * 
     * User has selected a ship from the drop down menu
     * The user can no longer place the ships randomly
     */
    @FXML void shipSelected(ActionEvent event){

        SingleSelectionModel<String> selection = comboBox.getSelectionModel();
        dropDownIndex = selection.getSelectedIndex();
        randomButton.setDisable(true);

    }

    /**
     * 
     * @param mouse
     * @return
     * 
     * Primary mouse buttom = Horizontal
     * Secondary mouse button = Vertical
     */
    private boolean getVertical(MouseEvent mouse){
        return mouse.getButton() == MouseButton.PRIMARY ? true : false;
    }

    
    /**
     * Initializes the drop down menu
     * Add the frequency of each ship next to the ships name
     */
    private void setComboBox(){

        values = FXCollections.observableArrayList();

        for(int i = 0; i < shipList.length; i++){
            int frequency = shipFrequencies[i];
            values.add(shipList[i].getName() + " ( " + frequency + " )");
        }

        comboBox.setItems(values);
        comboBox.setPromptText("Pick A Ship!");
        
    }

    /**
     * Decreases the ship frequency displayed to the user
     */
    private void updateComboBox(){
        values.set(dropDownIndex, shipList[dropDownIndex].getName() +
                           " ( " + shipFrequencies[dropDownIndex] + " )");
    }

  
    /**
     * 
     * @param index
     * @return
     * 
     * Check the frequency of the ship selected
     * If the frequency is not zero,
     * it may be placed on the board
     */
    private boolean shipAvailable(int index){
        return shipFrequencies[index] != 0;
    }


    private void updateFreqeuncy(int index){
        shipFrequencies[index]--;
        numberOfShips--;
    }


    /**
     * 
     * @return
     * 
     * Check if we are ready to play the game
     */
    private boolean allShipsPlaced(){
        return numberOfShips == 0;
    }

    /**
     * Sets total number of ships being placed
     * Depends on the frequency selected in previous scene
     */
    private void setNumberOfShips(){
        for(int i = 0; i < shipFrequencies.length;i++){
            numberOfShips += shipFrequencies[i];
        }
    }
   

        /**
     * 
     * @param pane FXML Pane where the rectangles will sit
     * 
     * Adding cells to the pane
     */
    public void showBoard(int size){

        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                humanBoard.getChildren().addAll(board.get(i, j));
            }
        }
    }

    /**
     * Starting point for controller class
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        shipFrequencies = DB.ShipFrequency.getFrequencies();
        gridSize = DB.Boards.size;
        width = paneSize / gridSize;

        board = new Board(gridSize, gridSize, paneSize);
        computer = new Board(gridSize, gridSize, paneSize);
        computer.placeRandom(DB.ShipList.computer);
        DB.Computer.setBoard(computer);
        showBoard(paneSize / gridSize);

        setComboBox();
        setNumberOfShips();
        startButton.setDisable(true);

    }
}