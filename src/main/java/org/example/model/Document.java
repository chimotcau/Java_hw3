package org.example.model;

import javax.annotation.Nonnull;

public abstract class Document {
    @Nonnull
    protected final String id;

    @Nonnull
    protected final DocumentType document_type;

    protected Document(@Nonnull String id, @Nonnull DocumentType document_type) {
        this.id = id;
        this.document_type = document_type;
    }

    @Nonnull
    public String getId() {
        return id;
    }

    @Nonnull
    public DocumentType getDocument_type() {
        return document_type;
    }

    @Override
    public abstract String toString();
}
