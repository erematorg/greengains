package com.sun.jna;

import java.lang.reflect.Field;

public class StructureReadContext extends FromNativeContext {
    private Field field;
    private Structure structure;

    public StructureReadContext(Structure structure2, Field field2) {
        super(field2.getType());
        this.structure = structure2;
        this.field = field2;
    }

    public Field getField() {
        return this.field;
    }

    public Structure getStructure() {
        return this.structure;
    }
}
