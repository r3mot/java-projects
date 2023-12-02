package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Form extends JPanel {

  private JComboBox<String> dropdown;
  private JTextField textField;
  private JButton button;

  public Form(String[] options) {
    this.dropdown = new JComboBox<String>(options);
    this.textField = new JTextField();
    this.button = new JButton("Submit");

    setLayout(new GridLayout(1, 1));
    textField.setPreferredSize(new Dimension(300, 30));
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
    JLabel formLabel = new JLabel("Enter a value");
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

    c.gridx = 3;
    c.gridy = 1;
    c.gridwidth = 1;
    formPanel.add(this.button, c);
    formPanel.setPreferredSize(new Dimension(600, 100));
    add(formPanel);
  }
}
