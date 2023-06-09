package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeView extends JFrame{
    private JPanel mainPanelEmployee;
    private JComboBox<String> comboCriteria;
    private JTextField txtFilter;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtType;
    private JTextField txtSpecies;
    private JRadioButton daRadioButton;
    private JRadioButton nuRadioButton;
    private JRadioButton aRadioButton;
    private JRadioButton bRadioButton;
    private JRadioButton cRadioButton;
    private JRadioButton dRadioButton;
    private JTable tabPlant;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JButton btnClean;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JScrollPane scrollPane;
    private JButton btnRo;
    private JButton btnEn;
    private JButton btnEs;
    private JButton btnFr;
    private JLabel welcomeLabel;
    private JLabel filterDataLabel;
    private JLabel criteriaLabel;
    private JLabel filterLabel;
    private JLabel addPlantLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel speciesLabel;
    private JLabel carnivorousLabel;
    private JLabel zoneLabel;
    private ButtonGroup btnGrZone;
    private ButtonGroup btnGrCarnivorous;
    private DefaultTableModel model = new DefaultTableModel();


    public EmployeeView() {
        super();
        setContentPane(mainPanelEmployee);
        setTitle("Employee");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }



    public JComboBox<String> getComboCriteria() {
        return comboCriteria;
    }

    public JTextField getTxtFilter() {
        return txtFilter;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtType() {
        return txtType;
    }

    public JTextField getTxtSpecies() {
        return txtSpecies;
    }

    public JRadioButton getDaRadioButton() {
        return daRadioButton;
    }

    public JRadioButton getNuRadioButton() {
        return nuRadioButton;
    }

    public JRadioButton getaRadioButton() {
        return aRadioButton;
    }

    public JRadioButton getbRadioButton() {
        return bRadioButton;
    }

    public JRadioButton getcRadioButton() {
        return cRadioButton;
    }

    public JRadioButton getdRadioButton() {
        return dRadioButton;
    }

    public JTable getTabPlant() {
        return tabPlant;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JButton getBtnClean() {
        return btnClean;
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

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public ButtonGroup getBtnGrZone() {
        return btnGrZone;
    }

    public ButtonGroup getBtnGrCarnivorous() {
        return btnGrCarnivorous;
    }

    public int getTabRowIndex() {
        return tabPlant.getSelectedRow();
    }

    public String getTabId(int rowIndex) {
        return tabPlant.getValueAt(rowIndex, 0).toString();
    }
    public String getTabName(int rowIndex) {
        return tabPlant.getValueAt(rowIndex, 1).toString();
    }
    public String getTabType(int rowIndex) {
        return tabPlant.getValueAt(rowIndex, 2).toString();
    }
    public String getTabSpecies(int rowIndex) {
        return tabPlant.getValueAt(rowIndex, 3).toString();
    }
    public String getTabCarnivorous(int rowIndex) {
        return tabPlant.getValueAt(rowIndex, 4).toString();
    }
    public String getTabZone(int rowIndex) {
        return tabPlant.getValueAt(rowIndex, 5).toString();
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

    public JLabel getFilterDataLabel() {
        return filterDataLabel;
    }

    public JLabel getCriteriaLabel() {
        return criteriaLabel;
    }

    public JLabel getFilterLabel() {
        return filterLabel;
    }

    public JLabel getAddPlantLabel() {
        return addPlantLabel;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public JLabel getSpeciesLabel() {
        return speciesLabel;
    }

    public JLabel getCarnivorousLabel() {
        return carnivorousLabel;
    }

    public JLabel getZoneLabel() {
        return zoneLabel;
    }
}
