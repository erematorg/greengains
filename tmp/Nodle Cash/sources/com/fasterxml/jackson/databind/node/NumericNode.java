package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class NumericNode extends ValueNode {
    private static final long serialVersionUID = 1;

    public final double asDouble() {
        return doubleValue();
    }

    public final int asInt() {
        return intValue();
    }

    public final long asLong() {
        return longValue();
    }

    public abstract String asText();

    public abstract BigInteger bigIntegerValue();

    public abstract boolean canConvertToInt();

    public abstract boolean canConvertToLong();

    public abstract BigDecimal decimalValue();

    public abstract double doubleValue();

    public final JsonNodeType getNodeType() {
        return JsonNodeType.NUMBER;
    }

    public abstract int intValue();

    public boolean isNaN() {
        return false;
    }

    public abstract long longValue();

    public abstract JsonParser.NumberType numberType();

    public abstract Number numberValue();

    public final double asDouble(double d2) {
        return doubleValue();
    }

    public final int asInt(int i3) {
        return intValue();
    }

    public final long asLong(long j2) {
        return longValue();
    }
}
