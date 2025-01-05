package org.example.maintenancemicroservice.utils;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataLoaderHelper {

    private final MantenimientoRepository maintRepo;

    public DataLoaderHelper(MantenimientoRepository m) {
        this.maintRepo = m;
    }

    @Transactional
    public void loadMaintenances() {
        List<String[]> mantenimientos = CSVReaderHelper.readCSV("MaintenanceMicrosevice/src/main/java/org/example/maintenancemicroservice/utils/mantenimientos.csv");

        for (String[] manten : mantenimientos.subList(1, mantenimientos.size())) {
            Mantenimiento m = new Mantenimiento();
            m.setId_monopatin(Long.parseLong(manten[1]));
            m.setEstado(manten[2]);
            maintRepo.save(m);
        }
    }
}
