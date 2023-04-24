package Controller;

import Model.User;
import Model.UserRepository;
import View.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdminController {

    private UserRepository userRepo;
    private AdminView adminView;

    public AdminController() {
        this.userRepo = new UserRepository();
        this.adminView = new AdminView();

        this.adminView.getBtnInsert().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertUserClick(e);
            }
        });

        this.adminView.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserClick(e);
            }
        });

        this.adminView.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserClick(e);
            }
        });

        this.adminView.getBtnClean().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFieldsClick(e);
            }
        });

        this.adminView.getTabUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                showSelectedRowData();
            }
        });
    }

    private void insertUserClick(ActionEvent e){
        User u = new User(adminView.getTxtUser().getText(), adminView.getTxtPassword().getText(), getRolString());

        if(userRepo.checkIfUserExists(u)){
            mesajEroare();
        }else{
            boolean inserted = userRepo.saveUser(u);
            if(inserted){
                mesajSucces();
            } else {
                mesajEroare();
            }
        }
        clearFields();
    }

    private void updateUserClick(ActionEvent e){}

    private void deleteUserClick(ActionEvent e){}

    private void cleanFieldsClick(ActionEvent e){}

    private void showSelectedRowData(){}

    public String getRolString(){
        String data = null;
        Object selectedItem = adminView.getComboRole().getSelectedItem();
        if (selectedItem != null) {
            data = selectedItem.toString();
        }
        return data;
    }

    private void mesajEroare() {
        JOptionPane.showMessageDialog(adminView,
                "Operație invalidă",
                "Încearcă din nou",
                JOptionPane.ERROR_MESSAGE);
    }

    private void mesajSucces() {
        JOptionPane.showMessageDialog(adminView,
                "Operație reușită!",
                "Succes!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshTable(){
        setRowCount(0);
        populateTable();
    }

    private void clearFields(){
        setId("");
        setUser("");
        setPassword("");
    }

    private void populateTable(){
        List<User> users = userRepo.getUsers();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 3;
            }
        };

        model.addColumn("ID");
        model.addColumn("User");
        model.addColumn("Parola");
        model.addColumn("Rol");

        for(User u : users){
            model.addRow(new Object[]{u.getId(), u.getUser(), u.getPassword(), u.getRole()});
        }
    }

    private void setId(String id){adminView.getTxtId().setText(id);}
    private void setUser(String user){adminView.getTxtUser().setText(user);}
    private void setPassword(String password){adminView.getTxtPassword().setText(password);}
    private void setRowCount(int count){adminView.getModel().setRowCount(count);}
    private void setTable(DefaultTableModel model){
        adminView.getTabUser().setModel(model);
        //adminView.getScrollPane().setViewport(adminView.getTabUser());
    }

}
