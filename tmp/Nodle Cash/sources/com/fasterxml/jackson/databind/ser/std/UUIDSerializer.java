package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.UUID;
import org.objectweb.asm.signature.SignatureVisitor;

public class UUIDSerializer extends StdScalarSerializer<UUID> implements ContextualSerializer {
    static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    protected final Boolean _asBinary;

    public UUIDSerializer() {
        this((Boolean) null);
    }

    private static void _appendInt(int i3, char[] cArr, int i4) {
        _appendShort(i3 >> 16, cArr, i4);
        _appendShort(i3, cArr, i4 + 4);
    }

    private static void _appendShort(int i3, char[] cArr, int i4) {
        char[] cArr2 = HEX_CHARS;
        cArr[i4] = cArr2[(i3 >> 12) & 15];
        cArr[i4 + 1] = cArr2[(i3 >> 8) & 15];
        cArr[i4 + 2] = cArr2[(i3 >> 4) & 15];
        cArr[i4 + 3] = cArr2[i3 & 15];
    }

    private static final byte[] _asBytes(UUID uuid) {
        byte[] bArr = new byte[16];
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        _appendInt((int) (mostSignificantBits >> 32), bArr, 0);
        _appendInt((int) mostSignificantBits, bArr, 4);
        _appendInt((int) (leastSignificantBits >> 32), bArr, 8);
        _appendInt((int) leastSignificantBits, bArr, 12);
        return bArr;
    }

    public boolean _writeAsBinary(JsonGenerator jsonGenerator) {
        Boolean bool = this._asBinary;
        return bool != null ? bool.booleanValue() : !(jsonGenerator instanceof TokenBuffer) && jsonGenerator.canWriteBinaryNatively();
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        visitStringFormat(jsonFormatVisitorWrapper, javaType, JsonValueFormat.UUID);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r2, com.fasterxml.jackson.databind.BeanProperty r3) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r1 = this;
            java.lang.Class r0 = r1.handledType()
            com.fasterxml.jackson.annotation.JsonFormat$Value r2 = r1.findFormatOverrides(r2, r3, r0)
            if (r2 == 0) goto L_0x001c
            com.fasterxml.jackson.annotation.JsonFormat$Shape r2 = r2.getShape()
            com.fasterxml.jackson.annotation.JsonFormat$Shape r3 = com.fasterxml.jackson.annotation.JsonFormat.Shape.BINARY
            if (r2 != r3) goto L_0x0015
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            goto L_0x001d
        L_0x0015:
            com.fasterxml.jackson.annotation.JsonFormat$Shape r3 = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
            if (r2 != r3) goto L_0x001c
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            goto L_0x001d
        L_0x001c:
            r2 = 0
        L_0x001d:
            java.lang.Boolean r3 = r1._asBinary
            boolean r3 = java.util.Objects.equals(r2, r3)
            if (r3 != 0) goto L_0x002a
            com.fasterxml.jackson.databind.ser.std.UUIDSerializer r1 = new com.fasterxml.jackson.databind.ser.std.UUIDSerializer
            r1.<init>(r2)
        L_0x002a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.UUIDSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public UUIDSerializer(Boolean bool) {
        super(UUID.class);
        this._asBinary = bool;
    }

    public boolean isEmpty(SerializerProvider serializerProvider, UUID uuid) {
        return uuid.getLeastSignificantBits() == 0 && uuid.getMostSignificantBits() == 0;
    }

    public void serialize(UUID uuid, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_writeAsBinary(jsonGenerator)) {
            jsonGenerator.writeBinary(_asBytes(uuid));
            return;
        }
        char[] cArr = new char[36];
        long mostSignificantBits = uuid.getMostSignificantBits();
        _appendInt((int) (mostSignificantBits >> 32), cArr, 0);
        cArr[8] = SignatureVisitor.SUPER;
        int i3 = (int) mostSignificantBits;
        _appendShort(i3 >>> 16, cArr, 9);
        cArr[13] = SignatureVisitor.SUPER;
        _appendShort(i3, cArr, 14);
        cArr[18] = SignatureVisitor.SUPER;
        long leastSignificantBits = uuid.getLeastSignificantBits();
        _appendShort((int) (leastSignificantBits >>> 48), cArr, 19);
        cArr[23] = SignatureVisitor.SUPER;
        _appendShort((int) (leastSignificantBits >>> 32), cArr, 24);
        _appendInt((int) leastSignificantBits, cArr, 28);
        jsonGenerator.writeString(cArr, 0, 36);
    }

    private static final void _appendInt(int i3, byte[] bArr, int i4) {
        bArr[i4] = (byte) (i3 >> 24);
        bArr[i4 + 1] = (byte) (i3 >> 16);
        bArr[i4 + 2] = (byte) (i3 >> 8);
        bArr[i4 + 3] = (byte) i3;
    }
}
