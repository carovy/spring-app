import jdk.jfr.Description;
import org.example.maintenancemicroservice.MaintenanceMicroserviceApplication;
import org.example.maintenancemicroservice.controllers.MantenimientoController;
import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.example.maintenancemicroservice.services.MantenimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MaintenanceTest {
    @InjectMocks
    private MantenimientoService mantenimientoService;
    @Mock
    private MantenimientoRepository mantenimientoRepository;

    @Test
    public void testIdMonopatin(){
        Monopatin monopatin = new Monopatin();
        monopatin.setId(1L);
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setId_monopatin(monopatin.getId());

        when(mantenimientoService.findByMonopatinId(monopatin.getId())).thenReturn(mantenimiento);
        Mantenimiento m = mantenimientoService.findByMonopatinId(monopatin.getId());
        assertEquals(m.getId_monopatin(), monopatin.getId(), "el mantenimiento no se creó corectamente");
    }
}
