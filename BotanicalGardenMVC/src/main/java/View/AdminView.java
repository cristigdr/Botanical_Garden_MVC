package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminView extends JFrame{
    private JPanel mainPanelAdmin;
    private JTextField txtId;
    private JTextField txtUser;
    private JTextField txtPassword;
    private JComboBox <String> comboRole;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClean;
    private JTable tabUser;
    private JScrollPane scrollPane;
    private JButton btnRo;
    private JButton btnEn;
    private JButton btnEs;
    private JButton btnFr;
    private JLabel welcomeLabel;
    private JLabel idLabel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel roleLabel;
    private JLabel addUserLabel;
    private DefaultTableModel model = new DefaultTableModel();


    public AdminView() {
        super();
        setContentPane(mainPanelAdmin);
        setTitle("Admin");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public DefaultTableModel getModel() {
        return model;
    }


    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtUser() {
        return txtUser;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JComboBox<String> getComboRole() {
        return comboRole;
    }

    public JButton getBtnInsert() {
        return btnInsert;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnClean() {
        return btnClean;
    }

    public JTable getTabUser() {
        return tabUser;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public int getTabRowIndex() {
        return tabUser.getSelectedRow();
    }

    public String getTabId(int rowIndex) {
        return tabUser.getValueAt(rowIndex, 0).toString();
    }

    public String getTabUser(int rowIndex) {
        return tabUser.getValueAt(rowIndex, 1).toString();
    }

    public String getTabPassword(int rowIndex) {
        return tabUser.getValueAt(rowIndex, 2).toString();
    }

    public String getTabRole(int rowIndex) {
        return tabUser.getValueAt(rowIndex, 3).toString();
    }

    public JButton getBtnRo() {
        return btnRo;
    }

    public JButton getBtnEn() {
        return btnEn;
    }

    public JButton getBtnEs() {
        return btnEs;
    }

    public JButton getBtnFr() {
        return btnFr;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JLabel getRoleLabel() {
        return roleLabel;
    }

    public JLabel getAddUserLabel() {
        return addUserLabel;
    }
}
