package org.example.model;

import javax.annotation.Nullable;

public enum DocumentType {
    CONTRACT,
    RECEIPT,
    RESUME;

    @Nullable
    public static DocumentType convertString(@Nullable String type_name) {
        if (type_name == null) {
            return null;
        }

        try {
            return DocumentType.valueOf(type_name.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
}