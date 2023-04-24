package Controller;

import Model.Plant;
import Model.PlantRepository;
import Model.User;
import View.GuestView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuestController {
    private PlantRepository plantRepo;
    private GuestView guestView;

    public GuestController() {
        this.plantRepo = new PlantRepository();
        this.guestView = new GuestView();
        populateTable();

        this.guestView.getBtnSearch().addActionListener(e -> searchClick());

        this.guestView.getBtnRefresh().addActionListener(e -> refreshClick());

        this.guestView.getBtnClean().addActionListener(e -> cleanFieldsClick());
    }

    private void populateTable(){
        List<Plant> plants = plantRepo.getPlants();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 3;
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

    private void searchClick(){}

    private void refreshClick(){}

    private void cleanFieldsClick(){}

    private void setTable(DefaultTableModel model){
        guestView.getTabPlant().setModel(model);
        JViewport viewport = new JViewport();
        viewport.setView(guestView.getTabPlant());
        guestView.getScrollPane().setViewport(viewport);
    }
}
