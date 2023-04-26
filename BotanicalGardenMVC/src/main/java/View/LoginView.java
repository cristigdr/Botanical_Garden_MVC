package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame{
    private JPanel mainPanelLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnGuest;
    private JButton btnCancel;
    private JLabel welcomeLoginLabel;
    private JLabel plzLoginLabel;
    private JLabel userLoginLabel;
    private JLabel passwordLoginLabel;
    private JButton btnRo;
    private JButton btnEn;
    private JButton btnEs;
    private JButton btnFr;

    public LoginView() {
        super();
        setContentPane(mainPanelLogin);
        setTitle("Formular autentificare");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 325);
        setMinimumSize(new Dimension(350, 150));
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public JPanel getMainPanelLogin() {
        return mainPanelLogin;
    }

    public JTextField getTxtUser() {
        return txtUser;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnGuest() {
        return btnGuest;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JLabel getWelcomeLoginLabel() {
        return welcomeLoginLabel;
    }

    public JLabel getPlzLoginLabel() {
        return plzLoginLabel;
    }

    public JLabel getUserLoginLabel() {
        return userLoginLabel;
    }

    public JLabel getPasswordLoginLabel() {
        return passwordLoginLabel;
    }
}
