package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Table view for the application to display the query results
 */
public class Table extends JPanel {

  private JScrollPane scrollPane;
  private JTable table; // display the query results

  public Table() {
    scrollPane = new JScrollPane();
    table = new JTable();
    initialize();

    table.getTableHeader().setOpaque(false);
    table.setRowHeight(25);
    // justify column headers
    (
      (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()
    ).setHorizontalAlignment(JLabel.LEFT);
  }

  /**
   * Do some initial setup for the table
   */
  private void initialize() {
    setLayout(new BorderLayout());

    // only change if you want to change the data in the table
    table.setModel(
      new DefaultTableModel(new Object[][] {}, Constants.Table.defaultColumns)
    );
    table.setFocusable(false);
    table.setIntercellSpacing(new Dimension(0, 0));
    table.setShowVerticalLines(false);
    table.setShowHorizontalLines(true);
    table.getTableHeader().setReorderingAllowed(false);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    scrollPane.setViewportView(table);
    scrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );

    add(scrollPane, BorderLayout.CENTER);
    setSize(new Dimension(1000, 600));
  }

  /**
   * Set the table data from outside the class
   * @param data - the data to display in the table (2D array of Objects)
   * @param columnNames - the column names for the table (array of Strings)
   */
  public void setTable(Object[][] data, String[] columnNames) {
    SwingUtilities.invokeLater(() -> {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.setDataVector(data, columnNames);
      model.fireTableDataChanged();
    });
  }

  /**
   * Set the table columns from outside the class
   * @param columns - the columns to display in the table (array of Strings)
   */
  public void setColumns(String[] columns) {
    SwingUtilities.invokeLater(() -> {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.setColumnIdentifiers(columns);
      model.fireTableStructureChanged();
    });
  }
}
