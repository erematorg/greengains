package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public abstract class GeneratorBase extends JsonGenerator {
    protected static final int DERIVED_FEATURES_MASK = ((JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.getMask() | JsonGenerator.Feature.ESCAPE_NON_ASCII.getMask()) | JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.getMask());
    protected static final int MAX_BIG_DECIMAL_SCALE = 9999;
    public static final int SURR1_FIRST = 55296;
    public static final int SURR1_LAST = 56319;
    public static final int SURR2_FIRST = 56320;
    public static final int SURR2_LAST = 57343;
    protected static final String WRITE_BINARY = "write a binary value";
    protected static final String WRITE_BOOLEAN = "write a boolean value";
    protected static final String WRITE_NULL = "write a null";
    protected static final String WRITE_NUMBER = "write a number";
    protected static final String WRITE_RAW = "write a raw (unencoded) value";
    protected static final String WRITE_STRING = "write a string";
    protected boolean _cfgNumbersAsStrings;
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;

    public GeneratorBase(int i3, ObjectCodec objectCodec) {
        this._features = i3;
        this._objectCodec = objectCodec;
        this._writeContext = JsonWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i3) ? DupDetector.rootDetector((JsonGenerator) this) : null);
        this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i3);
    }

    public String _asString(BigDecimal bigDecimal) throws IOException {
        if (!JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this._features)) {
            return bigDecimal.toString();
        }
        int scale = bigDecimal.scale();
        if (scale < -9999 || scale > 9999) {
            _reportError(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", new Object[]{Integer.valueOf(scale), 9999, 9999}));
        }
        return bigDecimal.toPlainString();
    }

    public void _checkRangeBoundsForByteArray(byte[] bArr, int i3, int i4) throws IOException {
        if (bArr == null) {
            _reportError("Invalid `byte[]` argument: `null`");
        }
        int length = bArr.length;
        int i5 = i3 + i4;
        if (((length - i5) | i3 | i4 | i5) < 0) {
            _reportError(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `byte[]` of length %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(length)}));
        }
    }

    public void _checkRangeBoundsForCharArray(char[] cArr, int i3, int i4) throws IOException {
        if (cArr == null) {
            _reportError("Invalid `char[]` argument: `null`");
        }
        int length = cArr.length;
        int i5 = i3 + i4;
        if (((length - i5) | i3 | i4 | i5) < 0) {
            _reportError(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `char[]` of length %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(length)}));
        }
    }

    public void _checkRangeBoundsForString(String str, int i3, int i4) throws IOException {
        if (str == null) {
            _reportError("Invalid `String` argument: `null`");
        }
        int length = str.length();
        int i5 = i3 + i4;
        if (((length - i5) | i3 | i4 | i5) < 0) {
            _reportError(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `String` of length %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(length)}));
        }
    }

    public void _checkStdFeatureChanges(int i3, int i4) {
        if ((DERIVED_FEATURES_MASK & i4) != 0) {
            this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i3);
            JsonGenerator.Feature feature = JsonGenerator.Feature.ESCAPE_NON_ASCII;
            if (feature.enabledIn(i4)) {
                if (feature.enabledIn(i3)) {
                    setHighestNonEscapedChar(127);
                } else {
                    setHighestNonEscapedChar(0);
                }
            }
            JsonGenerator.Feature feature2 = JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION;
            if (!feature2.enabledIn(i4)) {
                return;
            }
            if (!feature2.enabledIn(i3)) {
                this._writeContext = this._writeContext.withDupDetector((DupDetector) null);
            } else if (this._writeContext.getDupDetector() == null) {
                this._writeContext = this._writeContext.withDupDetector(DupDetector.rootDetector((JsonGenerator) this));
            }
        }
    }

    public PrettyPrinter _constructDefaultPrettyPrinter() {
        return new DefaultPrettyPrinter();
    }

    public final int _decodeSurrogate(int i3, int i4) throws IOException {
        if (i4 < 56320 || i4 > 57343) {
            _reportError(String.format("Incomplete surrogate pair: first char 0x%04X, second 0x%04X", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        }
        return (i4 - 56320) + ((i3 - SURR1_FIRST) << 10) + 65536;
    }

    public abstract void _releaseBuffers();

    public abstract void _verifyValueWrite(String str) throws IOException;

    public void close() throws IOException {
        this._closed = true;
    }

    public JsonGenerator disable(JsonGenerator.Feature feature) {
        int mask = feature.getMask();
        this._features &= ~mask;
        if ((mask & DERIVED_FEATURES_MASK) != 0) {
            if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
                this._cfgNumbersAsStrings = false;
            } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
                setHighestNonEscapedChar(0);
            } else if (feature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION) {
                this._writeContext = this._writeContext.withDupDetector((DupDetector) null);
            }
        }
        return this;
    }

    public JsonGenerator enable(JsonGenerator.Feature feature) {
        int mask = feature.getMask();
        this._features |= mask;
        if ((mask & DERIVED_FEATURES_MASK) != 0) {
            if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
                this._cfgNumbersAsStrings = true;
            } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
                setHighestNonEscapedChar(127);
            } else if (feature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION && this._writeContext.getDupDetector() == null) {
                this._writeContext = this._writeContext.withDupDetector(DupDetector.rootDetector((JsonGenerator) this));
            }
        }
        return this;
    }

    public abstract void flush() throws IOException;

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public Object getCurrentValue() {
        return this._writeContext.getCurrentValue();
    }

    public int getFeatureMask() {
        return this._features;
    }

    public JsonStreamContext getOutputContext() {
        return this._writeContext;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._features & feature.getMask()) != 0;
    }

    public JsonGenerator overrideStdFeatures(int i3, int i4) {
        int i5 = this._features;
        int i6 = (i3 & i4) | ((~i4) & i5);
        int i7 = i5 ^ i6;
        if (i7 != 0) {
            this._features = i6;
            _checkStdFeatureChanges(i6, i7);
        }
        return this;
    }

    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public void setCurrentValue(Object obj) {
        JsonWriteContext jsonWriteContext = this._writeContext;
        if (jsonWriteContext != null) {
            jsonWriteContext.setCurrentValue(obj);
        }
    }

    @Deprecated
    public JsonGenerator setFeatureMask(int i3) {
        int i4 = this._features ^ i3;
        this._features = i3;
        if (i4 != 0) {
            _checkStdFeatureChanges(i3, i4);
        }
        return this;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        return getPrettyPrinter() != null ? this : setPrettyPrinter(_constructDefaultPrettyPrinter());
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i3) throws IOException {
        _reportUnsupportedOperation();
        return 0;
    }

    public void writeFieldName(SerializableString serializableString) throws IOException {
        writeFieldName(serializableString.getValue());
    }

    public void writeObject(Object obj) throws IOException {
        if (obj == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this._objectCodec;
        if (objectCodec != null) {
            objectCodec.writeValue(this, obj);
        } else {
            _writeSimpleObject(obj);
        }
    }

    public void writeRawValue(String str) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(str);
    }

    public void writeStartObject(Object obj) throws IOException {
        writeStartObject();
        if (obj != null) {
            setCurrentValue(obj);
        }
    }

    public void writeString(SerializableString serializableString) throws IOException {
        writeString(serializableString.getValue());
    }

    public void writeTree(TreeNode treeNode) throws IOException {
        if (treeNode == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this._objectCodec;
        if (objectCodec != null) {
            objectCodec.writeValue(this, treeNode);
            return;
        }
        throw new IllegalStateException("No ObjectCodec defined");
    }

    public void writeRawValue(String str, int i3, int i4) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(str, i3, i4);
    }

    public void writeRawValue(char[] cArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(cArr, i3, i4);
    }

    public void writeRawValue(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(serializableString);
    }

    public GeneratorBase(int i3, ObjectCodec objectCodec, JsonWriteContext jsonWriteContext) {
        this._features = i3;
        this._objectCodec = objectCodec;
        this._writeContext = jsonWriteContext;
        this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i3);
    }
}
