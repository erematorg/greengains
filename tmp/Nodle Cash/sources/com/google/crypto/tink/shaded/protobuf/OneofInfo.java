package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;

@CheckReturnValue
final class OneofInfo {
    private final Field caseField;
    private final int id;
    private final Field valueField;

    public OneofInfo(int i3, Field field, Field field2) {
        this.id = i3;
        this.caseField = field;
        this.valueField = field2;
    }

    public Field getCaseField() {
        return this.caseField;
    }

    public int getId() {
        return this.id;
    }

    public Field getValueField() {
        return this.valueField;
    }
}
