package com.fasterxml.jackson.databind.node;

import androidx.collection.SieveCacheKt;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class LongNode extends NumericNode {
    protected final long _value;

    public LongNode(long j2) {
        this._value = j2;
    }

    public static LongNode valueOf(long j2) {
        return new LongNode(j2);
    }

    public boolean asBoolean(boolean z2) {
        return this._value != 0;
    }

    public String asText() {
        return NumberOutput.toString(this._value);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    public boolean canConvertToInt() {
        long j2 = this._value;
        return j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask;
    }

    public boolean canConvertToLong() {
        return true;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    public double doubleValue() {
        return (double) this._value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof LongNode)) {
            return ((LongNode) obj)._value == this._value;
        }
        return false;
    }

    public float floatValue() {
        return (float) this._value;
    }

    public int hashCode() {
        long j2 = this._value;
        return ((int) j2) ^ ((int) (j2 >> 32));
    }

    public int intValue() {
        return (int) this._value;
    }

    public boolean isIntegralNumber() {
        return true;
    }

    public boolean isLong() {
        return true;
    }

    public long longValue() {
        return this._value;
    }

    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.LONG;
    }

    public Number numberValue() {
        return Long.valueOf(this._value);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this._value);
    }

    public short shortValue() {
        return (short) ((int) this._value);
    }
}
