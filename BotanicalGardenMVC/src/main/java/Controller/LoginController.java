package Controller;

import Model.Language;
import Model.User;
import Model.UserRepository;
import View.LoginView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class LoginController implements Observer {
    private UserRepository userRepo;
    private LoginView loginView;
    private Language language;


    public LoginController() {
        this.userRepo = new UserRepository();
        this.loginView = new LoginView();
        this.language = new Language();
        language.addObserver(this);

        this.loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoginClick(e);
            }

        });

        this.loginView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelClick(e);
            }
        });

        this.loginView.getBtnGuest().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuestClick(e);
            }
        });

        this.loginView.getBtnRo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                language.setLanguage("ro");
            }
        });

        this.loginView.getBtnEn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                language.setLanguage("en");
            }
        });

        this.loginView.getBtnEs().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                language.setLanguage("es");
            }
        });

        this.loginView.getBtnFr().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                language.setLanguage("fr");
            }
        });

        updateView();

    }

    private void btnLoginClick(ActionEvent e){
        String user = loginView.getTxtUser().getText();
        String ppassword = loginView.getTxtPassword().getText();

        User u;
        u = userRepo.searchuser(user, ppassword);


        if (u == null)
            errorMessage();
        else{
            if(u.getRole().equals("admin"))
            {
                successMessage();
                setAdminView();
            }
            else if (u.getRole().equals("angajat"))
            {
                successMessage();
                setEmployeeView();
            }

        }
    }

    private void btnCancelClick(ActionEvent e){
        loginView.dispose();
    }

    private void btnGuestClick(ActionEvent e){
        setGuestView();
    }

    public void setAdminView() {
        AdminController adminController = new AdminController();
        loginView.dispose();
    }

    public void setEmployeeView() {
        EmployeeController empController = new EmployeeController();
        loginView.dispose();
    }

    public void setGuestView() {
        GuestController guestController = new GuestController();
        loginView.dispose();
    }

    public void errorMessage() {
        JOptionPane.showMessageDialog(loginView,
                language.getString("invalidLoginMessage"),
                language.getString("invalidLoginTitle"),
                JOptionPane.ERROR_MESSAGE);
    }

    public void successMessage() {
        JOptionPane.showMessageDialog(loginView,
                language.getString("loginSuccessMessage"),
                language.getString("loginSuccessTitle"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void update(Observable o, Object arg) {
        updateView();
    }

    private void updateView() {
        loginView.getWelcomeLoginLabel().setText((language.getString("welcomeLabel")));
        loginView.getPlzLoginLabel().setText((language.getString("plzLoginLabel")));
        loginView.getUserLoginLabel().setText(language.getString("usernameLabel"));
        loginView.getPasswordLoginLabel().setText(language.getString("passwordLabel"));

        loginView.getBtnLogin().setText(language.getString("loginButton"));
        loginView.getBtnCancel().setText((language.getString("cancelButton")));
        loginView.getBtnGuest().setText((language.getString("guestButton")));
    }
}
