package View;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame{
    private JPanel mainPanelAdmin;

    public AdminView() {
        super();
        setContentPane(mainPanelAdmin);
        setTitle("Formular autentificare");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 275);
        setMinimumSize(new Dimension(350, 150));
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


}
