package Controller;

import Model.Plant;
import Model.PlantRepository;
import View.GuestView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GuestController {
    private PlantRepository plantRepo;
    private GuestView guestView;

    public GuestController() {
        this.plantRepo = new PlantRepository();
        this.guestView = new GuestView();
        populateTable();

        this.guestView.getBtnSearch().addActionListener(e -> searchClick());

        this.guestView.getBtnRefresh().addActionListener(e -> populateTable());

        this.guestView.getBtnClean().addActionListener(e -> cleanFieldsClick());
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

    private void searchClick(){
        List<Plant> plants = plantRepo.filterPlants(getCriteriaString(), guestView.getTxtFilter().getText());

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

    private void cleanFieldsClick(){setFilter("");}

    private void setTable(DefaultTableModel model){
        guestView.getTabPlant().setModel(model);
        JViewport viewport = new JViewport();
        viewport.setView(guestView.getTabPlant());
        guestView.getScrollPane().setViewport(viewport);
    }

    public String getCriteriaString(){
        String data = null;
        Object selectedItem = guestView.getComboCriteria().getSelectedItem();
        if (selectedItem != null) {
            data = selectedItem.toString();
        }
        return data;
    }

    private void setFilter(String filter){guestView.getTxtFilter().setText(filter);}
}
