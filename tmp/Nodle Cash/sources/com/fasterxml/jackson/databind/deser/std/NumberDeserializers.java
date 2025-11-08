package com.fasterxml.jackson.databind.deser.std;

import androidx.collection.SieveCacheKt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public class NumberDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    /* renamed from: com.fasterxml.jackson.databind.deser.std.NumberDeserializers$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.fasterxml.jackson.databind.cfg.CoercionAction[] r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction = r0
                com.fasterxml.jackson.databind.cfg.CoercionAction r1 = com.fasterxml.jackson.databind.cfg.CoercionAction.Fail     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.databind.cfg.CoercionAction r1 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.databind.cfg.CoercionAction r1 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.NumberDeserializers.AnonymousClass1.<clinit>():void");
        }
    }

    @JacksonStdImpl
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();

        public BigDecimalDeserializer() {
            super((Class<?>) BigDecimal.class);
        }

        public Object getEmptyValue(DeserializationContext deserializationContext) {
            return BigDecimal.ZERO;
        }

        public final LogicalType logicalType() {
            return LogicalType.Float;
        }

        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (BigDecimal) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId != 6) {
                    if (currentTokenId == 7) {
                        CoercionAction _checkIntToFloatCoercion = _checkIntToFloatCoercion(jsonParser, deserializationContext, this._valueClass);
                        if (_checkIntToFloatCoercion == CoercionAction.AsNull) {
                            return (BigDecimal) getNullValue(deserializationContext);
                        }
                        if (_checkIntToFloatCoercion == CoercionAction.AsEmpty) {
                            return (BigDecimal) getEmptyValue(deserializationContext);
                        }
                    } else if (currentTokenId != 8) {
                        return (BigDecimal) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                    }
                    return jsonParser.getDecimalValue();
                }
                str = jsonParser.getText();
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (BigDecimal) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (BigDecimal) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_hasTextualNull(trim)) {
                return (BigDecimal) getNullValue(deserializationContext);
            }
            try {
                return NumberInput.parseBigDecimal(trim);
            } catch (IllegalArgumentException unused) {
                return (BigDecimal) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid representation", new Object[0]);
            }
        }
    }

    @JacksonStdImpl
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();

        public BigIntegerDeserializer() {
            super((Class<?>) BigInteger.class);
        }

        public Object getEmptyValue(DeserializationContext deserializationContext) {
            return BigInteger.ZERO;
        }

        public final LogicalType logicalType() {
            return LogicalType.Integer;
        }

        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            if (jsonParser.isExpectedNumberIntToken()) {
                return jsonParser.getBigIntegerValue();
            }
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (BigInteger) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId != 8) {
                    return (BigInteger) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                } else {
                    CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, this._valueClass);
                    if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                        return (BigInteger) getNullValue(deserializationContext);
                    }
                    if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                        return (BigInteger) getEmptyValue(deserializationContext);
                    }
                    return jsonParser.getDecimalValue().toBigInteger();
                }
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (BigInteger) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (BigInteger) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_hasTextualNull(trim)) {
                return (BigInteger) getNullValue(deserializationContext);
            }
            try {
                return NumberInput.parseBigInteger(trim);
            } catch (IllegalArgumentException unused) {
                return (BigInteger) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid representation", new Object[0]);
            }
        }
    }

    @JacksonStdImpl
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        static final BooleanDeserializer primitiveInstance = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
        private static final long serialVersionUID = 1;
        static final BooleanDeserializer wrapperInstance = new BooleanDeserializer(Boolean.class, (Boolean) null);

        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, LogicalType.Boolean, bool, Boolean.FALSE);
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (currentToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (this._primitive) {
                return Boolean.valueOf(_parseBooleanPrimitive(jsonParser, deserializationContext));
            }
            return _parseBoolean(jsonParser, deserializationContext, this._valueClass);
        }

        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (currentToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (this._primitive) {
                return Boolean.valueOf(_parseBooleanPrimitive(jsonParser, deserializationContext));
            }
            return _parseBoolean(jsonParser, deserializationContext, this._valueClass);
        }
    }

    @JacksonStdImpl
    public static class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        static final ByteDeserializer primitiveInstance = new ByteDeserializer(Byte.TYPE, (byte) 0);
        private static final long serialVersionUID = 1;
        static final ByteDeserializer wrapperInstance = new ByteDeserializer(Byte.class, (Byte) null);

        public ByteDeserializer(Class<Byte> cls, Byte b3) {
            super(cls, LogicalType.Integer, b3, (byte) 0);
        }

        public Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (Byte) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId == 11) {
                    return (Byte) getNullValue(deserializationContext);
                }
                if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return Byte.valueOf(jsonParser.getByteValue());
                } else {
                    if (currentTokenId != 8) {
                        return (Byte) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                    }
                    CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, this._valueClass);
                    return _checkFloatToIntCoercion == CoercionAction.AsNull ? (Byte) getNullValue(deserializationContext) : _checkFloatToIntCoercion == CoercionAction.AsEmpty ? (Byte) getEmptyValue(deserializationContext) : Byte.valueOf(jsonParser.getByteValue());
                }
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (Byte) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (Byte) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_checkTextualNull(deserializationContext, trim)) {
                return (Byte) getNullValue(deserializationContext);
            }
            try {
                int parseInt = NumberInput.parseInt(trim);
                return _byteOverflow(parseInt) ? (Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 8-bit value", new Object[0]) : Byte.valueOf((byte) parseInt);
            } catch (IllegalArgumentException unused) {
                return (Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Byte value", new Object[0]);
            }
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Byte.valueOf(jsonParser.getByteValue());
            }
            if (this._primitive) {
                return Byte.valueOf(_parseBytePrimitive(jsonParser, deserializationContext));
            }
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(Character.TYPE, 0);
        private static final long serialVersionUID = 1;
        static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.class, (Character) null);

        public CharacterDeserializer(Class<Character> cls, Character ch2) {
            super(cls, LogicalType.Integer, ch2, 0);
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (Character) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId == 11) {
                    if (this._primitive) {
                        _verifyNullForPrimitive(deserializationContext);
                    }
                    return (Character) getNullValue(deserializationContext);
                } else if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId != 7) {
                    return (Character) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                } else {
                    CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType(), this._valueClass, CoercionInputShape.Integer);
                    int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[findCoercionAction.ordinal()];
                    if (i3 == 1) {
                        Class<?> cls = this._valueClass;
                        Number numberValue = jsonParser.getNumberValue();
                        _checkCoercionFail(deserializationContext, findCoercionAction, cls, numberValue, "Integer value (" + jsonParser.getText() + ")");
                    } else if (i3 != 2) {
                        if (i3 == 3) {
                            return (Character) getEmptyValue(deserializationContext);
                        }
                        int intValue = jsonParser.getIntValue();
                        if (intValue < 0 || intValue > 65535) {
                            return (Character) deserializationContext.handleWeirdNumberValue(handledType(), Integer.valueOf(intValue), "value outside valid Character range (0x0000 - 0xFFFF)", new Object[0]);
                        }
                        return Character.valueOf((char) intValue);
                    }
                    return (Character) getNullValue(deserializationContext);
                }
            }
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (Character) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (Character) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_checkTextualNull(deserializationContext, trim)) {
                return (Character) getNullValue(deserializationContext);
            }
            return (Character) deserializationContext.handleWeirdStringValue(handledType(), trim, "Expected either Integer value code or 1-character String", new Object[0]);
        }
    }

    @JacksonStdImpl
    public static class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d));
        private static final long serialVersionUID = 1;
        static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.class, (Double) null);

        public DoubleDeserializer(Class<Double> cls, Double d2) {
            super(cls, LogicalType.Float, d2, Double.valueOf(0.0d));
        }

        public final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (Double) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId == 11) {
                    return (Double) getNullValue(deserializationContext);
                }
                if (currentTokenId != 6) {
                    if (currentTokenId == 7) {
                        CoercionAction _checkIntToFloatCoercion = _checkIntToFloatCoercion(jsonParser, deserializationContext, this._valueClass);
                        if (_checkIntToFloatCoercion == CoercionAction.AsNull) {
                            return (Double) getNullValue(deserializationContext);
                        }
                        if (_checkIntToFloatCoercion == CoercionAction.AsEmpty) {
                            return (Double) getEmptyValue(deserializationContext);
                        }
                    } else if (currentTokenId != 8) {
                        return (Double) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                    }
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                str = jsonParser.getText();
            }
            Double _checkDoubleSpecialValue = _checkDoubleSpecialValue(str);
            if (_checkDoubleSpecialValue != null) {
                return _checkDoubleSpecialValue;
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (Double) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (Double) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_checkTextualNull(deserializationContext, trim)) {
                return (Double) getNullValue(deserializationContext);
            }
            try {
                return Double.valueOf(StdDeserializer._parseDouble(trim, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)));
            } catch (IllegalArgumentException unused) {
                return (Double) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid `Double` value", new Object[0]);
            }
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return Double.valueOf(jsonParser.getDoubleValue());
            }
            if (this._primitive) {
                return Double.valueOf(_parseDoublePrimitive(jsonParser, deserializationContext));
            }
            return _parseDouble(jsonParser, deserializationContext);
        }

        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return Double.valueOf(jsonParser.getDoubleValue());
            }
            if (this._primitive) {
                return Double.valueOf(_parseDoublePrimitive(jsonParser, deserializationContext));
            }
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        static final FloatDeserializer primitiveInstance = new FloatDeserializer(Float.TYPE, Float.valueOf(0.0f));
        private static final long serialVersionUID = 1;
        static final FloatDeserializer wrapperInstance = new FloatDeserializer(Float.class, (Float) null);

        public FloatDeserializer(Class<Float> cls, Float f2) {
            super(cls, LogicalType.Float, f2, Float.valueOf(0.0f));
        }

        public final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (Float) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId == 11) {
                    return (Float) getNullValue(deserializationContext);
                }
                if (currentTokenId != 6) {
                    if (currentTokenId == 7) {
                        CoercionAction _checkIntToFloatCoercion = _checkIntToFloatCoercion(jsonParser, deserializationContext, this._valueClass);
                        if (_checkIntToFloatCoercion == CoercionAction.AsNull) {
                            return (Float) getNullValue(deserializationContext);
                        }
                        if (_checkIntToFloatCoercion == CoercionAction.AsEmpty) {
                            return (Float) getEmptyValue(deserializationContext);
                        }
                    } else if (currentTokenId != 8) {
                        return (Float) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                    }
                    return Float.valueOf(jsonParser.getFloatValue());
                }
                str = jsonParser.getText();
            }
            Float _checkFloatSpecialValue = _checkFloatSpecialValue(str);
            if (_checkFloatSpecialValue != null) {
                return _checkFloatSpecialValue;
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (Float) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (Float) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_checkTextualNull(deserializationContext, trim)) {
                return (Float) getNullValue(deserializationContext);
            }
            try {
                return Float.valueOf(NumberInput.parseFloat(trim, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)));
            } catch (IllegalArgumentException unused) {
                return (Float) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid `Float` value", new Object[0]);
            }
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return Float.valueOf(jsonParser.getFloatValue());
            }
            if (this._primitive) {
                return Float.valueOf(_parseFloatPrimitive(jsonParser, deserializationContext));
            }
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(Integer.TYPE, 0);
        private static final long serialVersionUID = 1;
        static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.class, (Integer) null);

        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, LogicalType.Integer, num, 0);
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public boolean isCachable() {
            return true;
        }

        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            if (this._primitive) {
                return Integer.valueOf(_parseIntPrimitive(jsonParser, deserializationContext));
            }
            return _parseInteger(jsonParser, deserializationContext, Integer.class);
        }

        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            if (this._primitive) {
                return Integer.valueOf(_parseIntPrimitive(jsonParser, deserializationContext));
            }
            return _parseInteger(jsonParser, deserializationContext, Integer.class);
        }
    }

    @JacksonStdImpl
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        static final LongDeserializer primitiveInstance = new LongDeserializer(Long.TYPE, 0L);
        private static final long serialVersionUID = 1;
        static final LongDeserializer wrapperInstance = new LongDeserializer(Long.class, (Long) null);

        public LongDeserializer(Class<Long> cls, Long l2) {
            super(cls, LogicalType.Integer, l2, 0L);
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public boolean isCachable() {
            return true;
        }

        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Long.valueOf(jsonParser.getLongValue());
            }
            if (this._primitive) {
                return Long.valueOf(_parseLongPrimitive(jsonParser, deserializationContext));
            }
            return _parseLong(jsonParser, deserializationContext, Long.class);
        }
    }

    @JacksonStdImpl
    public static class NumberDeserializer extends StdScalarDeserializer<Object> {
        public static final NumberDeserializer instance = new NumberDeserializer();

        public NumberDeserializer() {
            super((Class<?>) Number.class);
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId != 6) {
                    return currentTokenId != 7 ? currentTokenId != 8 ? deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser) : (!deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) || jsonParser.isNaN()) ? jsonParser.getNumberValue() : jsonParser.getDecimalValue() : deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS) ? _coerceIntegral(jsonParser, deserializationContext) : jsonParser.getNumberValue();
                }
                str = jsonParser.getText();
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_hasTextualNull(trim)) {
                return getNullValue(deserializationContext);
            }
            if (_isPosInf(trim)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            if (_isNegInf(trim)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            if (_isNaN(trim)) {
                return Double.valueOf(Double.NaN);
            }
            try {
                if (!_isIntNumber(trim)) {
                    return deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ? NumberInput.parseBigDecimal(trim) : Double.valueOf(NumberInput.parseDouble(trim, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)));
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return NumberInput.parseBigInteger(trim);
                }
                long parseLong = NumberInput.parseLong(trim);
                return (deserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) || parseLong > SieveCacheKt.NodeLinkMask || parseLong < SieveCacheKt.NodeMetaAndPreviousMask) ? Long.valueOf(parseLong) : Integer.valueOf((int) parseLong);
            } catch (IllegalArgumentException unused) {
                return deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid number", new Object[0]);
            }
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            int currentTokenId = jsonParser.currentTokenId();
            return (currentTokenId == 6 || currentTokenId == 7 || currentTokenId == 8) ? deserialize(jsonParser, deserializationContext) : typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
        }

        public final LogicalType logicalType() {
            return LogicalType.Integer;
        }
    }

    @JacksonStdImpl
    public static class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        static final ShortDeserializer primitiveInstance = new ShortDeserializer(Short.TYPE, 0);
        private static final long serialVersionUID = 1;
        static final ShortDeserializer wrapperInstance = new ShortDeserializer(Short.class, (Short) null);

        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, LogicalType.Integer, sh, (short) 0);
        }

        public Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String str;
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId == 1) {
                str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
            } else if (currentTokenId == 3) {
                return (Short) _deserializeFromArray(jsonParser, deserializationContext);
            } else {
                if (currentTokenId == 11) {
                    return (Short) getNullValue(deserializationContext);
                }
                if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return Short.valueOf(jsonParser.getShortValue());
                } else {
                    if (currentTokenId != 8) {
                        return (Short) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                    }
                    CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, this._valueClass);
                    return _checkFloatToIntCoercion == CoercionAction.AsNull ? (Short) getNullValue(deserializationContext) : _checkFloatToIntCoercion == CoercionAction.AsEmpty ? (Short) getEmptyValue(deserializationContext) : Short.valueOf(jsonParser.getShortValue());
                }
            }
            CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
            if (_checkFromStringCoercion == CoercionAction.AsNull) {
                return (Short) getNullValue(deserializationContext);
            }
            if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
                return (Short) getEmptyValue(deserializationContext);
            }
            String trim = str.trim();
            if (_checkTextualNull(deserializationContext, trim)) {
                return (Short) getNullValue(deserializationContext);
            }
            try {
                int parseInt = NumberInput.parseInt(trim);
                return _shortOverflow(parseInt) ? (Short) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 16-bit value", new Object[0]) : Short.valueOf((short) parseInt);
            } catch (IllegalArgumentException unused) {
                return (Short) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Short value", new Object[0]);
            }
        }

        public /* bridge */ /* synthetic */ Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public /* bridge */ /* synthetic */ AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Short.valueOf(jsonParser.getShortValue());
            }
            if (this._primitive) {
                return Short.valueOf(_parseShortPrimitive(jsonParser, deserializationContext));
            }
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    static {
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        for (int i3 = 0; i3 < 11; i3++) {
            _classNames.add(clsArr[i3].getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return IntegerDeserializer.primitiveInstance;
            }
            if (cls == Boolean.TYPE) {
                return BooleanDeserializer.primitiveInstance;
            }
            if (cls == Long.TYPE) {
                return LongDeserializer.primitiveInstance;
            }
            if (cls == Double.TYPE) {
                return DoubleDeserializer.primitiveInstance;
            }
            if (cls == Character.TYPE) {
                return CharacterDeserializer.primitiveInstance;
            }
            if (cls == Byte.TYPE) {
                return ByteDeserializer.primitiveInstance;
            }
            if (cls == Short.TYPE) {
                return ShortDeserializer.primitiveInstance;
            }
            if (cls == Float.TYPE) {
                return FloatDeserializer.primitiveInstance;
            }
            if (cls == Void.TYPE) {
                return NullifyingDeserializer.instance;
            }
        } else if (!_classNames.contains(str)) {
            return null;
        } else {
            if (cls == Integer.class) {
                return IntegerDeserializer.wrapperInstance;
            }
            if (cls == Boolean.class) {
                return BooleanDeserializer.wrapperInstance;
            }
            if (cls == Long.class) {
                return LongDeserializer.wrapperInstance;
            }
            if (cls == Double.class) {
                return DoubleDeserializer.wrapperInstance;
            }
            if (cls == Character.class) {
                return CharacterDeserializer.wrapperInstance;
            }
            if (cls == Byte.class) {
                return ByteDeserializer.wrapperInstance;
            }
            if (cls == Short.class) {
                return ShortDeserializer.wrapperInstance;
            }
            if (cls == Float.class) {
                return FloatDeserializer.wrapperInstance;
            }
            if (cls == Number.class) {
                return NumberDeserializer.instance;
            }
            if (cls == BigDecimal.class) {
                return BigDecimalDeserializer.instance;
            }
            if (cls == BigInteger.class) {
                return BigIntegerDeserializer.instance;
            }
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for ".concat(cls.getName()));
    }

    public static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        private static final long serialVersionUID = 1;
        protected final T _emptyValue;
        protected final LogicalType _logicalType;
        protected final T _nullValue;
        protected final boolean _primitive;

        public PrimitiveOrWrapperDeserializer(Class<T> cls, LogicalType logicalType, T t2, T t3) {
            super((Class<?>) cls);
            this._logicalType = logicalType;
            this._nullValue = t2;
            this._emptyValue = t3;
            this._primitive = cls.isPrimitive();
        }

        public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return this._emptyValue;
        }

        public AccessPattern getNullAccessPattern() {
            return this._primitive ? AccessPattern.DYNAMIC : this._nullValue == null ? AccessPattern.ALWAYS_NULL : AccessPattern.CONSTANT;
        }

        public final T getNullValue(DeserializationContext deserializationContext) throws JsonMappingException {
            if (this._primitive && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", ClassUtil.classNameOf(handledType()));
            }
            return this._nullValue;
        }

        public final LogicalType logicalType() {
            return this._logicalType;
        }

        @Deprecated
        public PrimitiveOrWrapperDeserializer(Class<T> cls, T t2, T t3) {
            this(cls, LogicalType.OtherScalar, t2, t3);
        }
    }
}
