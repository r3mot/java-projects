package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Main view for the application
 *
 * @see view.Dropdown
 * @see view.Form
 */
public class MainView extends JFrame {

  private Form form;
  private Table table;

  public MainView(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setDefaultLookAndFeelDecorated(true);
    setSize(new Dimension(1000, 600));
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);
    createForm(Constants.dropdownOptions); // default options
    createTable();
  }

  private void createForm(String[] options) {
    this.form = new Form(options);
    add(form, BorderLayout.NORTH);
  }

  private void createTable() {
    this.table = new Table();
    add(table, BorderLayout.CENTER);
  }

  public Form form() {
    return this.form;
  }

  public Table table() {
    return this.table;
  }
}
