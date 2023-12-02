package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {

  private JScrollPane scrollPane;
  private JTable table;

  public Table() {
    scrollPane = new JScrollPane();
    table = new JTable();
    initialize();

    table.getTableHeader().setOpaque(false);
    table.setRowHeight(25);
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

  public void setTable(Object[][] data, String[] columnNames) {
    SwingUtilities.invokeLater(() -> {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.setDataVector(data, columnNames);
      model.fireTableDataChanged();
    });
  }

  public void setColumns(String[] columns) {
    SwingUtilities.invokeLater(() -> {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.setColumnIdentifiers(columns);
      model.fireTableStructureChanged();
    });
  }
}
