package com.sun.jna;

import java.lang.reflect.Field;

public class StructureWriteContext extends ToNativeContext {
    private Field field;
    private Structure struct;

    public StructureWriteContext(Structure structure, Field field2) {
        this.struct = structure;
        this.field = field2;
    }

    public Field getField() {
        return this.field;
    }

    public Structure getStructure() {
        return this.struct;
    }
}
