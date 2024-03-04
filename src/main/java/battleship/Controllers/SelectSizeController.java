package battleship.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import battleship.App;
import battleship.Database.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SelectSizeController implements Initializable {

    @FXML private Button battleship;
    @FXML private Button randomQuantity;
    @FXML private Button carrier;
    @FXML private Button continueBtn;
    @FXML private Button destroyer;
    @FXML private Label destroyerCount;
    @FXML private Label patrolCount;
    @FXML private Label battleshipCount;
    @FXML private Label carrierCount;
    @FXML private Label submarineCount;
    @FXML private Button patrolboat;
    @FXML private Button sizeFifteen;
    @FXML private Button sizeTen;
    @FXML private Button sizeTwelve;
    @FXML private Button submarine;

    private Button sizePicked;
    private int[] shipFrequencies;
    private int frequencyAttempts = 0;

    private final String SIZE_10 = "sizeTen";                   // Id of Grid Size button
    private final String SIZE_12 = "sizeTwelve";                // Id of Grid Size button
    private final String SIZE_15 = "sizeFifteen";               // Id of Grid Size button

    private final int GRID_10 = 10;                             // Value of the Grid Size
    private final int GRID_12 = 12;                             // Value of the Grid Size
    private final int GRID_15 = 15;                             // Value of the Grid Size

    private int gridSize;                                       // Number of rows / columns
    private int capacity;                                       // Max cells available
    private int variableCapacity;

    private boolean SHOW = false;                               // Toggle buttons on / off
    private boolean HIDE = true;                                // Toggle buttons on / off



    /**
     * 
     * @param event
     * @throws IOException 
     * 
     * Switch scenes
     */
    @FXML void switchToSetShip(ActionEvent event) throws IOException {
        DB.Boards.setSize(gridSize);
        DB.ShipFrequency.setFrequencies(shipFrequencies);
        App.setRoot("SetShip");
    }

    /**
     * 
     * @param event
     * @throws IOException
     * 
     * Randomly select ship frequencies
     */
    @FXML void randomClicked(ActionEvent event) throws IOException {
        setFrequeciesRandom();
        switchToSetShip(event);
    }


     /**
     * 
     * @param event
     * 
     * User has selected a grid size (cannot change decision)
     * Each button has a fixed ID -> uses ID to determine the
     * value of the grid size
     */
    @FXML void sizeSelected(ActionEvent event) {
        
        sizePicked = (Button) event.getSource();

        switch(sizePicked.getId()){
            case SIZE_10: setGridSize(GRID_10); break;
            case SIZE_12: setGridSize(GRID_12); break;
            case SIZE_15: setGridSize(GRID_15); break;
        }

        setCapacity(gridSize);
        sizeButtons(HIDE);
        shipButtons(SHOW);

    }

    /**
     * 
     * @param event 
     * @throws IOException 
     * 
     * Increments the number of Battleships to be placed
     * If the user selects too many ships, alert them.
     * On the second failed attempt, select a random frequency of each ship type.
     * 
     */
    @FXML void addBattleship(ActionEvent event) throws IOException {

        int value = increment(DB.ShipIndex.BATTLESHIP);
        battleshipCount.setText(String.valueOf(value));
        

        if(tooManyShips(value, DB.ShipSize.BATTLESHIP)){
            displayError();

            if(maxAttempts()){
                setFrequeciesRandom();
                switchToSetShip(event);
            }
            resetFields();
        }
    }

    /**
     * 
     * @param event
     * @throws IOException 
     * 
     * Increments the number of Carriers to be placed
     * If the user selects too many ships, alert them.
     * On the second failed attempt, select a random frequency of each ship type.
     */
    @FXML void addCarrier(ActionEvent event) throws IOException {

    
        int value = increment(DB.ShipIndex.CARRIER);
        carrierCount.setText(String.valueOf(value));

        if(tooManyShips(value, DB.ShipSize.CARRIER)){
            displayError();
            
            if(maxAttempts()){
                setFrequeciesRandom();
                switchToSetShip(event);
            }
            resetFields();
        }
    }


    /**
     * 
     * @param event
     * @throws IOException
     * 
     * Increments the number of Destroyers to be placed
     * If the user selects too many ships, alert them.
     * On the second failed attempt, select a random frequency of each ship type.
     */
    @FXML void addDestroyer(ActionEvent event) throws IOException {
        
        int value = increment(DB.ShipIndex.DESTROYER);
        destroyerCount.setText(String.valueOf(value));

        if(tooManyShips(value, DB.ShipSize.DESTROYER)){
            displayError();

            if(maxAttempts()){
                setFrequeciesRandom();
                switchToSetShip(event);
            }
            resetFields();
        }
    }


    /**
     * 
     * @param event
     * @throws IOException
     * 
     * Increments the number of Patrol Boats to be placed
     * If the user selects too many ships, alert them.
     * On the second failed attempt, select a random frequency of each ship type.
     */
    @FXML void addPatrolBoat(ActionEvent event) throws IOException {

        int value = increment(DB.ShipIndex.PATROL_BOAT);
        patrolCount.setText(String.valueOf(value));

        if(tooManyShips(value, DB.ShipSize.PATROL_BOAT)){
            displayError();

            if(maxAttempts()){
                setFrequeciesRandom();
                switchToSetShip(event);
            }
            resetFields();
        }
    }


    /**
     * 
     * @param event
     * @throws IOException 
     * 
     * Increments the number of Submarines to be placed
     * If the user selects too many ships, alert them.
     * On the second failed attempt, select a random frequency of each ship type.
     */
    @FXML void addSubmarine(ActionEvent event) throws IOException {

        int value = increment(DB.ShipIndex.SUBMARINE);
        submarineCount.setText(String.valueOf(value));

        if(tooManyShips(value, DB.ShipSize.SUBMARINE)){
            displayError();

            if(maxAttempts()){
                setFrequeciesRandom();
                switchToSetShip(event);
            }
            resetFields();
        }
    }


    /**
     * Selects random number of Ship frequencies for user
     */
    private void setFrequeciesRandom(){

        switch(sizePicked.getId()){
            case SIZE_10: setRandomFrequenices(1); break;
            case SIZE_12: setRandomFrequenices(2); break;
            case SIZE_15: setRandomFrequenices(3); break;
        }
    }

    private void setRandomFrequenices(int number){

       for(int i = 0; i < 5; i++){
           shipFrequencies[i] = number;
       }
    }


    /**
     * 
     * @param gridSize Number of rows / columns
     * 
     */
    private void setGridSize(int gridSize){
        this.gridSize = gridSize;

    }

    /**
     * 
     * @param number The number of cells that can be occupied 
     */
    private void setCapacity(int number){
        this.capacity = (number * number ) / 3;
        setVariableCapacity();
    }

    private void setVariableCapacity(){
        this.variableCapacity = this.capacity;
    }

    /**
     * 
     * @param index 
     * @return
     * 
     * Increments the number of ships at the specified index
     */
    private int increment(int index){
        shipFrequencies[index]++;
        return shipFrequencies[index];
    }


    /**
     * 
     * @param quantity Number of ship type
     * @param shipSize Size of the specific ship
     * @return
     * 
     * Returns true if the capacity is reached
     * 
     */
    private boolean tooManyShips(int quantity, int shipSize){

        int spaceRequired = shipSize * quantity;
    
        if(spaceRequired > variableCapacity)
        {
            frequencyAttempts++;
            return true;
        }

        variableCapacity -= spaceRequired;
     
        return false;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        shipFrequencies = new int[5];
        variableCapacity = capacity;
        shipButtons(HIDE);
        sizeButtons(SHOW); 
    }

    private void displayError() {
        DB.ErrorType.frequencyWarning();
    }

    private boolean maxAttempts(){
        return frequencyAttempts >= 2;
    }

    private void resetFields(){

        for(int i = 0; i < 5; i++){
            shipFrequencies[i] = 0;
        }

        resetLabels();
        setGridSize(gridSize);
        setVariableCapacity();
    }

    private void sizeButtons(boolean state){
        sizeTen.setDisable(state);
        sizeTwelve.setDisable(state);
        sizeFifteen.setDisable(state);
    }

    private void shipButtons(boolean state){
        battleship.setDisable(state);
        carrier.setDisable(state);
        destroyer.setDisable(state);
        patrolboat.setDisable(state);
        submarine.setDisable(state);
    }

    private void resetLabels(){

        destroyerCount.setText("");
        patrolCount.setText("");
        battleshipCount.setText("");
        carrierCount.setText("");
        submarineCount.setText("");

    }
}
