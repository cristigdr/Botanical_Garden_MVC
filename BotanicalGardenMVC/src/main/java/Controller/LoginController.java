package Controller;

import Model.User;
import Model.UserRepository;
import View.AdminView;
import View.EmployeeView;
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
        String user = loginView.getTxtUser().getText();
        String ppassword = loginView.getTxtPassword().getText();

        User u;
        u = userRepo.searchuser(user, ppassword);


        if (u == null)
            System.out.println("User not found!");
        else{
            if(u.getRole().equals("admin"))
            {
                setAdminView();
            }
            else if (u.getRole().equals("angajat"))
            {
                setEmployeeView();
            }

        }
    }

    private void btnCancelClick(ActionEvent e){
    }

    private void btnGuestClick(ActionEvent e){

    }

    public void setAdminView() {
        AdminView adminView = new AdminView();
        loginView.dispose();
    }

    public void setEmployeeView() {
        EmployeeView adminView = new EmployeeView();
        loginView.dispose();
    }
}
