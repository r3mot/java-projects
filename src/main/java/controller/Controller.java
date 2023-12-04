package controller;

import database.DataService;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import views.Constants;
import views.MainView;

/**
 * The main controller for the application
 *
 * @see model.Warehouse
 * @see model.Product
 * @see model.ProductType
 * @see model.ProductPickup
 * @see model.ProductSupplied
 * @see model.Supplier
 *
 */
public class Controller {

  private MainView mainView;
  private DataService service;

  public Controller() {
    service = new DataService();
    mainView = new MainView("Grocery Store Database");

    attachButtonListener();
    attachDropdownListener();
    addKeyListener();
  }

  // render the main view
  public void renderView() {
    EventQueue.invokeLater(
      new Runnable() {
        public void run() {
          mainView.setVisible(true);
        }
      }
    );
  }

  /**
   * Attaches a listener to the dropdown to update the table columns
   * When the dropdown is changed, the table columns will be updated
   *
   * @see view.Form
   * @see view.Table
   * @see view.Constants
   */
  private void attachDropdownListener() {
    mainView
      .form()
      .dropdown()
      .addItemListener(
        new ItemListener() {
          public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
              int selectedIndex = mainView.form().dropdown().getSelectedIndex();
              String[] tableColumns = Constants.getColumns(selectedIndex);
              mainView.table().setTable(new Object[][] {}, tableColumns);
            }
          }
        }
      );
  }

  /**
   * Attaches a listener to the button to query the database
   * Gets the index of the dropdown and calls the query method
   */
  private void attachButtonListener() {
    mainView
      .form()
      .button()
      .addActionListener(e -> {
        int index = mainView.form().dropdown().getSelectedIndex(); // get dropdown index
        Object[][] data = query(index); // query the database
        mainView.table().setTable(data, Constants.getColumns(index)); // update the table
      });
  }

  /**
   * If the user presses enter, perform the query
   */
  private void addKeyListener() {
    mainView
      .form()
      .textField()
      .addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              int index = mainView.form().dropdown().getSelectedIndex(); // get dropdown index
              Object[][] data = query(index); // query the database
              mainView.table().setTable(data, Constants.getColumns(index)); // update the table
            }
          }
        }
      );
  }

  /**
   * Queries the database based on the dropdown index
   * The table expects a 2D array of Objects, so we will return that
   *
   * @param index
   * @return Object[][] data
   */
  private Object[][] query(int index) {
    String params = mainView.form().textField().getText();
    Constants.Options option = Constants.Options.values()[index];
    switch (option) {
      case GET_PRODUCT_BY_ID:
        return service.getProductById(Integer.parseInt(params));
      case GET_PRODUCTS_BY_PRODUCT_TYPE:
        return service.getProductsByType(mainView.form().textField().getText());
      case GET_WAREHOUSE_BY_AREA_CODE:
        return service.getWarehouseByAreaCode(Integer.parseInt(params));
      default:
        return new Object[][] {};
    }
  }
}
