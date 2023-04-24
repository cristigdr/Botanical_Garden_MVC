package Controller;

import Model.UserRepository;
import View.LoginView;

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

    }

    private void btnCancelClick(ActionEvent e){
    }

    private void btnGuestClick(ActionEvent e){

    }
}
