package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JPanel mainPanelLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnGuest;
    private JButton btnCancel;

    public LoginView() {
        super();
        setContentPane(mainPanelLogin);
        setTitle("Formular autentificare");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 275);
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
}
