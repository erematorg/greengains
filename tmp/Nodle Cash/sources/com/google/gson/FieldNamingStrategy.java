package com.google.gson;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public interface FieldNamingStrategy {
    List<String> alternateNames(Field field) {
        return Collections.emptyList();
    }

    String translateName(Field field);
}
