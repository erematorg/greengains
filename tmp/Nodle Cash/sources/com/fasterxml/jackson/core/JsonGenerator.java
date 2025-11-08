package com.fasterxml.jackson.core;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class JsonGenerator implements Closeable, Flushable, Versioned {
    protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_BINARY_WRITE_CAPABILITIES;
    protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_TEXTUAL_WRITE_CAPABILITIES;
    protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_WRITE_CAPABILITIES;
    protected PrettyPrinter _cfgPrettyPrinter;

    /* renamed from: com.fasterxml.jackson.core.JsonGenerator$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.fasterxml.jackson.core.type.WritableTypeId$Inclusion[] r0 = com.fasterxml.jackson.core.type.WritableTypeId.Inclusion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion = r0
                com.fasterxml.jackson.core.type.WritableTypeId$Inclusion r1 = com.fasterxml.jackson.core.type.WritableTypeId.Inclusion.PARENT_PROPERTY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.core.type.WritableTypeId$Inclusion r1 = com.fasterxml.jackson.core.type.WritableTypeId.Inclusion.PAYLOAD_PROPERTY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.core.type.WritableTypeId$Inclusion r1 = com.fasterxml.jackson.core.type.WritableTypeId.Inclusion.METADATA_PROPERTY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.core.type.WritableTypeId$Inclusion r1 = com.fasterxml.jackson.core.type.WritableTypeId.Inclusion.WRAPPER_OBJECT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.core.type.WritableTypeId$Inclusion r1 = com.fasterxml.jackson.core.type.WritableTypeId.Inclusion.WRAPPER_ARRAY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonGenerator.AnonymousClass1.<clinit>():void");
        }
    }

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        FLUSH_PASSED_TO_STREAM(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        ESCAPE_NON_ASCII(false),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNKNOWN(false),
        USE_FAST_DOUBLE_WRITER(false),
        WRITE_HEX_UPPER_CASE(true);
        
        private final boolean _defaultState;
        private final int _mask;

        private Feature(boolean z2) {
            this._defaultState = z2;
            this._mask = 1 << ordinal();
        }

        public static int collectDefaults() {
            int i3 = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i3 |= feature.getMask();
                }
            }
            return i3;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i3) {
            return (this._mask & i3) != 0;
        }

        public int getMask() {
            return this._mask;
        }
    }

    static {
        JacksonFeatureSet<StreamWriteCapability> fromDefaults = JacksonFeatureSet.fromDefaults(StreamWriteCapability.values());
        DEFAULT_WRITE_CAPABILITIES = fromDefaults;
        DEFAULT_TEXTUAL_WRITE_CAPABILITIES = fromDefaults.with(StreamWriteCapability.CAN_WRITE_FORMATTED_NUMBERS);
        DEFAULT_BINARY_WRITE_CAPABILITIES = fromDefaults.with(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY);
    }

    public void _copyCurrentContents(JsonParser jsonParser) throws IOException {
        int i3 = 1;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != null) {
                switch (nextToken.id()) {
                    case 1:
                        writeStartObject();
                        break;
                    case 2:
                        writeEndObject();
                        i3--;
                        if (i3 != 0) {
                            continue;
                        } else {
                            return;
                        }
                    case 3:
                        writeStartArray();
                        break;
                    case 4:
                        writeEndArray();
                        i3--;
                        if (i3 != 0) {
                            continue;
                        } else {
                            return;
                        }
                    case 5:
                        writeFieldName(jsonParser.getCurrentName());
                        continue;
                    case 6:
                        if (!jsonParser.hasTextCharacters()) {
                            writeString(jsonParser.getText());
                            break;
                        } else {
                            writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                            continue;
                        }
                    case 7:
                        JsonParser.NumberType numberType = jsonParser.getNumberType();
                        if (numberType != JsonParser.NumberType.INT) {
                            if (numberType != JsonParser.NumberType.BIG_INTEGER) {
                                writeNumber(jsonParser.getLongValue());
                                break;
                            } else {
                                writeNumber(jsonParser.getBigIntegerValue());
                                break;
                            }
                        } else {
                            writeNumber(jsonParser.getIntValue());
                            continue;
                        }
                    case 8:
                        JsonParser.NumberType numberType2 = jsonParser.getNumberType();
                        if (numberType2 != JsonParser.NumberType.BIG_DECIMAL) {
                            if (numberType2 != JsonParser.NumberType.FLOAT) {
                                writeNumber(jsonParser.getDoubleValue());
                                break;
                            } else {
                                writeNumber(jsonParser.getFloatValue());
                                break;
                            }
                        } else {
                            writeNumber(jsonParser.getDecimalValue());
                            continue;
                        }
                    case 9:
                        writeBoolean(true);
                        continue;
                    case 10:
                        writeBoolean(false);
                        continue;
                    case 11:
                        writeNull();
                        continue;
                    case 12:
                        writeObject(jsonParser.getEmbeddedObject());
                        continue;
                    default:
                        throw new IllegalStateException("Internal error: unknown current token, " + nextToken);
                }
                i3++;
            } else {
                return;
            }
        }
    }

    public void _reportError(String str) throws JsonGenerationException {
        throw new JsonGenerationException(str, this);
    }

    public void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type ".concat(getClass().getName()));
    }

    public final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    public final void _verifyOffsets(int i3, int i4, int i5) {
        if (i4 < 0 || i4 + i5 > i3) {
            throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i3)}));
        }
    }

    public void _writeSimpleObject(Object obj) throws IOException {
        if (obj == null) {
            writeNull();
        } else if (obj instanceof String) {
            writeString((String) obj);
        } else {
            if (obj instanceof Number) {
                Number number = (Number) obj;
                if (number instanceof Integer) {
                    writeNumber(number.intValue());
                    return;
                } else if (number instanceof Long) {
                    writeNumber(number.longValue());
                    return;
                } else if (number instanceof Double) {
                    writeNumber(number.doubleValue());
                    return;
                } else if (number instanceof Float) {
                    writeNumber(number.floatValue());
                    return;
                } else if (number instanceof Short) {
                    writeNumber(number.shortValue());
                    return;
                } else if (number instanceof Byte) {
                    writeNumber((short) number.byteValue());
                    return;
                } else if (number instanceof BigInteger) {
                    writeNumber((BigInteger) number);
                    return;
                } else if (number instanceof BigDecimal) {
                    writeNumber((BigDecimal) number);
                    return;
                } else if (number instanceof AtomicInteger) {
                    writeNumber(((AtomicInteger) number).get());
                    return;
                } else if (number instanceof AtomicLong) {
                    writeNumber(((AtomicLong) number).get());
                    return;
                }
            } else if (obj instanceof byte[]) {
                writeBinary((byte[]) obj);
                return;
            } else if (obj instanceof Boolean) {
                writeBoolean(((Boolean) obj).booleanValue());
                return;
            } else if (obj instanceof AtomicBoolean) {
                writeBoolean(((AtomicBoolean) obj).get());
                return;
            }
            throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + obj.getClass().getName() + ")");
        }
    }

    public void assignCurrentValue(Object obj) {
        setCurrentValue(obj);
    }

    public boolean canOmitFields() {
        return true;
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    public boolean canWriteBinaryNatively() {
        return false;
    }

    public boolean canWriteFormattedNumbers() {
        return false;
    }

    public boolean canWriteObjectId() {
        return false;
    }

    public boolean canWriteTypeId() {
        return false;
    }

    public abstract void close() throws IOException;

    public final JsonGenerator configure(Feature feature, boolean z2) {
        if (z2) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public void copyCurrentEvent(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.currentToken();
        switch (currentToken == null ? -1 : currentToken.id()) {
            case -1:
                _reportError("No current event to copy");
                return;
            case 1:
                writeStartObject();
                return;
            case 2:
                writeEndObject();
                return;
            case 3:
                writeStartArray();
                return;
            case 4:
                writeEndArray();
                return;
            case 5:
                writeFieldName(jsonParser.getCurrentName());
                return;
            case 6:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    writeString(jsonParser.getText());
                    return;
                }
            case 7:
                JsonParser.NumberType numberType = jsonParser.getNumberType();
                if (numberType == JsonParser.NumberType.INT) {
                    writeNumber(jsonParser.getIntValue());
                    return;
                } else if (numberType == JsonParser.NumberType.BIG_INTEGER) {
                    writeNumber(jsonParser.getBigIntegerValue());
                    return;
                } else {
                    writeNumber(jsonParser.getLongValue());
                    return;
                }
            case 8:
                JsonParser.NumberType numberType2 = jsonParser.getNumberType();
                if (numberType2 == JsonParser.NumberType.BIG_DECIMAL) {
                    writeNumber(jsonParser.getDecimalValue());
                    return;
                } else if (numberType2 == JsonParser.NumberType.FLOAT) {
                    writeNumber(jsonParser.getFloatValue());
                    return;
                } else {
                    writeNumber(jsonParser.getDoubleValue());
                    return;
                }
            case 9:
                writeBoolean(true);
                return;
            case 10:
                writeBoolean(false);
                return;
            case 11:
                writeNull();
                return;
            case 12:
                writeObject(jsonParser.getEmbeddedObject());
                return;
            default:
                throw new IllegalStateException("Internal error: unknown current token, " + currentToken);
        }
    }

    public void copyCurrentStructure(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.currentToken();
        int i3 = -1;
        int id = currentToken == null ? -1 : currentToken.id();
        if (id == 5) {
            writeFieldName(jsonParser.getCurrentName());
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != null) {
                i3 = nextToken.id();
            }
            id = i3;
        }
        if (id == 1) {
            writeStartObject();
            _copyCurrentContents(jsonParser);
        } else if (id != 3) {
            copyCurrentEvent(jsonParser);
        } else {
            writeStartArray();
            _copyCurrentContents(jsonParser);
        }
    }

    public Object currentValue() {
        return getCurrentValue();
    }

    public abstract JsonGenerator disable(Feature feature);

    public abstract JsonGenerator enable(Feature feature);

    public abstract void flush() throws IOException;

    public CharacterEscapes getCharacterEscapes() {
        return null;
    }

    public abstract ObjectCodec getCodec();

    public Object getCurrentValue() {
        JsonStreamContext outputContext = getOutputContext();
        if (outputContext == null) {
            return null;
        }
        return outputContext.getCurrentValue();
    }

    public abstract int getFeatureMask();

    public int getFormatFeatures() {
        return 0;
    }

    public int getHighestEscapedChar() {
        return 0;
    }

    public int getOutputBuffered() {
        return -1;
    }

    public abstract JsonStreamContext getOutputContext();

    public Object getOutputTarget() {
        return null;
    }

    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }

    public FormatSchema getSchema() {
        return null;
    }

    public JacksonFeatureSet<StreamWriteCapability> getWriteCapabilities() {
        return DEFAULT_WRITE_CAPABILITIES;
    }

    public abstract boolean isClosed();

    public abstract boolean isEnabled(Feature feature);

    public boolean isEnabled(StreamWriteFeature streamWriteFeature) {
        return isEnabled(streamWriteFeature.mappedFeature());
    }

    public JsonGenerator overrideFormatFeatures(int i3, int i4) {
        return this;
    }

    public JsonGenerator overrideStdFeatures(int i3, int i4) {
        return setFeatureMask((i3 & i4) | (getFeatureMask() & (~i4)));
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        return this;
    }

    public abstract JsonGenerator setCodec(ObjectCodec objectCodec);

    public void setCurrentValue(Object obj) {
        JsonStreamContext outputContext = getOutputContext();
        if (outputContext != null) {
            outputContext.setCurrentValue(obj);
        }
    }

    @Deprecated
    public abstract JsonGenerator setFeatureMask(int i3);

    public JsonGenerator setHighestNonEscapedChar(int i3) {
        return this;
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._cfgPrettyPrinter = prettyPrinter;
        return this;
    }

    public JsonGenerator setRootValueSeparator(SerializableString serializableString) {
        throw new UnsupportedOperationException();
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException(C0118y.g("Generator of type ", getClass().getName(), " does not support schema of type '", formatSchema.getSchemaType(), "'"));
    }

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public abstract Version version();

    public void writeArray(int[] iArr, int i3, int i4) throws IOException {
        if (iArr != null) {
            _verifyOffsets(iArr.length, i3, i4);
            writeStartArray(iArr, i4);
            int i5 = i4 + i3;
            while (i3 < i5) {
                writeNumber(iArr[i3]);
                i3++;
            }
            writeEndArray();
            return;
        }
        throw new IllegalArgumentException("null array");
    }

    public void writeArrayFieldStart(String str) throws IOException {
        writeFieldName(str);
        writeStartArray();
    }

    public abstract int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i3) throws IOException;

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i3, int i4) throws IOException;

    public void writeBinary(byte[] bArr, int i3, int i4) throws IOException {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, i3, i4);
    }

    public void writeBinaryField(String str, byte[] bArr) throws IOException {
        writeFieldName(str);
        writeBinary(bArr);
    }

    public abstract void writeBoolean(boolean z2) throws IOException;

    public void writeBooleanField(String str, boolean z2) throws IOException {
        writeFieldName(str);
        writeBoolean(z2);
    }

    public void writeEmbeddedObject(Object obj) throws IOException {
        if (obj == null) {
            writeNull();
        } else if (obj instanceof byte[]) {
            writeBinary((byte[]) obj);
        } else {
            throw new JsonGenerationException(b.h(obj, "No native support for writing embedded objects of type "), this);
        }
    }

    public abstract void writeEndArray() throws IOException;

    public abstract void writeEndObject() throws IOException;

    public void writeFieldId(long j2) throws IOException {
        writeFieldName(Long.toString(j2));
    }

    public abstract void writeFieldName(SerializableString serializableString) throws IOException;

    public abstract void writeFieldName(String str) throws IOException;

    public abstract void writeNull() throws IOException;

    public void writeNullField(String str) throws IOException {
        writeFieldName(str);
        writeNull();
    }

    public abstract void writeNumber(double d2) throws IOException;

    public abstract void writeNumber(float f2) throws IOException;

    public abstract void writeNumber(int i3) throws IOException;

    public abstract void writeNumber(long j2) throws IOException;

    public abstract void writeNumber(String str) throws IOException;

    public abstract void writeNumber(BigDecimal bigDecimal) throws IOException;

    public abstract void writeNumber(BigInteger bigInteger) throws IOException;

    public void writeNumber(short s3) throws IOException {
        writeNumber((int) s3);
    }

    public void writeNumberField(String str, short s3) throws IOException {
        writeFieldName(str);
        writeNumber(s3);
    }

    public abstract void writeObject(Object obj) throws IOException;

    public void writeObjectField(String str, Object obj) throws IOException {
        writeFieldName(str);
        writeObject(obj);
    }

    public void writeObjectFieldStart(String str) throws IOException {
        writeFieldName(str);
        writeStartObject();
    }

    public void writeObjectId(Object obj) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeObjectRef(Object obj) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeOmittedField(String str) throws IOException {
    }

    public void writePOJO(Object obj) throws IOException {
        writeObject(obj);
    }

    public void writePOJOField(String str, Object obj) throws IOException {
        writeObjectField(str, obj);
    }

    public abstract void writeRaw(char c3) throws IOException;

    public void writeRaw(SerializableString serializableString) throws IOException {
        writeRaw(serializableString.getValue());
    }

    public abstract void writeRaw(String str) throws IOException;

    public abstract void writeRaw(String str, int i3, int i4) throws IOException;

    public abstract void writeRaw(char[] cArr, int i3, int i4) throws IOException;

    public abstract void writeRawUTF8String(byte[] bArr, int i3, int i4) throws IOException;

    public void writeRawValue(SerializableString serializableString) throws IOException {
        writeRawValue(serializableString.getValue());
    }

    public abstract void writeRawValue(String str) throws IOException;

    public abstract void writeRawValue(String str, int i3, int i4) throws IOException;

    public abstract void writeRawValue(char[] cArr, int i3, int i4) throws IOException;

    public abstract void writeStartArray() throws IOException;

    @Deprecated
    public void writeStartArray(int i3) throws IOException {
        writeStartArray();
    }

    public abstract void writeStartObject() throws IOException;

    public void writeStartObject(Object obj) throws IOException {
        writeStartObject();
        setCurrentValue(obj);
    }

    public abstract void writeString(SerializableString serializableString) throws IOException;

    public void writeString(Reader reader, int i3) throws IOException {
        _reportUnsupportedOperation();
    }

    public abstract void writeString(String str) throws IOException;

    public abstract void writeString(char[] cArr, int i3, int i4) throws IOException;

    public void writeStringField(String str, String str2) throws IOException {
        writeFieldName(str);
        writeString(str2);
    }

    public abstract void writeTree(TreeNode treeNode) throws IOException;

    public void writeTypeId(Object obj) throws IOException {
        throw new JsonGenerationException("No native support for writing Type Ids", this);
    }

    public WritableTypeId writeTypePrefix(WritableTypeId writableTypeId) throws IOException {
        Object obj = writableTypeId.id;
        JsonToken jsonToken = writableTypeId.valueShape;
        if (canWriteTypeId()) {
            writableTypeId.wrapperWritten = false;
            writeTypeId(obj);
        } else {
            String valueOf = obj instanceof String ? (String) obj : String.valueOf(obj);
            writableTypeId.wrapperWritten = true;
            WritableTypeId.Inclusion inclusion = writableTypeId.include;
            if (jsonToken != JsonToken.START_OBJECT && inclusion.requiresObjectContext()) {
                inclusion = WritableTypeId.Inclusion.WRAPPER_ARRAY;
                writableTypeId.include = inclusion;
            }
            int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion[inclusion.ordinal()];
            if (!(i3 == 1 || i3 == 2)) {
                if (i3 == 3) {
                    writeStartObject(writableTypeId.forValue);
                    writeStringField(writableTypeId.asProperty, valueOf);
                    return writableTypeId;
                } else if (i3 != 4) {
                    writeStartArray();
                    writeString(valueOf);
                } else {
                    writeStartObject();
                    writeFieldName(valueOf);
                }
            }
        }
        if (jsonToken == JsonToken.START_OBJECT) {
            writeStartObject(writableTypeId.forValue);
        } else if (jsonToken == JsonToken.START_ARRAY) {
            writeStartArray();
        }
        return writableTypeId;
    }

    public WritableTypeId writeTypeSuffix(WritableTypeId writableTypeId) throws IOException {
        JsonToken jsonToken = writableTypeId.valueShape;
        if (jsonToken == JsonToken.START_OBJECT) {
            writeEndObject();
        } else if (jsonToken == JsonToken.START_ARRAY) {
            writeEndArray();
        }
        if (writableTypeId.wrapperWritten) {
            int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$type$WritableTypeId$Inclusion[writableTypeId.include.ordinal()];
            if (i3 == 1) {
                Object obj = writableTypeId.id;
                writeStringField(writableTypeId.asProperty, obj instanceof String ? (String) obj : String.valueOf(obj));
            } else if (!(i3 == 2 || i3 == 3)) {
                if (i3 != 5) {
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        return writableTypeId;
    }

    public abstract void writeUTF8String(byte[] bArr, int i3, int i4) throws IOException;

    public void writeBinary(byte[] bArr) throws IOException {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, 0, bArr.length);
    }

    public void writeNumber(char[] cArr, int i3, int i4) throws IOException {
        writeNumber(new String(cArr, i3, i4));
    }

    public void writeStartArray(Object obj) throws IOException {
        writeStartArray();
        setCurrentValue(obj);
    }

    public int writeBinary(InputStream inputStream, int i3) throws IOException {
        return writeBinary(Base64Variants.getDefaultVariant(), inputStream, i3);
    }

    public void writeNumberField(String str, int i3) throws IOException {
        writeFieldName(str);
        writeNumber(i3);
    }

    public void writeStartObject(Object obj, int i3) throws IOException {
        writeStartObject(obj);
    }

    public void writeStartArray(Object obj, int i3) throws IOException {
        writeStartArray(i3);
        setCurrentValue(obj);
    }

    public void writeNumberField(String str, long j2) throws IOException {
        writeFieldName(str);
        writeNumber(j2);
    }

    public void writeArray(long[] jArr, int i3, int i4) throws IOException {
        if (jArr != null) {
            _verifyOffsets(jArr.length, i3, i4);
            writeStartArray(jArr, i4);
            int i5 = i4 + i3;
            while (i3 < i5) {
                writeNumber(jArr[i3]);
                i3++;
            }
            writeEndArray();
            return;
        }
        throw new IllegalArgumentException("null array");
    }

    public void writeNumberField(String str, BigInteger bigInteger) throws IOException {
        writeFieldName(str);
        writeNumber(bigInteger);
    }

    public void writeNumberField(String str, float f2) throws IOException {
        writeFieldName(str);
        writeNumber(f2);
    }

    public void writeArray(double[] dArr, int i3, int i4) throws IOException {
        if (dArr != null) {
            _verifyOffsets(dArr.length, i3, i4);
            writeStartArray(dArr, i4);
            int i5 = i4 + i3;
            while (i3 < i5) {
                writeNumber(dArr[i3]);
                i3++;
            }
            writeEndArray();
            return;
        }
        throw new IllegalArgumentException("null array");
    }

    public void writeNumberField(String str, double d2) throws IOException {
        writeFieldName(str);
        writeNumber(d2);
    }

    public void writeNumberField(String str, BigDecimal bigDecimal) throws IOException {
        writeFieldName(str);
        writeNumber(bigDecimal);
    }

    public void writeArray(String[] strArr, int i3, int i4) throws IOException {
        if (strArr != null) {
            _verifyOffsets(strArr.length, i3, i4);
            writeStartArray(strArr, i4);
            int i5 = i4 + i3;
            while (i3 < i5) {
                writeString(strArr[i3]);
                i3++;
            }
            writeEndArray();
            return;
        }
        throw new IllegalArgumentException("null array");
    }
}
