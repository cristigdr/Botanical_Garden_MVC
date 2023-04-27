package Controller;

import Model.Language;
import Model.User;
import Model.UserRepository;
import View.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AdminController implements Observer {

    final private UserRepository userRepo;
    final private AdminView adminView;
    final private Language language;


    public AdminController() {
        this.userRepo = new UserRepository();
        this.adminView = new AdminView();
        this.language = new Language();
        language.addObserver(this);

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

        this.adminView.getBtnRo().addActionListener(e -> language.setLanguage("ro"));

        this.adminView.getBtnEn().addActionListener(e -> language.setLanguage("en"));

        this.adminView.getBtnEs().addActionListener(e -> language.setLanguage("es"));

        this.adminView.getBtnFr().addActionListener(e -> language.setLanguage("fr"));

        updateView();
    }

    private void insertUserClick(ActionEvent e){
        User u = new User(adminView.getTxtUser().getText(), adminView.getTxtPassword().getText(), getRolString());

        if(userRepo.checkIfUserExists(u)){
            errorMessage();
        }else{
            boolean inserted = userRepo.saveUser(u);
            if(inserted){
                successMessage();
            } else {
                errorMessage();
            }
        }
        refreshTable();
        clearFields();
    }

    private void updateUserClick(ActionEvent e){
        User u = new User(adminView.getTxtUser().getText(), adminView.getTxtPassword().getText(), getRolString());

        if(userRepo.checkIfUserExists(u)){
            errorMessage();
        }else{
            boolean updated = userRepo.updateUser(u, adminView.getTxtId().getText());
            if(updated){
                successMessage();
            } else {
                errorMessage();
            }
        }
        refreshTable();
        clearFields();
    }

    private void deleteUserClick(ActionEvent e){
        boolean deleted = userRepo.deleteUser(adminView.getTxtId().getText());
        if(deleted){
            successMessage();
        } else{
            errorMessage();
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

    private void errorMessage() {
        JOptionPane.showMessageDialog(adminView,
                language.getString("invalidOperationMessage"),
                language.getString("invalidOperationTitle"),
                JOptionPane.ERROR_MESSAGE);
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(adminView,
                language.getString("operationSuccessfulMessage"),
                language.getString("operationSuccessfulTitle"),
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

        model.addColumn(language.getString("idLabel"));
        model.addColumn(language.getString("userLabel"));
        model.addColumn(language.getString("passwordLabel"));
        model.addColumn(language.getString("roleLabel"));

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
        adminView.getScrollPane().setViewport(viewport);
    }

    public void update(Observable o, Object arg) {
        updateView();
    }

    private void updateView() {
        adminView.getWelcomeLabel().setText((language.getString("welcomeLabel")));
        adminView.getAddUserLabel().setText((language.getString("addUserLabel")));
        adminView.getIdLabel().setText((language.getString("idLabel")));
        adminView.getUserLabel().setText((language.getString("userLabel")));
        adminView.getPasswordLabel().setText((language.getString("passwordLabel")));
        adminView.getRoleLabel().setText((language.getString("roleLabel")));

        adminView.getBtnInsert().setText(language.getString("insertButton"));
        adminView.getBtnUpdate().setText(language.getString("updateButton"));
        adminView.getBtnDelete().setText(language.getString("deleteButton"));
        adminView.getBtnClean().setText(language.getString("cleanButton"));

        //combobox
        adminView.getComboRole().removeAllItems();
        String[] roles = {language.getString("adminLabel"), language.getString("empLabel")};
        for (String role : roles) {
            adminView.getComboRole().addItem(role);
        }

        //column headers
        String[] columnNames = {
                language.getString("idLabel"),
                language.getString("userLabel"),
                language.getString("passwordLabel"),
                language.getString("roleLabel")
        };
        DefaultTableModel model = (DefaultTableModel) adminView.getTabUser().getModel();
        model.setColumnIdentifiers(columnNames);


    }

}
