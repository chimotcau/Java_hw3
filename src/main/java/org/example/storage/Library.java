package org.example.storage;

import org.example.model.Document;
import org.example.exception.DocumentNotFoundException;
import org.example.exception.ValueTypeException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Library<T extends Document> {
    private final Map<String, T> documents = new HashMap<>();

    public void put(@Nonnull T document) {
        String id = document.getId();

        documents.put(id, document);
    }

    public void remove(@Nonnull String id) {
        if (!documents.containsKey(id)) {
            throw new DocumentNotFoundException(id);
        }
        documents.remove(id);
    }

    @Nonnull
    public T get(@Nonnull String id) {
        T document = documents.get(id);
        if (document == null) {
            throw new DocumentNotFoundException(id);
        }
        return document;
    }
}