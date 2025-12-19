package org.example.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Contract extends Document {
    @Nullable
    private final Integer cost;

    @Nullable
    private final String date;

    public Contract(@Nonnull String id, @Nullable Integer cost, @Nullable String date) {
        super(id, DocumentType.CONTRACT);
        this.cost = cost;
        this.date = date;
    }

    @Nullable
    public Integer getCost() {
        return cost;
    }

    @Nullable
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Contract{id='" + getId() + "', cost=" + cost + ", date='" + date + "'}";
    }

}
