package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory implements Serializable, JsonNodeCreator {
    protected static final int MAX_ELEMENT_INDEX_FOR_INSERT = 9999;
    private static final JsonNodeFactory decimalsAsIs = new JsonNodeFactory(true);
    private static final JsonNodeFactory decimalsNormalized;
    public static final JsonNodeFactory instance;
    private static final long serialVersionUID = 1;
    private final boolean _cfgBigDecimalExact;

    static {
        JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
        decimalsNormalized = jsonNodeFactory;
        instance = jsonNodeFactory;
    }

    public JsonNodeFactory(boolean z2) {
        this._cfgBigDecimalExact = z2;
    }

    public static JsonNodeFactory withExactBigDecimals(boolean z2) {
        return z2 ? decimalsAsIs : decimalsNormalized;
    }

    public boolean _inIntRange(long j2) {
        return ((long) ((int) j2)) == j2;
    }

    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    public int getMaxElementIndexForInsert() {
        return 9999;
    }

    public JsonNode missingNode() {
        return MissingNode.getInstance();
    }

    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    public ValueNode pojoNode(Object obj) {
        return new POJONode(obj);
    }

    public ValueNode rawValueNode(RawValue rawValue) {
        return new POJONode(rawValue);
    }

    public ArrayNode arrayNode(int i3) {
        return new ArrayNode(this, i3);
    }

    public BooleanNode booleanNode(boolean z2) {
        return z2 ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    public TextNode textNode(String str) {
        return TextNode.valueOf(str);
    }

    public JsonNodeFactory() {
        this(false);
    }

    public BinaryNode binaryNode(byte[] bArr) {
        return BinaryNode.valueOf(bArr);
    }

    public BinaryNode binaryNode(byte[] bArr, int i3, int i4) {
        return BinaryNode.valueOf(bArr, i3, i4);
    }

    public NumericNode numberNode(byte b3) {
        return IntNode.valueOf(b3);
    }

    public ValueNode numberNode(Byte b3) {
        return b3 == null ? nullNode() : IntNode.valueOf(b3.intValue());
    }

    public NumericNode numberNode(short s3) {
        return ShortNode.valueOf(s3);
    }

    public ValueNode numberNode(Short sh) {
        return sh == null ? nullNode() : ShortNode.valueOf(sh.shortValue());
    }

    public NumericNode numberNode(int i3) {
        return IntNode.valueOf(i3);
    }

    public ValueNode numberNode(Integer num) {
        return num == null ? nullNode() : IntNode.valueOf(num.intValue());
    }

    public NumericNode numberNode(long j2) {
        return LongNode.valueOf(j2);
    }

    public ValueNode numberNode(Long l2) {
        if (l2 == null) {
            return nullNode();
        }
        return LongNode.valueOf(l2.longValue());
    }

    public ValueNode numberNode(BigInteger bigInteger) {
        if (bigInteger == null) {
            return nullNode();
        }
        return BigIntegerNode.valueOf(bigInteger);
    }

    public NumericNode numberNode(float f2) {
        return FloatNode.valueOf(f2);
    }

    public ValueNode numberNode(Float f2) {
        return f2 == null ? nullNode() : FloatNode.valueOf(f2.floatValue());
    }

    public NumericNode numberNode(double d2) {
        return DoubleNode.valueOf(d2);
    }

    public ValueNode numberNode(Double d2) {
        return d2 == null ? nullNode() : DoubleNode.valueOf(d2.doubleValue());
    }

    public ValueNode numberNode(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return nullNode();
        }
        if (this._cfgBigDecimalExact) {
            return DecimalNode.valueOf(bigDecimal);
        }
        if (bigDecimal.signum() == 0) {
            return DecimalNode.ZERO;
        }
        try {
            bigDecimal = bigDecimal.stripTrailingZeros();
        } catch (ArithmeticException unused) {
        }
        return DecimalNode.valueOf(bigDecimal);
    }
}
