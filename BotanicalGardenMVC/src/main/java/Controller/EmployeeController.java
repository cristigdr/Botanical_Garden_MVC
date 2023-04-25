package Controller;

import Model.Plant;
import Model.PlantRepository;
import View.EmployeeView;
import View.GuestView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EmployeeController {
    private PlantRepository plantRepo;
    private EmployeeView empView;

    public EmployeeController() {
        this.plantRepo = new PlantRepository();
        this.empView = new EmployeeView();
        populateTable();

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

    
}
