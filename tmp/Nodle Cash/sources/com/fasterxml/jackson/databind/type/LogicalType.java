package com.fasterxml.jackson.databind.type;

import java.util.Collection;
import java.util.Map;

public enum LogicalType {
    Array,
    Collection,
    Map,
    POJO,
    Untyped,
    Integer,
    Float,
    Boolean,
    Enum,
    Textual,
    Binary,
    DateTime,
    OtherScalar;

    public static LogicalType fromClass(Class<?> cls, LogicalType logicalType) {
        return cls.isEnum() ? Enum : cls.isArray() ? cls == byte[].class ? Binary : Array : Collection.class.isAssignableFrom(cls) ? Collection : Map.class.isAssignableFrom(cls) ? Map : cls == String.class ? Textual : logicalType;
    }
}
