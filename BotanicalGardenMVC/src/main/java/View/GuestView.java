package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GuestView extends JFrame{
    private JPanel mainPanelGuest;
    private JComboBox comboCriteria;
    private JTextField txtFilter;
    private JTable tabPlant;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JButton btnClean;
    private JScrollPane scrollPane;
    private DefaultTableModel model = new DefaultTableModel();


    public GuestView() {
        super();
        setContentPane(mainPanelGuest);
        setTitle("Guest");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 275);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JPanel getMainPanelGuest() {
        return mainPanelGuest;
    }

    public JComboBox getComboBox1() {
        return comboCriteria;
    }

    public JTextField getTextField1() {
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
}
