package Controller;

import Model.Language;
import Model.Plant;
import Model.PlantRepository;
import View.GuestView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GuestController implements Observer {
    final private PlantRepository plantRepo;
    final private GuestView guestView;

    final private Language language;

    public GuestController() {
        this.plantRepo = new PlantRepository();
        this.guestView = new GuestView();
        this.language = new Language();
        language.addObserver(this);
        populateTable();

        this.guestView.getBtnSearch().addActionListener(e -> searchClick());

        this.guestView.getBtnRefresh().addActionListener(e -> populateTable());

        this.guestView.getBtnClean().addActionListener(e -> cleanFieldsClick());

        this.guestView.getBtnRo().addActionListener(e -> language.setLanguage("ro"));

        this.guestView.getBtnEn().addActionListener(e -> language.setLanguage("en"));

        this.guestView.getBtnEs().addActionListener(e -> language.setLanguage("es"));

        this.guestView.getBtnFr().addActionListener(e -> language.setLanguage("fr"));

        updateView();
    }

    private void populateTable(){
        List<Plant> plants = plantRepo.getPlants();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };

        model.addColumn(language.getString("idLabelTab"));
        model.addColumn(language.getString("nameLabelTab"));
        model.addColumn(language.getString("typeLabelTab"));
        model.addColumn(language.getString("speciesLabelTab"));
        model.addColumn(language.getString("carnivorousLabelTab"));
        model.addColumn(language.getString("zoneLabelTab"));

        for (Plant p : plants) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getType(), p.getSpecies(), p.getCarnivorous(), p.getZone()});
        }
        setTable(model);
    }

    private void searchClick(){
        List<Plant> plants = plantRepo.filterPlants(getCriteriaValue(), guestView.getTxtFilter().getText());

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };

        model.addColumn(language.getString("idLabelTab"));
        model.addColumn(language.getString("nameLabelTab"));
        model.addColumn(language.getString("typeLabelTab"));
        model.addColumn(language.getString("speciesLabelTab"));
        model.addColumn(language.getString("carnivorousLabelTab"));
        model.addColumn(language.getString("zoneLabelTab"));

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

    public void update(Observable o, Object arg) {
        updateView();
    }

    private void updateView() {
        guestView.getWelcomeLabel().setText((language.getString("welcomeLabel")));
        guestView.getFilterDataLabel().setText((language.getString("filterDataLabel")));
        guestView.getCriteriaLabel().setText((language.getString("criteriaLabel")));
        guestView.getFilterLabel().setText((language.getString("filterLabel")));


        guestView.getBtnClean().setText(language.getString("cleanButton"));
        guestView.getBtnSearch().setText(language.getString("searchButton"));
        guestView.getBtnRefresh().setText(language.getString("refreshButton"));

        //combobox
        guestView.getComboCriteria().removeAllItems();

        String[] criterias = {language.getString("typeCBLabel"), language.getString("speciesCBLabel"), language.getString("carnivorousCBLabel"), language.getString("zoneCBLabel")};
        for (String criteria : criterias) {
            guestView.getComboCriteria().addItem(criteria);
        }

        //column headers
        String[] columnNames = {
                language.getString("idLabelTab"),
                language.getString("nameLabelTab"),
                language.getString("typeLabelTab"),
                language.getString("speciesLabelTab"),
                language.getString("carnivorousLabelTab"),
                language.getString("zoneLabelTab")
        };
        DefaultTableModel model = (DefaultTableModel) guestView.getTabPlant().getModel();
        model.setColumnIdentifiers(columnNames);
    }

    public String getCriteriaValue(){
        if(getCriteriaString().equals(language.getString("typeCBLabel"))) {
            return "tip";
        }
        if(getCriteriaString().equals(language.getString("speciesCBLabel"))) {
            return "specie";
        }
        if(getCriteriaString().equals(language.getString("carnivorousCBLabel"))) {
            return "planta_carnivora";
        }
        if(getCriteriaString().equals(language.getString("zoneCBLabel"))) {
            return "zona_gradina_botanica";
        }
        return "no criteria choosed";
    }
}
