package org.example.billingmicroservice.utils;

import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.repositories.BillRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoaderHelper {

    private final BillRepository billRepository;

    public DataLoaderHelper(BillRepository b) {
        this.billRepository = b;
    }

    @Transactional
    public void loadBillings() throws ParseException {
        List<String[]> billings = CSVReaderHelper.readCSV("BillingMicrosevice/src/main/java/org/example/billingmicroservice/utils/billing.csv");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (String[] bill : billings.subList(1, billings.size())) {
            Bill b = new Bill();
            b.setId(bill[0]);
            b.setFecha(LocalDate.parse(bill[1]));
            b.setPrice(Float.parseFloat(bill[2]));
            b.setAdditionalPrice(Float.parseFloat(bill[3]));
            billRepository.save(b);
        }
    }
}
