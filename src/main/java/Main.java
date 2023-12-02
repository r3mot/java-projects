import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import controller.Controller;

public class Main {

  public static void main(String[] args) {
    FlatMaterialDarkerIJTheme.setup(); // theme for the app
    Controller controller = new Controller();
    controller.renderView();
  }
}
