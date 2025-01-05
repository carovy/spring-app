import jdk.jfr.Description;
import org.example.monopatinmicroservice.controllers.MonopatinController;
import org.example.monopatinmicroservice.controllers.ParadaController;
import org.example.monopatinmicroservice.dtos.MonopatinConIdParadaDTO;
import org.example.monopatinmicroservice.dtos.MonopatinKmDTO;
import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.feignClients.ManteinanceFeignClient;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.example.monopatinmicroservice.services.MonopatinService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonopatinTest {
    @InjectMocks
    private MonopatinService monopatinService;
    @Mock
    private MonopatinRepository monopatinRepository;

    private Monopatin m;

    @BeforeEach
    void setUp() {
        m = new Monopatin();
        monopatinService.add(m);
    }

    @Test
    @Description("Verifica que se devuelve null ante la busqueda de un monopatin que no existe")
    public void testMonopatinNotFound(){
       Monopatin m = monopatinService.getById(200L);
       assertNull(m, "El monopatin debería ser null porque no existe ninguno con id 200");
    }

    @Test
    @Description("Verifica que un objeto monopatin se agrega correctamente")
    public void testCorrectPost(){
       when(monopatinRepository.findById(m.getId())).thenReturn(Optional.of(m));
       Long id = m.getId();
       Monopatin m2 = monopatinService.getById(id);
       assertTrue(m2 != null, "el monopatin no fue agregado correctamente");
    }


}