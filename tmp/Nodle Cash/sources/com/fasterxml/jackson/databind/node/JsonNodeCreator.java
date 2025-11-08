package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.util.RawValue;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface JsonNodeCreator {
    ArrayNode arrayNode();

    ArrayNode arrayNode(int i3);

    ValueNode binaryNode(byte[] bArr);

    ValueNode binaryNode(byte[] bArr, int i3, int i4);

    ValueNode booleanNode(boolean z2);

    ValueNode nullNode();

    ValueNode numberNode(byte b3);

    ValueNode numberNode(double d2);

    ValueNode numberNode(float f2);

    ValueNode numberNode(int i3);

    ValueNode numberNode(long j2);

    ValueNode numberNode(Byte b3);

    ValueNode numberNode(Double d2);

    ValueNode numberNode(Float f2);

    ValueNode numberNode(Integer num);

    ValueNode numberNode(Long l2);

    ValueNode numberNode(Short sh);

    ValueNode numberNode(BigDecimal bigDecimal);

    ValueNode numberNode(BigInteger bigInteger);

    ValueNode numberNode(short s3);

    ObjectNode objectNode();

    ValueNode pojoNode(Object obj);

    ValueNode rawValueNode(RawValue rawValue);

    ValueNode textNode(String str);
}
