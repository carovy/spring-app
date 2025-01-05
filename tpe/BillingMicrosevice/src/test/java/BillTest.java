
import org.example.billingmicroservice.dtos.BillDTO;
import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.repositories.BillRepository;
import org.example.billingmicroservice.services.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillTest {
    @InjectMocks
    private BillService billService;
    @Mock
    private BillRepository billRepository;

    private BillDTO b;
    private Bill bb;

    @BeforeEach
    void setUp() {
        b = new BillDTO(LocalDate.now(), 40F, 5F);
        bb = new Bill("1", b.getFechaInicioFacturacionNueva(), 40F, 5F);
    }

    @Test
    void TEST_SetNewBill() throws Exception {
        when(billRepository.save(any(Bill.class))).thenReturn(bb);
        Bill bill = billRepository.save(bb);

        when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));

        Bill bill2 = billService.getBillById(bill.getId());

        assertNotNull(bill2, "La cuenta no se anuló correctamente");
    }



}
