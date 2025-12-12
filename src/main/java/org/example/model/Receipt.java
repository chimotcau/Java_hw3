package org.example.model;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

public class Receipt extends Document {
    @Nullable
    private final Integer money_amount;

    public Receipt(@Nonnull String id, @Nullable Integer money_amount) {
        super(id, DocumentType.RECEIPT);
        this.money_amount = money_amount;
    }

    @Nullable
    Integer getMoney_amount() {
        return money_amount;
    }

    @Override
    public String toString() {
        return "Receipt{id='" + getId() + "', money_amount=" + money_amount + "}";
    }
}
