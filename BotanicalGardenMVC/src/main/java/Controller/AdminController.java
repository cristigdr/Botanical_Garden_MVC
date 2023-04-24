package Controller;

import Model.User;
import Model.UserRepository;
import View.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdminController {

    private UserRepository userRepo;
    private AdminView adminView;

    public AdminController() {
        this.userRepo = new UserRepository();
        this.adminView = new AdminView();
        populateTable();

        this.adminView.getBtnInsert().addActionListener(this::insertUserClick);

        this.adminView.getBtnUpdate().addActionListener(this::updateUserClick);

        this.adminView.getBtnDelete().addActionListener(this::deleteUserClick);

        this.adminView.getBtnClean().addActionListener(this::cleanFieldsClick);

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
        refreshTable();
        clearFields();
    }

    private void updateUserClick(ActionEvent e){
        User u = new User(adminView.getTxtUser().getText(), adminView.getTxtPassword().getText(), getRolString());

        if(userRepo.checkIfUserExists(u)){
            mesajEroare();
        }else{
            boolean updated = userRepo.updateUser(u, adminView.getTxtId().getText());
            if(updated){
                mesajSucces();
            } else {
                mesajEroare();
            }
        }
        refreshTable();
        clearFields();
    }

    private void deleteUserClick(ActionEvent e){
        boolean deleted = userRepo.deleteUser(adminView.getTxtId().getText());
        if(deleted){
            mesajSucces();
        } else{
            mesajEroare();
        }
        refreshTable();
        clearFields();
    }

    private void cleanFieldsClick(ActionEvent e){
        clearFields();
    }

    private void showSelectedRowData(){
        int row = adminView.getTabRowIndex();
        String id = adminView.getTabId(row);
        String user = adminView.getTabUser(row);
        String password = adminView.getTabPassword(row);
        String role = adminView.getTabRole(row);
        setId(id);
        setUser(user);
        setPassword(password);
        setRole(role);

    }

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

        setTable(model);
    }

    private void setId(String id){adminView.getTxtId().setText(id);}
    private void setUser(String user){adminView.getTxtUser().setText(user);}
    private void setPassword(String password){adminView.getTxtPassword().setText(password);}
    private void setRole(String role){adminView.getComboRole().setSelectedItem(role);}
    private void setRowCount(int count){adminView.getModel().setRowCount(count);}
    private void setTable(DefaultTableModel model){
        adminView.getTabUser().setModel(model);
        JViewport viewport = new JViewport();
        viewport.setView(adminView.getTabUser());
        adminView.getScrollPane().setViewport(viewport);    }

}
