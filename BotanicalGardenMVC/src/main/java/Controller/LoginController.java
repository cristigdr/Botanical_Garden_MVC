package Controller;

import Model.User;
import Model.UserRepository;
import View.AdminView;
import View.EmployeeView;
import View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private UserRepository userRepo;
    private LoginView loginView;

    public LoginController() {
        this.userRepo = new UserRepository();
        this.loginView = new LoginView();
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

    }

    private void btnLoginClick(ActionEvent e){
        String user = loginView.getTxtUser().getText();
        String ppassword = loginView.getTxtPassword().getText();

        User u;
        u = userRepo.searchuser(user, ppassword);


        if (u == null)
            mesajEroare();
        else{
            if(u.getRole().equals("admin"))
            {
                mesajSucces();
                setAdminView();
            }
            else if (u.getRole().equals("angajat"))
            {
                mesajSucces();
                setEmployeeView();
            }

        }
    }

    private void btnCancelClick(ActionEvent e){
        loginView.dispose();
    }

    private void btnGuestClick(ActionEvent e){
        setEmployeeView();
    }

    public void setAdminView() {
        AdminController adminController = new AdminController();
        loginView.dispose();
    }

    public void setEmployeeView() {
        GuestController guestController = new GuestController();
        loginView.dispose();
    }

    public void mesajEroare() {
        JOptionPane.showMessageDialog(loginView,
                "Utilizator sau parolă invalidă",
                "Încearcă din nou",
                JOptionPane.ERROR_MESSAGE);
    }

    public void mesajSucces() {
        JOptionPane.showMessageDialog(loginView,
                "Autentificare reușită!",
                "Succes!",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
