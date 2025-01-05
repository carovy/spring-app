
import jdk.jfr.Description;
import org.example.reportsmicroservice.dtos.MonopatinKmDTO;
import org.example.reportsmicroservice.entities.ReporteMonopatinesSinPausa;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.feignClients.MonopatinFeignClient;
import org.example.reportsmicroservice.services.ReportsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReporteMonopatinTest {
    @InjectMocks
    private ReportsService repService;
    @Mock
    private MonopatinFeignClient monopatinFeignClient;

    ResponseEntity<ArrayList<MonopatinKmDTO>> monopatines;
    ArrayList<MonopatinKmDTO> mons;

    @BeforeEach
    void setUp() {
        mons = new ArrayList<>();
        mons.add(new MonopatinKmDTO(1L, 40.0F, 300, 350));
        mons.add(new MonopatinKmDTO(2L, 90.2F, 900, 1230));
        mons.add(new MonopatinKmDTO(3L, 20F, 200, 290));
        mons.add(new MonopatinKmDTO(4L, 75.8F, 782, 890));
        monopatines = ResponseEntity.status(200).body(mons);
    }

    @Test
    @Description("Corrobora que la respuesta del feign client sea la correcta")
    public void testCorrectResponse() {
        when(monopatinFeignClient.getMonopatinesPorKM(true)).thenReturn(monopatines);
        assertEquals(Objects.requireNonNull(monopatinFeignClient.getMonopatinesPorKM(true).getBody()).getFirst().getClass(), mons.getFirst().getClass());
    }

    @Test
    @Description("Corrobora que si se pide un reporte con pausas se devuelve un array de objetos reportes monopatines uso")
    public void testServiceCorrectObject() {
        when(monopatinFeignClient.getMonopatinesPorKM(true)).thenReturn(monopatines);
        ArrayList<Object> objs = repService.getReporteUsoMonopatinKm(true);
        assertEquals(objs.getFirst().getClass(), ReporteMonopatinesUso.class);
    }
}
