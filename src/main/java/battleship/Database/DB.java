package battleship.Database;

import java.io.IOException;
import java.util.Optional;

import battleship.App;
import battleship.Boards.Board;
import battleship.Ships.Battleship;
import battleship.Ships.Carrier;
import battleship.Ships.Destroyer;
import battleship.Ships.PatrolBoat;
import battleship.Ships.Ship;
import battleship.Ships.Submarine;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;

/**
 * Stores all information needed for the program to function
 * 
 * Sectioned off by purpose for readability when accessing
 */
public class DB {

// Ships
//==========================================================================================================================================================
   
    public static class Images {

        private static final Image FRONT                  = new Image("file:src/main/resources/Images/Carrier/front.png");
        private static final Image MIDDLE                 = new Image("file:src/main/resources/Images/Carrier/middle.png");
        private static final Image BACK                   = new Image("file:src/main/resources/Images/Carrier/back.png");
        private static final Image FRONT_ROTATED          = new Image("file:src/main/resources/Images/Carrier/frontRotated.png");
        private static final Image MIDDLE_ROTATED         = new Image("file:src/main/resources/Images/Carrier/middleRotated.png");
        private static final Image BACK_ROTATED           = new Image("file:src/main/resources/Images/Carrier/backRotated.png");

        public static final Image[] CARRIER              = { BACK, MIDDLE, MIDDLE, MIDDLE, FRONT };
        public static final Image[] BATTLESHIP           = { BACK, MIDDLE, MIDDLE, FRONT };
        public static final Image[] DESTROYER            = { BACK, MIDDLE, FRONT };
        public static final Image[] SUBMARINE            = { BACK, MIDDLE, FRONT };
        public static final Image[] PATROL_BOAT          = { BACK, FRONT };

        public static final Image[] CARRIER_ROTATED      = { FRONT_ROTATED, MIDDLE_ROTATED, MIDDLE_ROTATED, MIDDLE_ROTATED, BACK_ROTATED };
        public static final Image[] BATTLESHIP_ROTATED   = { FRONT_ROTATED, MIDDLE_ROTATED, MIDDLE_ROTATED, BACK_ROTATED };
        public static final Image[] DESTROYER_ROTATED    = { FRONT_ROTATED, MIDDLE_ROTATED, BACK_ROTATED };
        public static final Image[] SUBMARINE_ROTATED    = { FRONT_ROTATED, MIDDLE_ROTATED, BACK_ROTATED };
        public static final Image[] PATROL_BOAT_ROTATED  = { FRONT_ROTATED, BACK_ROTATED };

    }

    public static class ShipList {
    
        public static Ship[] human = {  new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine(),
                                        new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine(),
                                        new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine(), new Battleship() 
                                    };

        public static Ship[] computer = {  new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine(),
                                        new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine(),
                                        new Battleship(), new Destroyer(), new Carrier(), new PatrolBoat(), new Submarine(), new Battleship() 
                                    };

    }

    public static class ShipIndex {

        public static final int BATTLESHIP    = 0;
        public static final int CARRIER       = 1;
        public static final int DESTROYER     = 2;
        public static final int PATROL_BOAT   = 3;
        public static final int SUBMARINE     = 4;

    }

    public static class ShipSize {

        public static final int BATTLESHIP    = 4;
        public static final int CARRIER       = 5;
        public static final int DESTROYER     = 3;
        public static final int PATROL_BOAT   = 2;
        public static final int SUBMARINE     = 3;

    }

    public static class ShipFrequency {

        private static int[] frequencies;

        public static void setFrequencies(int[] array){
            frequencies = new int[5];
            frequencies = array;
        }

        public static int[] getFrequencies(){
            return frequencies;
        }

        public static int getFrequency(){
            return frequencies[0];
        }

    }

    

// Board
//==========================================================================================================================================================

    public static class Boards {

        public static int rows;
        public static int columns;
        public static int size;
        public static int numShips;

        public static void setNumShips(int number){
            numShips = number;
        }

        public static void setRows(int number){
            rows = number;
        }

        public static void setColumns(int number){
            columns = number;
        }

        public static void setSize(int number){
            size = number;
        }
    }


// Human
//==========================================================================================================================================================

    public static class Human{

        public static Board board;

        public static void setBoard(Board b){
            board = b;
        }

        public static Board getBoard(){
            return board;
        }

    }


// Computer
//==========================================================================================================================================================
    public static class Computer {

        private static Board board;

        public static void setBoard(Board b){
            board = b;
        }

        public static Board getBoard(){
            return board;
        }
    }

// GUI
//==========================================================================================================================================================
  
    public static class GUI {

        public static final int large_pane_size = 600;
        public static final int small_pane_size = 300;
    }



// Strings
//==========================================================================================================================================================

    public static class ErrorType {

        public static final String TITLE = "ERORR";
        public static final String FREQUENCY = "You have selected too many ships." +
                                                " We are resetting your selection." +
                                                " NOTE: If you cannot select the appropriate amount this time, we will pick for you";

        public static void frequencyWarning() {

            Alert error = new Alert(AlertType.WARNING);
            error.setTitle(TITLE);
            error.setHeaderText(FREQUENCY);
            Optional<ButtonType> result = error.showAndWait();
            if(result.get() == ButtonType.OK){
                return;
            }
        }
        
    }

    public static class Winner {

        public static void display(String winner){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("WINNER!!!");
            alert.setHeaderText(winner + " has won the game!");
            alert.setContentText("Would you like to play again?");
            ButtonType yes = new ButtonType("Yes", ButtonData.YES);
            ButtonType no = new ButtonType("No", ButtonData.NO);
            alert.getButtonTypes().setAll(yes, no);
            alert.showAndWait().ifPresent(type ->{
                if(type == yes){
                    try {
                        startOver();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(type == no){
                    Platform.exit();
                }
            });
        }

        private static void startOver() throws IOException{
            App.setRoot("SelectSize");
        }
       
    }

}
