package social.Controllers.Home;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import social.Database.Database;
import social.Debug.Flag;
import social.Objects.ClubPane;
import social.Objects.CurrentUser;

public class ClubController implements Initializable {

    @FXML private AnchorPane scrollAnchor;
    private ArrayList<ClubPane> clubs;
    private Database db;
    private int currentY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        db = new Database();
        try {
            clubs = db.getClubs();
        } catch (SQLException e) {
            // Flag.DEBUG(e.getCause().toString());
        }

        displayClubs();
    }

    private void displayClubs(){

        for(ClubPane club : clubs){
            club.getJoin().setOnAction(e -> {
                try {
                    clicked(club);
                } catch (SQLException e1) {
                    Flag.DEBUG(e1.getCause().toString());
                }
            });

            club.setY(currentY);
            scrollAnchor.getChildren().addAll(club);
            currentY += 115;
        }
    }

    private void clicked(ClubPane club) throws SQLException{
        db.addClub(club.getClub());
    }

}
