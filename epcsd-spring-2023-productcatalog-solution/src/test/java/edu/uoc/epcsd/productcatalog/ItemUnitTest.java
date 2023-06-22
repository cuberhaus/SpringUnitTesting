package edu.uoc.epcsd.productcatalog;
//package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Item;
import edu.uoc.epcsd.productcatalog.domain.ItemStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ItemUnitTest {
    // Verifiquem que en crear un item com a inoperatiu, el status final d'aquest item és NON_OPERATIONAL
    @Test void testItemNonOperational() {
        Item item = Item.builder()
                .status(ItemStatus.NON_OPERATIONAL)
//                .serialNumber("12345")
//                .productId(100L)
                .build();
        Assertions.assertEquals(ItemStatus.NON_OPERATIONAL, item.getStatus());

    }

}
