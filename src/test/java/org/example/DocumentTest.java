package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    @Test
    public void testContract() {
        Contract contract = new Contract("A1", 5, "1-1-2023");

        assertEquals("A1", contract.getId());
        assertEquals(5, contract.getCost());
        assertEquals("1-1-2023", contract.getDate());
    }
}