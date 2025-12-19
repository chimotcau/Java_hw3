package org.example.parser;

import org.example.exception.BaseException;
import org.example.exception.FileNotFoundException;
import org.example.exception.MissingFieldException;
import org.example.exception.ValueTypeException;
import org.example.exception.JsonFormatException;
import org.example.model.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JsonParser {
    @Nonnull
    public static Document parseDocument(@Nonnull String file_name) {
        Path path = Paths.get(file_name);
        if(Files.exists(path) == false) {
            throw new FileNotFoundException(file_name);
        }

        String json_content;
        try {
            json_content = Files.readString(path);
        }
        catch (Exception exception) {
            throw new JsonFormatException("Can't read file: " + file_name);
        }

        Map<String, Object> jsonMap = parseJsonString(json_content);

        String id = stringField(jsonMap, "id");
        String docTypeStr = stringField(jsonMap, "document_type");

        DocumentType documentType = DocumentType.convertString(docTypeStr);
        if (documentType == null) {
            throw new MissingFieldException("it should be CONTRACT, RECEIPT, RESUME");
        }

        switch (documentType) {
            case CONTRACT:
                Integer cost = integerField(jsonMap, "cost");
                String date = stringField(jsonMap, "date");
                return new Contract(id, cost, date);

            case RECEIPT:
                Integer moneyAmount = integerField(jsonMap, "money_amount");
                return new Receipt(id, moneyAmount);

            case RESUME:
                String name = stringField(jsonMap, "name");
                return new Resume(id, name);

            default:
                throw new MissingFieldException("it should be CONTRACT, RECEIPT, RESUME");
        }
    }

    @Nonnull
    private static Map<String, Object> parseJsonString(@Nonnull String json) {
        Map<String, Object> result = new HashMap<>();
        json = json.trim();

        json = json.substring(1, json.length() - 1).trim();

        if (json.isEmpty()) {
            return result;
        }

        String[] pairs = splitJsonPairs(json);

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) {
                throw new JsonFormatException("invalid key-value pair: " + pair);
            }

            String key = parseStringValue(keyValue[0].trim());
            String valueStr = keyValue[1].trim();

            Object value;
            if (valueStr.startsWith("\"")) {
                value = parseStringValue(valueStr);
            } else {
                value = parseIntegerValue(valueStr, key);
            }

            result.put(key, value);
        }

        return result;
    }

    @Nonnull
    private static String parseStringValue(@Nonnull String str) {
        return str.substring(1, str.length() - 1);
    }

    @Nonnull
    private static Integer parseIntegerValue(@Nonnull String str, @Nonnull String fieldName) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new ValueTypeException(fieldName, str);
        }
    }

    @Nonnull
    private static String[] splitJsonPairs(@Nonnull String json) {
        List<String> pairs = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '"') {
                if (i == 0 || json.charAt(i - 1) != '\\') {
                    inQuotes = !inQuotes;
                }
            } else if (!inQuotes && c == ',') {
                pairs.add(current.toString().trim());
                current = new StringBuilder();
                continue;
            }

            current.append(c);
        }

        if (current.length() > 0) {
            pairs.add(current.toString().trim());
        }

        return pairs.toArray(new String[0]);
    }

    @Nonnull
    private static String stringField(@Nonnull Map<String, Object> jsonMap, @Nonnull String fieldName) {
        Object value = jsonMap.get(fieldName);
        if (value == null) {
            throw new MissingFieldException(fieldName);
        }
        if (!(value instanceof String)) {
            throw new ValueTypeException(fieldName, value.getClass().getSimpleName());
        }
        return (String) value;
    }

    @Nullable
    private static Integer integerField(@Nonnull Map<String, Object> jsonMap, @Nonnull String fieldName) {
        Object value = jsonMap.get(fieldName);
        if (value == null) {
            return null;
        }
        if (!(value instanceof Integer)) {
            throw new ValueTypeException(fieldName, value.getClass().getSimpleName());
        }
        return (Integer) value;
    }
}
