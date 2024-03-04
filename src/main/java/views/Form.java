package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Form to display the query options and query parameters
 */
public class Form extends JPanel {

  private JComboBox<String> dropdown; // Query options
  private JTextField textField; // Query parameters
  private JButton button; // Submit query

  public Form(String[] options) {
    this.dropdown = new JComboBox<String>(options);
    this.textField = new JTextField();
    this.button = new JButton("Submit");

    setLayout(new GridLayout(1, 1));
    textField.setPreferredSize(new Dimension(300, 40));
    dropdown.setPreferredSize(new Dimension(300, 40));
    button.setPreferredSize(new Dimension(100, 40));

    setDropdown();
    setFormField();
  }

  public JComboBox<String> dropdown() {
    return this.dropdown;
  }

  public JTextField textField() {
    return this.textField;
  }

  public JButton button() {
    return this.button;
  }

  /**
   * Dropdown menu to display the query options
   */
  private void setDropdown() {
    JPanel dropPanel = new JPanel();
    JLabel dropLabel = new JLabel("Query Options");
    dropLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

    dropPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    dropPanel.add(dropLabel, c);

    c.gridx = 0;
    c.gridy = 1;
    dropPanel.add(this.dropdown, c);
    dropPanel.setPreferredSize(new Dimension(300, 100));
    dropdown.setSelectedIndex(0);

    add(dropPanel);
  }

  /**
   * Form field to enter the query parameters
   */
  private void setFormField() {
    JPanel formPanel = new JPanel();
    JLabel formLabel = new JLabel("Query Parameters");
    formLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

    formPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    formPanel.add(formLabel, c);

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 2;
    formPanel.add(this.textField, c);

    c.gridx = 2;
    c.gridy = 1;
    c.insets = new Insets(0, 10, 0, 0); // Adjust the left inset for space
    formPanel.add(this.button, c);
    formPanel.setPreferredSize(new Dimension(600, 100));
    add(formPanel);
  }
}
