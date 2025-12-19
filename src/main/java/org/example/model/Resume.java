package org.example.model;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

public class Resume extends Document {
    @Nullable
    private final String name;

    public Resume(@Nonnull String id, @Nullable String name) {
        super(id, DocumentType.RESUME);
        this.name = name;
    }

    @Nullable
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Resume{id='" + getId() + "', name='" + name + "'}";
    }
}
