package com.fasterxml.jackson.databind.deser.std;

import A.a;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class UUIDDeserializer extends FromStringDeserializer<UUID> {
    static final int[] HEX_DIGITS;
    private static final long serialVersionUID = 1;

    static {
        int[] iArr = new int[127];
        HEX_DIGITS = iArr;
        Arrays.fill(iArr, -1);
        for (int i3 = 0; i3 < 10; i3++) {
            HEX_DIGITS[i3 + 48] = i3;
        }
        for (int i4 = 0; i4 < 6; i4++) {
            int[] iArr2 = HEX_DIGITS;
            int i5 = i4 + 10;
            iArr2[i4 + 97] = i5;
            iArr2[i4 + 65] = i5;
        }
    }

    public UUIDDeserializer() {
        super(UUID.class);
    }

    private UUID _badFormat(String str, DeserializationContext deserializationContext) throws IOException {
        return (UUID) deserializationContext.handleWeirdStringValue(handledType(), str, "UUID has to be represented by standard 36-char representation", new Object[0]);
    }

    private UUID _fromBytes(byte[] bArr, DeserializationContext deserializationContext) throws JsonMappingException {
        if (bArr.length == 16) {
            return new UUID(_long(bArr, 0), _long(bArr, 8));
        }
        throw InvalidFormatException.from(deserializationContext.getParser(), a.m(new StringBuilder("Can only construct UUIDs from byte[16]; got "), " bytes", bArr.length), bArr, handledType());
    }

    private static int _int(byte[] bArr, int i3) {
        return (bArr[i3 + 3] & 255) | (bArr[i3] << Ascii.CAN) | ((bArr[i3 + 1] & 255) << 16) | ((bArr[i3 + 2] & 255) << 8);
    }

    private static long _long(byte[] bArr, int i3) {
        return ((((long) _int(bArr, i3 + 4)) << 32) >>> 32) | (((long) _int(bArr, i3)) << 32);
    }

    public int _badChar(String str, int i3, DeserializationContext deserializationContext, char c3) throws JsonMappingException {
        throw deserializationContext.weirdStringException(str, handledType(), String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", new Object[]{Character.valueOf(c3), Integer.toHexString(c3)}));
    }

    public int byteFromChars(String str, int i3, DeserializationContext deserializationContext) throws JsonMappingException {
        char charAt = str.charAt(i3);
        int i4 = i3 + 1;
        char charAt2 = str.charAt(i4);
        if (charAt <= 127 && charAt2 <= 127) {
            int[] iArr = HEX_DIGITS;
            int i5 = iArr[charAt2] | (iArr[charAt] << 4);
            if (i5 >= 0) {
                return i5;
            }
        }
        return (charAt > 127 || HEX_DIGITS[charAt] < 0) ? _badChar(str, i3, deserializationContext, charAt) : _badChar(str, i4, deserializationContext, charAt2);
    }

    public Object getEmptyValue(DeserializationContext deserializationContext) {
        return new UUID(0, 0);
    }

    public int intFromChars(String str, int i3, DeserializationContext deserializationContext) throws JsonMappingException {
        return byteFromChars(str, i3 + 6, deserializationContext) + (byteFromChars(str, i3, deserializationContext) << 24) + (byteFromChars(str, i3 + 2, deserializationContext) << 16) + (byteFromChars(str, i3 + 4, deserializationContext) << 8);
    }

    public int shortFromChars(String str, int i3, DeserializationContext deserializationContext) throws JsonMappingException {
        return byteFromChars(str, i3 + 2, deserializationContext) + (byteFromChars(str, i3, deserializationContext) << 8);
    }

    public UUID _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        if (str.length() == 36) {
            if (!(str.charAt(8) == '-' && str.charAt(13) == '-' && str.charAt(18) == '-' && str.charAt(23) == '-')) {
                _badFormat(str, deserializationContext);
            }
            int shortFromChars = shortFromChars(str, 24, deserializationContext);
            return new UUID((((long) intFromChars(str, 0, deserializationContext)) << 32) + ((((long) shortFromChars(str, 9, deserializationContext)) << 16) | ((long) shortFromChars(str, 14, deserializationContext))), ((((long) intFromChars(str, 28, deserializationContext)) << 32) >>> 32) | (((long) (shortFromChars | (shortFromChars(str, 19, deserializationContext) << 16))) << 32));
        } else if (str.length() == 24) {
            return _fromBytes(Base64Variants.getDefaultVariant().decode(str), deserializationContext);
        } else {
            return _badFormat(str, deserializationContext);
        }
    }

    public UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException {
        if (obj instanceof byte[]) {
            return _fromBytes((byte[]) obj, deserializationContext);
        }
        return (UUID) super._deserializeEmbedded(obj, deserializationContext);
    }
}
