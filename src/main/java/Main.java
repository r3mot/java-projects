import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import controller.Controller;
import javax.swing.UIManager;

/**
 * A simple application that connects a MySQL database to a Java application.
 */
public class Main {

  public static void main(String[] args) {
    // theme for the app (FlatLaf - dependency in pom.xml)
    FlatAtomOneDarkIJTheme.setup();
    UIManager.put("Component.focusWidth", 0);
    UIManager.put("TextComponent.arc", 5);

    // main controller for the application
    Controller controller = new Controller();

    // render the main view
    controller.renderView();
  }
}
