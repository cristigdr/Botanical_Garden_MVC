package Controller;

import Model.Plant;
import Model.PlantRepository;
import Model.User;
import View.EmployeeView;
import View.GuestView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

public class EmployeeController {
    private PlantRepository plantRepo;
    private EmployeeView empView;

    public EmployeeController() {
        this.plantRepo = new PlantRepository();
        this.empView = new EmployeeView();
        populateTable();

        empView.getDaRadioButton().setActionCommand("Da");
        empView.getNuRadioButton().setActionCommand("Nu");
        empView.getaRadioButton().setActionCommand("A");
        empView.getbRadioButton().setActionCommand("B");
        empView.getcRadioButton().setActionCommand("C");
        empView.getdRadioButton().setActionCommand("D");

        this.empView.getBtnSearch().addActionListener(e -> searchClick());
        this.empView.getBtnRefresh().addActionListener(e -> populateTable());
        this.empView.getBtnClean().addActionListener(e -> cleanFieldsClick());

        this.empView.getBtnInsert().addActionListener(e -> insertPlantClick());
        this.empView.getBtnUpdate().addActionListener(e -> updatePlantClick());
        this.empView.getBtnDelete().addActionListener(e -> deletePlantClick());

        this.empView.getTabPlant().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                showSelectedRowData();
            }
        });


    }

    private void populateTable(){
        List<Plant> plants = plantRepo.getPlants();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };

        model.addColumn("ID");
        model.addColumn("Denumire");
        model.addColumn("Tip");
        model.addColumn("Specie");
        model.addColumn("Planta Carnivora");
        model.addColumn("Zona Gradina Botanica");

        for (Plant p : plants) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getType(), p.getSpecies(), p.getCarnivorous(), p.getZone()});
        }
        setTable(model);
    }

    private void searchClick(){List<Plant> plants = plantRepo.filterPlants(getCriteriaString(), empView.getTxtFilter().getText());

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };

        model.addColumn("ID");
        model.addColumn("Denumire");
        model.addColumn("Tip");
        model.addColumn("Specie");
        model.addColumn("Planta Carnivora");
        model.addColumn("Zona Gradina Botanica");

        for (Plant p : plants) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getType(), p.getSpecies(), p.getCarnivorous(), p.getZone()});
        }
        setTable(model);
    }

    private void cleanFieldsClick(){
        setId("");
        setName("");
        setType("");
        setSpecies("");
        setFilter("");
        setCarnivorous(false);
        setZone(false);
    }

    private void insertPlantClick(){
        Plant p = new Plant(empView.getTxtName().getText(), empView.getTxtType().getText(), empView.getTxtSpecies().getText(), getStringCarn(), getSTringZone());

        if(plantRepo.checkIfPlantExists(p)){
            errorMessage();
        }else{
            boolean inserted = plantRepo.savePlant(p);
            if(inserted){
                successMessage();
            } else {
                errorMessage();
            }
        }
        refreshTable();
        cleanFieldsClick();}

    private void updatePlantClick(){
        Plant p = new Plant(empView.getTxtName().getText(), empView.getTxtType().getText(), empView.getTxtSpecies().getText(), getStringCarn(), getSTringZone());

        if(plantRepo.checkIfPlantExists(p)){
            errorMessage();
        }else{
            boolean updated = plantRepo.updatePlant(p, empView.getTxtId().getText());
            if(updated){
                successMessage();
            } else {
                errorMessage();
            }
        }
        refreshTable();
        cleanFieldsClick();
    }

    private void deletePlantClick(){
        boolean deleted = plantRepo.deletePlant(empView.getTxtId().getText());
        if(deleted){
            successMessage();
        } else{
            errorMessage();
        }
        refreshTable();
        cleanFieldsClick();
    }

    private void showSelectedRowData(){
        int row = empView.getTabRowIndex();
        String id = empView.getTabId(row);
        String name = empView.getTabName(row);
        String type = empView.getTabType(row);
        String species = empView.getTabSpecies(row);
        String carnivorous = empView.getTabCarnivorous(row);
        String zone = empView.getTabZone(row);
        setId(id);
        setName(name);
        setType(type);
        setSpecies(species);
        setBtnGrCarn(carnivorous);
        setBtnGrZone(zone);
    }

    private void refreshTable(){
        setRowCount(0);
        populateTable();
    }

    private void setRowCount(int count){empView.getModel().setRowCount(count);}


    private void setTable(DefaultTableModel model){
        empView.getTabPlant().setModel(model);
        JViewport viewport = new JViewport();
        viewport.setView(empView.getTabPlant());
        empView.getScrollPane().setViewport(viewport);
    }

    public String getCriteriaString(){
        String data = null;
        Object selectedItem = empView.getComboCriteria().getSelectedItem();
        if (selectedItem != null) {
            data = selectedItem.toString();
        }
        return data;
    }

    public String getStringCarn() {
        {
            for (Enumeration<AbstractButton> buttons = empView.getBtnGrCarnivorous().getElements(); buttons.hasMoreElements(); ) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    return button.getText();
                }
            }
            return "";
        }
    }

    public String getSTringZone() {
        {
            for (Enumeration<AbstractButton> buttons = empView.getBtnGrZone().getElements(); buttons.hasMoreElements(); ) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    return button.getText();
                }
            }
            return "";
        }
    }


    private void setFilter(String filter){empView.getTxtFilter().setText(filter);}
    private void setId(String id){empView.getTxtId().setText(id);}
    private void setName(String name){empView.getTxtName().setText(name);}
    private void setType(String type){empView.getTxtType().setText(type);}
    private void setSpecies(String species){empView.getTxtSpecies().setText(species);}

    private void setCarnivorous(boolean bool){
        ButtonModel buttonModel = null;
        empView.getBtnGrCarnivorous().setSelected(buttonModel, bool);
        if(bool){
            empView.getBtnGrCarnivorous().setSelected(buttonModel, true);
        } else {
            empView.getBtnGrCarnivorous().clearSelection();
        }
    }

    private void setZone(boolean bool){
        ButtonModel buttonModel = null;
        empView.getBtnGrZone().setSelected(buttonModel, bool);
        if(bool){
            empView.getBtnGrZone().setSelected(buttonModel, true);
        } else {
            empView.getBtnGrZone().clearSelection();
        }
    }

    public void setBtnGrCarn(String carn) {
        if (carn.equals("Da")) {
            empView.getDaRadioButton().setSelected(true);
        } else if (carn.equals("Nu")) {
            empView.getNuRadioButton().setSelected(true);
        }
    }

    public void setBtnGrZone(String zone) {
        if (zone.equals("A")) {
            empView.getaRadioButton().setSelected(true);
        } else if (zone.equals("B")) {
            empView.getbRadioButton().setSelected(true);
        } else if (zone.equals("C")) {
            empView.getcRadioButton().setSelected(true);
        } else if (zone.equals("D")) {
            empView.getdRadioButton().setSelected(true);
        }
    }

    private void errorMessage() {
        JOptionPane.showMessageDialog(empView,
                "Operație invalidă",
                "Încearcă din nou",
                JOptionPane.ERROR_MESSAGE);
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(empView,
                "Operație reușită!",
                "Succes!",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
