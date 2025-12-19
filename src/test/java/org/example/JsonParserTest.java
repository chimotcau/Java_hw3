package org.example;

import org.example.parser.JsonParser;
import org.example.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @TempDir
    Path tempDir;

    @Test
    public void testParseDocument() throws Exception {
        String json = "{" + "\"cost\": 5," + "\"date\": \"1-1-2023\"," + "\"id\": \"A1\"," + "\"document_type\": \"CONTRACT\"" + "}";

        Path file = tempDir.resolve("contract.json");
        Files.writeString(file, json);

        Document document = JsonParser.parseDocument(file.toString());

        assertInstanceOf(Contract.class, document);
        assertEquals("A1", document.getId());
    }
}