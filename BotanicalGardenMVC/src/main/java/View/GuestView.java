package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GuestView extends JFrame{
    private JPanel mainPanelGuest;
    private JComboBox comboCriteria;
    private JTextField txtFilter;
    private JTable tabPlant;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JButton btnClean;
    private JScrollPane scrollPane;
    private JButton btnRo;
    private JButton btnEn;
    private JButton btnEs;
    private JButton btnFr;
    private JLabel welcomeLabel;
    private JLabel filterDataLabel;
    private JLabel criteriaLabel;
    private JLabel filterLabel;
    private DefaultTableModel model = new DefaultTableModel();


    public GuestView() {
        super();
        setContentPane(mainPanelGuest);
        setTitle("Guest");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JPanel getMainPanelGuest() {
        return mainPanelGuest;
    }

    public JComboBox getComboCriteria() {
        return comboCriteria;
    }

    public JTextField getTxtFilter() {
        return txtFilter;
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

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultTableModel getModel() {
        return model;
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
}
