import jdk.jfr.Description;
import org.example.adminmicroservice.controllers.AdminController;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.UserFeignClient;
import org.example.adminmicroservice.models.User;
import org.example.adminmicroservice.services.AdminService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminTest {
    @InjectMocks
    private AdminService adminService;

    @Test
    @Description("Verifica que retorna la respuesta correspondiente ante roles invalidos")
    public void Test_Users_By_Role(){
        String role = "frontend-developer";
        ResponseEntity<?> response = adminService.getUsersByRole(role);
        assertFalse(Objects.equals(response.getStatusCode().toString(), "200 OK"));
    }

    @Test
    @Description("Verifica que la response ante fechas erroneas es correcta")
    public void Test_Total_Billed(){
        String origin = "2024-11-11";
        String end = "2020-10-12";
        Object response = this.adminService.getTotalBilled(origin, end);
        assertEquals(response, null);
    }
}
