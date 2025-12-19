package org.example;

import org.example.storage.Library;
import org.example.exception.DocumentNotFoundException;
import org.example.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testPut() {
        Library<Contract> library = new Library<>();
        Contract contract = new Contract("A1", 5, "1-1-2023");

        library.put(contract);
        Contract retrieved = library.get("A1");

        assertEquals(contract, retrieved);
    }

    @Test
    public void testGet() {
        Library<Contract> library = new Library<>();
        Contract contract = new Contract("A1", 5, "1-1-2023");
        library.put(contract);

        Contract retrieved = library.get("A1");

        assertEquals("A1", retrieved.getId());
        assertEquals(5, retrieved.getCost());
    }

    @Test
    public void testRemove() {
        Library<Contract> library = new Library<>();
        Contract contract = new Contract("A1", 5, "1-1-2023");
        library.put(contract);

        library.remove("A1");

        assertThrows(DocumentNotFoundException.class, () -> {
            library.get("A1");
        });
    }
}