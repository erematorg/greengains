package com.fasterxml.jackson.databind.deser.std;

import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import androidx.collection.SieveCacheKt;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable, ValueInstantiator.Gettable {
    @Deprecated
    protected static final int F_MASK_ACCEPT_ARRAYS = (DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask());
    protected static final int F_MASK_INT_COERCIONS = (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask());
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;
    protected final JavaType _valueType;

    /* renamed from: com.fasterxml.jackson.databind.deser.std.StdDeserializer$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.fasterxml.jackson.databind.cfg.CoercionAction[] r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction = r0
                com.fasterxml.jackson.databind.cfg.CoercionAction r1 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty     // Catch:{ NoSuchFieldError -> 0x0012 }
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
                com.fasterxml.jackson.databind.cfg.CoercionAction r1 = com.fasterxml.jackson.databind.cfg.CoercionAction.TryConvert     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.databind.cfg.CoercionAction r1 = com.fasterxml.jackson.databind.cfg.CoercionAction.Fail     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.AnonymousClass1.<clinit>():void");
        }
    }

    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
        this._valueType = null;
    }

    public static final boolean _isBlank(String str) {
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (str.charAt(i3) > ' ') {
                return false;
            }
        }
        return true;
    }

    public static final boolean _neitherNull(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    public static final double _parseDouble(String str) throws NumberFormatException {
        return _parseDouble(str, false);
    }

    public final boolean _byteOverflow(int i3) {
        return i3 < -128 || i3 > 255;
    }

    public CoercionAction _checkBooleanToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        return _checkToStringCoercion(jsonParser, deserializationContext, cls, Boolean.valueOf(jsonParser.getBooleanValue()), CoercionInputShape.Boolean);
    }

    public CoercionAction _checkCoercionFail(DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, Object obj, String str) throws IOException {
        if (coercionAction == CoercionAction.Fail) {
            deserializationContext.reportBadCoercion(this, cls, obj, "Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", str, _coercedTypeDesc());
        }
        return coercionAction;
    }

    public Double _checkDoubleSpecialValue(String str) {
        if (str.isEmpty()) {
            return null;
        }
        char charAt = str.charAt(0);
        if (charAt != '-') {
            if (charAt != 'I') {
                if (charAt == 'N' && _isNaN(str)) {
                    return Double.valueOf(Double.NaN);
                }
                return null;
            } else if (_isPosInf(str)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            } else {
                return null;
            }
        } else if (_isNegInf(str)) {
            return Double.valueOf(Double.NEGATIVE_INFINITY);
        } else {
            return null;
        }
    }

    public Float _checkFloatSpecialValue(String str) {
        if (str.isEmpty()) {
            return null;
        }
        char charAt = str.charAt(0);
        if (charAt != '-') {
            if (charAt != 'I') {
                if (charAt == 'N' && _isNaN(str)) {
                    return Float.valueOf(Float.NaN);
                }
                return null;
            } else if (_isPosInf(str)) {
                return Float.valueOf(Float.POSITIVE_INFINITY);
            } else {
                return null;
            }
        } else if (_isNegInf(str)) {
            return Float.valueOf(Float.NEGATIVE_INFINITY);
        } else {
            return null;
        }
    }

    public CoercionAction _checkFloatToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Integer, cls, CoercionInputShape.Float);
        if (findCoercionAction != CoercionAction.Fail) {
            return findCoercionAction;
        }
        Number numberValue = jsonParser.getNumberValue();
        return _checkCoercionFail(deserializationContext, findCoercionAction, cls, numberValue, "Floating-point value (" + jsonParser.getText() + ")");
    }

    public CoercionAction _checkFloatToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        return _checkToStringCoercion(jsonParser, deserializationContext, cls, jsonParser.getNumberValue(), CoercionInputShape.Float);
    }

    public CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str) throws IOException {
        return _checkFromStringCoercion(deserializationContext, str, logicalType(), handledType());
    }

    public CoercionAction _checkIntToFloatCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Float, cls, CoercionInputShape.Integer);
        if (findCoercionAction != CoercionAction.Fail) {
            return findCoercionAction;
        }
        Number numberValue = jsonParser.getNumberValue();
        return _checkCoercionFail(deserializationContext, findCoercionAction, cls, numberValue, "Integer value (" + jsonParser.getText() + ")");
    }

    public CoercionAction _checkIntToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        return _checkToStringCoercion(jsonParser, deserializationContext, cls, jsonParser.getNumberValue(), CoercionInputShape.Integer);
    }

    public boolean _checkTextualNull(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        if (!_hasTextualNull(str)) {
            return false;
        }
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            _reportFailedNullCoerce(deserializationContext, true, mapperFeature, "String \"null\"");
        }
        return true;
    }

    public CoercionAction _checkToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls, Object obj, CoercionInputShape coercionInputShape) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Textual, cls, coercionInputShape);
        if (findCoercionAction != CoercionAction.Fail) {
            return findCoercionAction;
        }
        return _checkCoercionFail(deserializationContext, findCoercionAction, cls, obj, coercionInputShape.name() + " value (" + jsonParser.getText() + ")");
    }

    public Boolean _coerceBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Boolean, cls, CoercionInputShape.Integer);
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[findCoercionAction.ordinal()];
        boolean z2 = true;
        if (i3 == 1) {
            return Boolean.FALSE;
        }
        if (i3 == 2) {
            return null;
        }
        if (i3 == 4) {
            Number numberValue = jsonParser.getNumberValue();
            _checkCoercionFail(deserializationContext, findCoercionAction, cls, numberValue, "Integer value (" + jsonParser.getText() + ")");
            return Boolean.FALSE;
        } else if (jsonParser.getNumberType() != JsonParser.NumberType.INT) {
            return Boolean.valueOf(!SchemaSymbols.ATTVAL_FALSE_0.equals(jsonParser.getText()));
        } else {
            if (jsonParser.getIntValue() == 0) {
                z2 = false;
            }
            return Boolean.valueOf(z2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.fasterxml.jackson.databind.MapperFeature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.fasterxml.jackson.databind.DeserializationFeature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.fasterxml.jackson.databind.MapperFeature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.fasterxml.jackson.databind.MapperFeature} */
    /* JADX WARNING: Multi-variable type inference failed */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object _coerceEmptyString(com.fasterxml.jackson.databind.DeserializationContext r3, boolean r4) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.MapperFeature r0 = com.fasterxml.jackson.databind.MapperFeature.ALLOW_COERCION_OF_SCALARS
            boolean r1 = r3.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r0)
            if (r1 != 0) goto L_0x000a
            r4 = 1
            goto L_0x0015
        L_0x000a:
            if (r4 == 0) goto L_0x001c
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES
            boolean r4 = r3.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r0)
            if (r4 == 0) goto L_0x001c
            r4 = 0
        L_0x0015:
            java.lang.String r1 = "empty String (\"\")"
            r2._reportFailedNullCoerce(r3, r4, r0, r1)
            r2 = 0
            return r2
        L_0x001c:
            java.lang.Object r2 = r2.getNullValue(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._coerceEmptyString(com.fasterxml.jackson.databind.DeserializationContext, boolean):java.lang.Object");
    }

    public Object _coerceIntegral(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : deserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) ? Long.valueOf(jsonParser.getLongValue()) : jsonParser.getNumberValue();
    }

    @Deprecated
    public Object _coerceNullToken(DeserializationContext deserializationContext, boolean z2) throws JsonMappingException {
        if (z2) {
            _verifyNullForPrimitive(deserializationContext);
        }
        return getNullValue(deserializationContext);
    }

    @Deprecated
    public Object _coerceTextualNull(DeserializationContext deserializationContext, boolean z2) throws JsonMappingException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            _reportFailedNullCoerce(deserializationContext, true, mapperFeature, "String \"null\"");
        }
        return getNullValue(deserializationContext);
    }

    public String _coercedTypeDesc() {
        String str;
        JavaType valueType = getValueType();
        boolean z2 = true;
        if (valueType == null || valueType.isPrimitive()) {
            Class<?> handledType = handledType();
            if (!handledType.isArray() && !Collection.class.isAssignableFrom(handledType) && !Map.class.isAssignableFrom(handledType)) {
                z2 = false;
            }
            str = ClassUtil.getClassDescription(handledType);
        } else {
            if (!valueType.isContainerType() && !valueType.isReferenceType()) {
                z2 = false;
            }
            str = ClassUtil.getTypeDescription(valueType);
        }
        return z2 ? c.a("element of ", str) : a.m(str, " value");
    }

    public T _deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CoercionAction _findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (isEnabled || _findCoercionFromEmptyArray != CoercionAction.Fail) {
            JsonToken nextToken = jsonParser.nextToken();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            if (nextToken == jsonToken) {
                int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_findCoercionFromEmptyArray.ordinal()];
                if (i3 == 1) {
                    return getEmptyValue(deserializationContext);
                }
                if (i3 == 2 || i3 == 3) {
                    return getNullValue(deserializationContext);
                }
            } else if (isEnabled) {
                T _deserializeWrappedValue = _deserializeWrappedValue(jsonParser, deserializationContext);
                if (jsonParser.nextToken() != jsonToken) {
                    handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return _deserializeWrappedValue;
            }
        }
        return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    @Deprecated
    public T _deserializeFromEmpty(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.hasToken(JsonToken.START_ARRAY) || !deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
            return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
        }
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return null;
        }
        return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
    }

    public Object _deserializeFromEmptyString(JsonParser jsonParser, DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, String str) throws IOException {
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[coercionAction.ordinal()];
        if (i3 == 1) {
            return getEmptyValue(deserializationContext);
        }
        if (i3 != 4) {
            return null;
        }
        _checkCoercionFail(deserializationContext, coercionAction, cls, "", "empty String (\"\")");
        return null;
    }

    public T _deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ValueInstantiator valueInstantiator = getValueInstantiator();
        Class<?> handledType = handledType();
        String valueAsString = jsonParser.getValueAsString();
        if (valueInstantiator != null && valueInstantiator.canCreateFromString()) {
            return valueInstantiator.createFromString(deserializationContext, valueAsString);
        }
        if (valueAsString.isEmpty()) {
            return _deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionAction(logicalType(), handledType, CoercionInputShape.EmptyString), handledType, "empty String (\"\")");
        } else if (_isBlank(valueAsString)) {
            return _deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType(), handledType, CoercionAction.Fail), handledType, "blank String (all whitespace)");
        } else {
            if (valueInstantiator != null) {
                valueAsString = valueAsString.trim();
                if (valueInstantiator.canCreateFromInt() && deserializationContext.findCoercionAction(LogicalType.Integer, Integer.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                    return valueInstantiator.createFromInt(deserializationContext, _parseIntPrimitive(deserializationContext, valueAsString));
                }
                if (valueInstantiator.canCreateFromLong() && deserializationContext.findCoercionAction(LogicalType.Integer, Long.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                    return valueInstantiator.createFromLong(deserializationContext, _parseLongPrimitive(deserializationContext, valueAsString));
                }
                if (valueInstantiator.canCreateFromBoolean() && deserializationContext.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                    String trim = valueAsString.trim();
                    if ("true".equals(trim)) {
                        return valueInstantiator.createFromBoolean(deserializationContext, true);
                    }
                    if ("false".equals(trim)) {
                        return valueInstantiator.createFromBoolean(deserializationContext, false);
                    }
                }
            }
            Object[] objArr = {valueAsString};
            return deserializationContext.handleMissingInstantiator(handledType, valueInstantiator, deserializationContext.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", objArr);
        }
    }

    public T _deserializeWrappedValue(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return jsonParser.hasToken(JsonToken.START_ARRAY) ? handleNestedArrayForSingle(jsonParser, deserializationContext) : deserialize(jsonParser, deserializationContext);
    }

    @Deprecated
    public void _failDoubleToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        deserializationContext.reportInputMismatch(handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", jsonParser.getValueAsString(), str);
    }

    public CoercionAction _findCoercionFromBlankString(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionFromBlankString(logicalType(), handledType(), CoercionAction.Fail);
    }

    public CoercionAction _findCoercionFromEmptyArray(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyArray);
    }

    public CoercionAction _findCoercionFromEmptyString(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.fasterxml.jackson.databind.JsonDeserializer<?>, com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.deser.NullValueProvider _findNullProvider(com.fasterxml.jackson.databind.DeserializationContext r2, com.fasterxml.jackson.databind.BeanProperty r3, com.fasterxml.jackson.annotation.Nulls r4, com.fasterxml.jackson.databind.JsonDeserializer<?> r5) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r1 = this;
            com.fasterxml.jackson.annotation.Nulls r1 = com.fasterxml.jackson.annotation.Nulls.FAIL
            if (r4 != r1) goto L_0x001d
            if (r3 != 0) goto L_0x0018
            if (r5 != 0) goto L_0x000b
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            goto L_0x000f
        L_0x000b:
            java.lang.Class r1 = r5.handledType()
        L_0x000f:
            com.fasterxml.jackson.databind.JavaType r1 = r2.constructType(r1)
            com.fasterxml.jackson.databind.deser.impl.NullsFailProvider r1 = com.fasterxml.jackson.databind.deser.impl.NullsFailProvider.constructForRootValue(r1)
            return r1
        L_0x0018:
            com.fasterxml.jackson.databind.deser.impl.NullsFailProvider r1 = com.fasterxml.jackson.databind.deser.impl.NullsFailProvider.constructForProperty(r3)
            return r1
        L_0x001d:
            com.fasterxml.jackson.annotation.Nulls r1 = com.fasterxml.jackson.annotation.Nulls.AS_EMPTY
            r0 = 0
            if (r4 != r1) goto L_0x0072
            if (r5 != 0) goto L_0x0025
            return r0
        L_0x0025:
            boolean r1 = r5 instanceof com.fasterxml.jackson.databind.deser.BeanDeserializerBase
            if (r1 == 0) goto L_0x0052
            r1 = r5
            com.fasterxml.jackson.databind.deser.BeanDeserializerBase r1 = (com.fasterxml.jackson.databind.deser.BeanDeserializerBase) r1
            com.fasterxml.jackson.databind.deser.ValueInstantiator r4 = r1.getValueInstantiator()
            boolean r4 = r4.canCreateUsingDefault()
            if (r4 != 0) goto L_0x0052
            if (r3 != 0) goto L_0x003d
            com.fasterxml.jackson.databind.JavaType r1 = r1.getValueType()
            goto L_0x0041
        L_0x003d:
            com.fasterxml.jackson.databind.JavaType r1 = r3.getType()
        L_0x0041:
            java.lang.String r3 = "Cannot create empty instance of %s, no default Creator"
            java.lang.Object[] r4 = new java.lang.Object[]{r1}
            java.lang.String r3 = java.lang.String.format(r3, r4)
            java.lang.Object r1 = r2.reportBadDefinition(r1, r3)
            com.fasterxml.jackson.databind.deser.NullValueProvider r1 = (com.fasterxml.jackson.databind.deser.NullValueProvider) r1
            return r1
        L_0x0052:
            com.fasterxml.jackson.databind.util.AccessPattern r1 = r5.getEmptyAccessPattern()
            com.fasterxml.jackson.databind.util.AccessPattern r3 = com.fasterxml.jackson.databind.util.AccessPattern.ALWAYS_NULL
            if (r1 != r3) goto L_0x005f
            com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider r1 = com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider.nuller()
            return r1
        L_0x005f:
            com.fasterxml.jackson.databind.util.AccessPattern r3 = com.fasterxml.jackson.databind.util.AccessPattern.CONSTANT
            if (r1 != r3) goto L_0x006c
            java.lang.Object r1 = r5.getEmptyValue(r2)
            com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider r1 = com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider.forValue(r1)
            return r1
        L_0x006c:
            com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider r1 = new com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider
            r1.<init>(r5)
            return r1
        L_0x0072:
            com.fasterxml.jackson.annotation.Nulls r1 = com.fasterxml.jackson.annotation.Nulls.SKIP
            if (r4 != r1) goto L_0x007b
            com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider r1 = com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider.skipper()
            return r1
        L_0x007b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._findNullProvider(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanProperty, com.fasterxml.jackson.annotation.Nulls, com.fasterxml.jackson.databind.JsonDeserializer):com.fasterxml.jackson.databind.deser.NullValueProvider");
    }

    public boolean _hasTextualNull(String str) {
        return AbstractJsonLexerKt.NULL.equals(str);
    }

    public final boolean _intOverflow(long j2) {
        return j2 < SieveCacheKt.NodeMetaAndPreviousMask || j2 > SieveCacheKt.NodeLinkMask;
    }

    @Deprecated
    public boolean _isEmptyOrTextualNull(String str) {
        return str.isEmpty() || AbstractJsonLexerKt.NULL.equals(str);
    }

    public boolean _isFalse(String str) {
        char charAt = str.charAt(0);
        if (charAt == 'f') {
            return "false".equals(str);
        }
        if (charAt == 'F') {
            return "FALSE".equals(str) || "False".equals(str);
        }
        return false;
    }

    public final boolean _isIntNumber(String str) {
        int i3;
        int length = str.length();
        if (length <= 0) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt != '-' && charAt != '+') {
            i3 = 0;
        } else if (length == 1) {
            return false;
        } else {
            i3 = 1;
        }
        while (i3 < length) {
            char charAt2 = str.charAt(i3);
            if (charAt2 > '9' || charAt2 < '0') {
                return false;
            }
            i3++;
        }
        return true;
    }

    public final boolean _isNaN(String str) {
        return "NaN".equals(str);
    }

    public final boolean _isNegInf(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    public final boolean _isPosInf(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    public boolean _isTrue(String str) {
        char charAt = str.charAt(0);
        if (charAt == 't') {
            return "true".equals(str);
        }
        if (charAt == 'T') {
            return "TRUE".equals(str) || "True".equals(str);
        }
        return false;
    }

    public Number _nonNullNumber(Number number) {
        if (number == null) {
            return 0;
        }
        return number;
    }

    public final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            str = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Boolean) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 6) {
                str = jsonParser.getText();
            } else if (currentTokenId == 7) {
                return _coerceBooleanFromInt(jsonParser, deserializationContext, cls);
            } else {
                switch (currentTokenId) {
                    case 9:
                        return Boolean.TRUE;
                    case 10:
                        return Boolean.FALSE;
                    case 11:
                        return null;
                    default:
                        return (Boolean) deserializationContext.handleUnexpectedToken(cls, jsonParser);
                }
            }
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str, LogicalType.Boolean, cls);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return null;
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return Boolean.FALSE;
        }
        String trim = str.trim();
        int length = trim.length();
        if (length == 4) {
            if (_isTrue(trim)) {
                return Boolean.TRUE;
            }
        } else if (length == 5 && _isFalse(trim)) {
            return Boolean.FALSE;
        }
        if (_checkTextualNull(deserializationContext, trim)) {
            return null;
        }
        return (Boolean) deserializationContext.handleWeirdStringValue(cls, trim, "only \"true\" or \"false\" recognized", new Object[0]);
    }

    @Deprecated
    public boolean _parseBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        _verifyNumberForScalarCoercion(deserializationContext, jsonParser);
        return !SchemaSymbols.ATTVAL_FALSE_0.equals(jsonParser.getText());
    }

    @Deprecated
    public final boolean _parseBooleanPrimitive(DeserializationContext deserializationContext, JsonParser jsonParser, Class<?> cls) throws IOException {
        return _parseBooleanPrimitive(jsonParser, deserializationContext);
    }

    public final byte _parseBytePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                } else if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getByteValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Byte.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return 0;
                        }
                        return jsonParser.getByteValue();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Byte) handleNestedArrayForSingle(jsonParser, deserializationContext)).byteValue();
                }
                byte _parseBytePrimitive = _parseBytePrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseBytePrimitive;
            }
            return ((Byte) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Byte.TYPE), jsonParser)).byteValue();
        }
        str = deserializationContext.extractScalarFromObject(jsonParser, this, Byte.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str, LogicalType.Integer, Byte.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0;
        } else {
            String trim = str.trim();
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return 0;
            }
            try {
                int parseInt = NumberInput.parseInt(trim);
                return _byteOverflow(parseInt) ? ((Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 8-bit value", new Object[0])).byteValue() : (byte) parseInt;
            } catch (IllegalArgumentException unused) {
                return ((Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid `byte` value", new Object[0])).byteValue();
            }
        }
    }

    public Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        long j2;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            str = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
        } else if (currentTokenId == 3) {
            return _parseDateFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Date) getNullValue(deserializationContext);
            }
            if (currentTokenId == 6) {
                str = jsonParser.getText();
            } else if (currentTokenId != 7) {
                return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            } else {
                try {
                    j2 = jsonParser.getLongValue();
                } catch (StreamReadException unused) {
                    j2 = ((Number) deserializationContext.handleWeirdNumberValue(this._valueClass, jsonParser.getNumberValue(), "not a valid 64-bit `long` for creating `java.util.Date`", new Object[0])).longValue();
                }
                return new Date(j2);
            }
        }
        return _parseDate(str.trim(), deserializationContext);
    }

    public Date _parseDateFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CoercionAction _findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (isEnabled || _findCoercionFromEmptyArray != CoercionAction.Fail) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_findCoercionFromEmptyArray.ordinal()];
                if (i3 == 1) {
                    return (Date) getEmptyValue(deserializationContext);
                }
                if (i3 == 2 || i3 == 3) {
                    return (Date) getNullValue(deserializationContext);
                }
            } else if (isEnabled) {
                if (nextToken == JsonToken.START_ARRAY) {
                    return (Date) handleNestedArrayForSingle(jsonParser, deserializationContext);
                }
                Date _parseDate = _parseDate(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseDate;
            }
        }
        return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r0 != 8) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final double _parseDoublePrimitive(com.fasterxml.jackson.core.JsonParser r6, com.fasterxml.jackson.databind.DeserializationContext r7) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r6.currentTokenId()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x0069
            r1 = 3
            if (r0 == r1) goto L_0x0039
            r1 = 11
            if (r0 == r1) goto L_0x0035
            r1 = 6
            if (r0 == r1) goto L_0x0030
            r1 = 7
            if (r0 == r1) goto L_0x001b
            r5 = 8
            if (r0 == r5) goto L_0x002b
            goto L_0x005c
        L_0x001b:
            java.lang.Class r0 = java.lang.Double.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r5 = r5._checkIntToFloatCoercion(r6, r7, r0)
            com.fasterxml.jackson.databind.cfg.CoercionAction r7 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r5 != r7) goto L_0x0026
            return r2
        L_0x0026:
            com.fasterxml.jackson.databind.cfg.CoercionAction r7 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r5 != r7) goto L_0x002b
            return r2
        L_0x002b:
            double r5 = r6.getDoubleValue()
            return r5
        L_0x0030:
            java.lang.String r0 = r6.getText()
            goto L_0x006f
        L_0x0035:
            r5._verifyNullForPrimitive(r7)
            return r2
        L_0x0039:
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS
            boolean r0 = r7.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r0)
            if (r0 == 0) goto L_0x005c
            com.fasterxml.jackson.core.JsonToken r0 = r6.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r0 != r1) goto L_0x0054
            java.lang.Object r5 = r5.handleNestedArrayForSingle(r6, r7)
            java.lang.Double r5 = (java.lang.Double) r5
            double r5 = r5.doubleValue()
            return r5
        L_0x0054:
            double r0 = r5._parseDoublePrimitive((com.fasterxml.jackson.core.JsonParser) r6, (com.fasterxml.jackson.databind.DeserializationContext) r7)
            r5._verifyEndArrayForSingle(r6, r7)
            return r0
        L_0x005c:
            java.lang.Class r5 = java.lang.Double.TYPE
            java.lang.Object r5 = r7.handleUnexpectedToken((java.lang.Class<?>) r5, (com.fasterxml.jackson.core.JsonParser) r6)
            java.lang.Number r5 = (java.lang.Number) r5
            double r5 = r5.doubleValue()
            return r5
        L_0x0069:
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.String r0 = r7.extractScalarFromObject(r6, r5, r0)
        L_0x006f:
            java.lang.Double r1 = r5._checkDoubleSpecialValue(r0)
            if (r1 == 0) goto L_0x007a
            double r5 = r1.doubleValue()
            return r5
        L_0x007a:
            com.fasterxml.jackson.databind.type.LogicalType r1 = com.fasterxml.jackson.databind.type.LogicalType.Integer
            java.lang.Class r4 = java.lang.Double.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r1 = r5._checkFromStringCoercion(r7, r0, r1, r4)
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r1 != r4) goto L_0x008a
            r5._verifyNullForPrimitive(r7)
            return r2
        L_0x008a:
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r1 != r4) goto L_0x008f
            return r2
        L_0x008f:
            java.lang.String r0 = r0.trim()
            boolean r1 = r5._hasTextualNull(r0)
            if (r1 == 0) goto L_0x009d
            r5._verifyNullForPrimitiveCoercion(r7, r0)
            return r2
        L_0x009d:
            double r5 = r5._parseDoublePrimitive(r6, r7, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseDoublePrimitive(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        if (r0 != 8) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float _parseFloatPrimitive(com.fasterxml.jackson.core.JsonParser r5, com.fasterxml.jackson.databind.DeserializationContext r6) throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r5.currentTokenId()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x0068
            r1 = 3
            if (r0 == r1) goto L_0x0038
            r1 = 11
            if (r0 == r1) goto L_0x0034
            r1 = 6
            if (r0 == r1) goto L_0x002f
            r1 = 7
            if (r0 == r1) goto L_0x001a
            r4 = 8
            if (r0 == r4) goto L_0x002a
            goto L_0x005b
        L_0x001a:
            java.lang.Class r0 = java.lang.Float.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = r4._checkIntToFloatCoercion(r5, r6, r0)
            com.fasterxml.jackson.databind.cfg.CoercionAction r6 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r4 != r6) goto L_0x0025
            return r2
        L_0x0025:
            com.fasterxml.jackson.databind.cfg.CoercionAction r6 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r4 != r6) goto L_0x002a
            return r2
        L_0x002a:
            float r4 = r5.getFloatValue()
            return r4
        L_0x002f:
            java.lang.String r0 = r5.getText()
            goto L_0x006e
        L_0x0034:
            r4._verifyNullForPrimitive(r6)
            return r2
        L_0x0038:
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS
            boolean r0 = r6.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r0)
            if (r0 == 0) goto L_0x005b
            com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r0 != r1) goto L_0x0053
            java.lang.Object r4 = r4.handleNestedArrayForSingle(r5, r6)
            java.lang.Float r4 = (java.lang.Float) r4
            float r4 = r4.floatValue()
            return r4
        L_0x0053:
            float r0 = r4._parseFloatPrimitive((com.fasterxml.jackson.core.JsonParser) r5, (com.fasterxml.jackson.databind.DeserializationContext) r6)
            r4._verifyEndArrayForSingle(r5, r6)
            return r0
        L_0x005b:
            java.lang.Class r4 = java.lang.Float.TYPE
            java.lang.Object r4 = r6.handleUnexpectedToken((java.lang.Class<?>) r4, (com.fasterxml.jackson.core.JsonParser) r5)
            java.lang.Number r4 = (java.lang.Number) r4
            float r4 = r4.floatValue()
            return r4
        L_0x0068:
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.String r0 = r6.extractScalarFromObject(r5, r4, r0)
        L_0x006e:
            java.lang.Float r1 = r4._checkFloatSpecialValue(r0)
            if (r1 == 0) goto L_0x0079
            float r4 = r1.floatValue()
            return r4
        L_0x0079:
            com.fasterxml.jackson.databind.type.LogicalType r1 = com.fasterxml.jackson.databind.type.LogicalType.Integer
            java.lang.Class r3 = java.lang.Float.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r1 = r4._checkFromStringCoercion(r6, r0, r1, r3)
            com.fasterxml.jackson.databind.cfg.CoercionAction r3 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r1 != r3) goto L_0x0089
            r4._verifyNullForPrimitive(r6)
            return r2
        L_0x0089:
            com.fasterxml.jackson.databind.cfg.CoercionAction r3 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r1 != r3) goto L_0x008e
            return r2
        L_0x008e:
            java.lang.String r0 = r0.trim()
            boolean r1 = r4._hasTextualNull(r0)
            if (r1 == 0) goto L_0x009c
            r4._verifyNullForPrimitiveCoercion(r6, r0)
            return r2
        L_0x009c:
            float r4 = r4._parseFloatPrimitive(r5, r6, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseFloatPrimitive(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):float");
    }

    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                } else if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getIntValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Integer.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return 0;
                        }
                        return jsonParser.getValueAsInt();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Integer) handleNestedArrayForSingle(jsonParser, deserializationContext)).intValue();
                }
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseIntPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken((Class<?>) Integer.TYPE, jsonParser)).intValue();
        }
        str = deserializationContext.extractScalarFromObject(jsonParser, this, Integer.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str, LogicalType.Integer, Integer.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0;
        } else {
            String trim = str.trim();
            if (!_hasTextualNull(trim)) {
                return _parseIntPrimitive(deserializationContext, trim);
            }
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return 0;
        }
    }

    public final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            str = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Integer) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Integer) getNullValue(deserializationContext);
            }
            if (currentTokenId == 6) {
                str = jsonParser.getText();
            } else if (currentTokenId == 7) {
                return Integer.valueOf(jsonParser.getIntValue());
            } else {
                if (currentTokenId != 8) {
                    return (Integer) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                    return (Integer) getNullValue(deserializationContext);
                }
                if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                    return (Integer) getEmptyValue(deserializationContext);
                }
                return Integer.valueOf(jsonParser.getValueAsInt());
            }
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return (Integer) getNullValue(deserializationContext);
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (Integer) getEmptyValue(deserializationContext);
        }
        String trim = str.trim();
        if (_checkTextualNull(deserializationContext, trim)) {
            return (Integer) getNullValue(deserializationContext);
        }
        return _parseInteger(deserializationContext, trim);
    }

    public final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            str = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Long) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Long) getNullValue(deserializationContext);
            }
            if (currentTokenId == 6) {
                str = jsonParser.getText();
            } else if (currentTokenId == 7) {
                return Long.valueOf(jsonParser.getLongValue());
            } else {
                if (currentTokenId != 8) {
                    return (Long) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                    return (Long) getNullValue(deserializationContext);
                }
                if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                    return (Long) getEmptyValue(deserializationContext);
                }
                return Long.valueOf(jsonParser.getValueAsLong());
            }
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return (Long) getNullValue(deserializationContext);
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (Long) getEmptyValue(deserializationContext);
        }
        String trim = str.trim();
        if (_checkTextualNull(deserializationContext, trim)) {
            return (Long) getNullValue(deserializationContext);
        }
        return _parseLong(deserializationContext, trim);
    }

    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                } else if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getLongValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Long.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return 0;
                        }
                        return jsonParser.getValueAsLong();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Long) handleNestedArrayForSingle(jsonParser, deserializationContext)).longValue();
                }
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseLongPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken((Class<?>) Long.TYPE, jsonParser)).longValue();
        }
        str = deserializationContext.extractScalarFromObject(jsonParser, this, Long.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str, LogicalType.Integer, Long.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0;
        } else {
            String trim = str.trim();
            if (!_hasTextualNull(trim)) {
                return _parseLongPrimitive(deserializationContext, trim);
            }
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return 0;
        }
    }

    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                } else if (currentTokenId == 6) {
                    str = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getShortValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Short.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return 0;
                        }
                        return jsonParser.getShortValue();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Short) handleNestedArrayForSingle(jsonParser, deserializationContext)).shortValue();
                }
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseShortPrimitive;
            }
            return ((Short) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Short.TYPE), jsonParser)).shortValue();
        }
        str = deserializationContext.extractScalarFromObject(jsonParser, this, Short.TYPE);
        LogicalType logicalType = LogicalType.Integer;
        Class cls = Short.TYPE;
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, str, logicalType, cls);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0;
        } else {
            String trim = str.trim();
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return 0;
            }
            try {
                int parseInt = NumberInput.parseInt(trim);
                return _shortOverflow(parseInt) ? ((Short) deserializationContext.handleWeirdStringValue(cls, trim, "overflow, value cannot be represented as 16-bit value", new Object[0])).shortValue() : (short) parseInt;
            } catch (IllegalArgumentException unused) {
                return ((Short) deserializationContext.handleWeirdStringValue(Short.TYPE, trim, "not a valid `short` value", new Object[0])).shortValue();
            }
        }
    }

    @Deprecated
    public final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _parseString(jsonParser, deserializationContext, NullsConstantProvider.nuller());
    }

    public void _reportFailedNullCoerce(DeserializationContext deserializationContext, boolean z2, Enum<?> enumR, String str) throws JsonMappingException {
        deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", str, _coercedTypeDesc(), z2 ? "enable" : "disable", enumR.getDeclaringClass().getSimpleName(), enumR.name());
    }

    public final boolean _shortOverflow(int i3) {
        return i3 < -32768 || i3 > 32767;
    }

    public void _verifyEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            handleMissingEndArrayForSingle(jsonParser, deserializationContext);
        }
    }

    public final void _verifyNullForPrimitive(DeserializationContext deserializationContext) throws JsonMappingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", _coercedTypeDesc());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.fasterxml.jackson.databind.MapperFeature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.fasterxml.jackson.databind.DeserializationFeature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.fasterxml.jackson.databind.MapperFeature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.fasterxml.jackson.databind.MapperFeature} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void _verifyNullForPrimitiveCoercion(com.fasterxml.jackson.databind.DeserializationContext r5, java.lang.String r6) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r4 = this;
            com.fasterxml.jackson.databind.MapperFeature r0 = com.fasterxml.jackson.databind.MapperFeature.ALLOW_COERCION_OF_SCALARS
            boolean r1 = r5.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r0)
            if (r1 != 0) goto L_0x000a
            r1 = 1
            goto L_0x0013
        L_0x000a:
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES
            boolean r1 = r5.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r0)
            if (r1 == 0) goto L_0x0027
            r1 = 0
        L_0x0013:
            boolean r2 = r6.isEmpty()
            if (r2 == 0) goto L_0x001c
            java.lang.String r6 = "empty String (\"\")"
            goto L_0x0024
        L_0x001c:
            java.lang.String r2 = "String \""
            java.lang.String r3 = "\""
            java.lang.String r6 = A.a.l(r2, r6, r3)
        L_0x0024:
            r4._reportFailedNullCoerce(r5, r1, r0, r6)
        L_0x0027:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._verifyNullForPrimitiveCoercion(com.fasterxml.jackson.databind.DeserializationContext, java.lang.String):void");
    }

    @Deprecated
    public final void _verifyNullForScalarCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            _reportFailedNullCoerce(deserializationContext, true, mapperFeature, str.isEmpty() ? "empty String (\"\")" : A.a.l("String \"", str, "\""));
        }
    }

    @Deprecated
    public void _verifyNumberForScalarCoercion(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot coerce Number (%s) to %s (enable `%s.%s` to allow)", jsonParser.getText(), _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
        }
    }

    @Deprecated
    public void _verifyStringForScalarCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot coerce String \"%s\" to %s (enable `%s.%s` to allow)", str, _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public NullValueProvider findContentNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Nulls findContentNullStyle = findContentNullStyle(deserializationContext, beanProperty);
        if (findContentNullStyle == Nulls.SKIP) {
            return NullsConstantProvider.skipper();
        }
        if (findContentNullStyle != Nulls.FAIL) {
            NullValueProvider _findNullProvider = _findNullProvider(deserializationContext, beanProperty, findContentNullStyle, jsonDeserializer);
            return _findNullProvider != null ? _findNullProvider : jsonDeserializer;
        } else if (beanProperty != null) {
            return NullsFailProvider.constructForProperty(beanProperty, beanProperty.getType().getContentType());
        } else {
            JavaType constructType = deserializationContext.constructType(jsonDeserializer.handledType());
            if (constructType.isContainerType()) {
                constructType = constructType.getContentType();
            }
            return NullsFailProvider.constructForRootValue(constructType);
        }
    }

    public Nulls findContentNullStyle(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        return beanProperty != null ? beanProperty.getMetadata().getContentNulls() : deserializationContext.getConfig().getDefaultSetterInfo().getContentNulls();
    }

    public JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        AnnotatedMember member;
        Object findDeserializationContentConverter;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!_neitherNull(annotationIntrospector, beanProperty) || (member = beanProperty.getMember()) == null || (findDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(member)) == null) {
            return jsonDeserializer;
        }
        Converter<Object, Object> converterInstance = deserializationContext.converterInstance(beanProperty.getMember(), findDeserializationContentConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer2 = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
        }
        return new StdDelegatingDeserializer(converterInstance, inputType, jsonDeserializer2);
    }

    public JsonDeserializer<Object> findDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    public Boolean findFormatFeature(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls, JsonFormat.Feature feature) {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, cls);
        if (findFormatOverrides != null) {
            return findFormatOverrides.getFeature(feature);
        }
        return null;
    }

    public JsonFormat.Value findFormatOverrides(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls) {
        return beanProperty != null ? beanProperty.findPropertyFormat(deserializationContext.getConfig(), cls) : deserializationContext.getDefaultPropertyFormat(cls);
    }

    public final NullValueProvider findValueNullProvider(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, PropertyMetadata propertyMetadata) throws JsonMappingException {
        if (settableBeanProperty != null) {
            return _findNullProvider(deserializationContext, settableBeanProperty, propertyMetadata.getValueNulls(), settableBeanProperty.getValueDeserializer());
        }
        return null;
    }

    @Deprecated
    public final Class<?> getValueClass() {
        return this._valueClass;
    }

    public ValueInstantiator getValueInstantiator() {
        return null;
    }

    public JavaType getValueType() {
        return this._valueType;
    }

    public void handleMissingEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        deserializationContext.reportWrongTokenException((JsonDeserializer<?>) this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", handledType().getName());
    }

    public Object handleNestedArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String nameOf = ClassUtil.nameOf(this._valueClass);
        JsonToken jsonToken = JsonToken.START_ARRAY;
        return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, "Cannot deserialize instance of " + nameOf + " out of " + jsonToken + " token: nested Arrays not allowed with DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS", new Object[0]);
    }

    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        if (obj == null) {
            obj = handledType();
        }
        if (!deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            jsonParser.skipChildren();
        }
    }

    public Class<?> handledType() {
        return this._valueClass;
    }

    public boolean isDefaultDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return ClassUtil.isJacksonStdImpl((Object) jsonDeserializer);
    }

    public boolean isDefaultKeyDeserializer(KeyDeserializer keyDeserializer) {
        return ClassUtil.isJacksonStdImpl((Object) keyDeserializer);
    }

    public static final double _parseDouble(String str, boolean z2) throws NumberFormatException {
        return NumberInput.parseDouble(str, z2);
    }

    public CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str, LogicalType logicalType, Class<?> cls) throws IOException {
        if (str.isEmpty()) {
            return _checkCoercionFail(deserializationContext, deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.EmptyString), cls, str, "empty String (\"\")");
        } else if (_isBlank(str)) {
            return _checkCoercionFail(deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType, cls, CoercionAction.Fail), cls, str, "blank String (all whitespace)");
        } else if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS)) {
            return CoercionAction.TryConvert;
        } else {
            CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.String);
            if (findCoercionAction == CoercionAction.Fail) {
                deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", str, _coercedTypeDesc());
            }
            return findCoercionAction;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean _parseBooleanPrimitive(com.fasterxml.jackson.core.JsonParser r6, com.fasterxml.jackson.databind.DeserializationContext r7) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r6.currentTokenId()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x005c
            r3 = 3
            if (r0 == r3) goto L_0x002c
            r3 = 6
            if (r0 == r3) goto L_0x0027
            r3 = 7
            if (r0 == r3) goto L_0x001a
            switch(r0) {
                case 9: goto L_0x0019;
                case 10: goto L_0x0018;
                case 11: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x004f
        L_0x0015:
            r5._verifyNullForPrimitive(r7)
        L_0x0018:
            return r2
        L_0x0019:
            return r1
        L_0x001a:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.Class r1 = java.lang.Boolean.TYPE
            java.lang.Boolean r5 = r5._coerceBooleanFromInt(r6, r7, r1)
            boolean r5 = r0.equals(r5)
            return r5
        L_0x0027:
            java.lang.String r6 = r6.getText()
            goto L_0x0062
        L_0x002c:
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS
            boolean r0 = r7.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r0)
            if (r0 == 0) goto L_0x004f
            com.fasterxml.jackson.core.JsonToken r0 = r6.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r0 != r1) goto L_0x0047
            java.lang.Object r5 = r5.handleNestedArrayForSingle(r6, r7)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        L_0x0047:
            boolean r0 = r5._parseBooleanPrimitive(r6, r7)
            r5._verifyEndArrayForSingle(r6, r7)
            return r0
        L_0x004f:
            java.lang.Class r5 = java.lang.Boolean.TYPE
            java.lang.Object r5 = r7.handleUnexpectedToken((java.lang.Class<?>) r5, (com.fasterxml.jackson.core.JsonParser) r6)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        L_0x005c:
            java.lang.Class r0 = java.lang.Boolean.TYPE
            java.lang.String r6 = r7.extractScalarFromObject(r6, r5, r0)
        L_0x0062:
            com.fasterxml.jackson.databind.type.LogicalType r0 = com.fasterxml.jackson.databind.type.LogicalType.Boolean
            java.lang.Class r3 = java.lang.Boolean.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r0 = r5._checkFromStringCoercion(r7, r6, r0, r3)
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r0 != r4) goto L_0x0072
            r5._verifyNullForPrimitive(r7)
            return r2
        L_0x0072:
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r0 != r4) goto L_0x0077
            return r2
        L_0x0077:
            java.lang.String r6 = r6.trim()
            int r0 = r6.length()
            r4 = 4
            if (r0 != r4) goto L_0x0089
            boolean r0 = r5._isTrue(r6)
            if (r0 == 0) goto L_0x0093
            return r1
        L_0x0089:
            r1 = 5
            if (r0 != r1) goto L_0x0093
            boolean r0 = r5._isFalse(r6)
            if (r0 == 0) goto L_0x0093
            return r2
        L_0x0093:
            boolean r0 = r5._hasTextualNull(r6)
            if (r0 == 0) goto L_0x009d
            r5._verifyNullForPrimitiveCoercion(r7, r6)
            return r2
        L_0x009d:
            java.lang.String r5 = "only \"true\"/\"True\"/\"TRUE\" or \"false\"/\"False\"/\"FALSE\" recognized"
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.Object r5 = r7.handleWeirdStringValue(r3, r6, r5, r0)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            boolean r5 = r6.equals(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseBooleanPrimitive(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):boolean");
    }

    public JavaType getValueType(DeserializationContext deserializationContext) {
        JavaType javaType = this._valueType;
        if (javaType != null) {
            return javaType;
        }
        return deserializationContext.constructType(this._valueClass);
    }

    public final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext, NullValueProvider nullValueProvider) throws IOException {
        String valueAsString;
        CoercionAction coercionAction = CoercionAction.TryConvert;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            return deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
        }
        if (currentTokenId != 12) {
            switch (currentTokenId) {
                case 6:
                    return jsonParser.getText();
                case 7:
                    coercionAction = _checkIntToStringCoercion(jsonParser, deserializationContext, this._valueClass);
                    break;
                case 8:
                    coercionAction = _checkFloatToStringCoercion(jsonParser, deserializationContext, this._valueClass);
                    break;
                case 9:
                case 10:
                    coercionAction = _checkBooleanToStringCoercion(jsonParser, deserializationContext, this._valueClass);
                    break;
            }
            if (coercionAction == CoercionAction.AsNull) {
                return (String) nullValueProvider.getNullValue(deserializationContext);
            }
            if (coercionAction == CoercionAction.AsEmpty) {
                return "";
            }
            if (!jsonParser.currentToken().isScalarValue() || (valueAsString = jsonParser.getValueAsString()) == null) {
                return (String) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
            }
            return valueAsString;
        }
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject instanceof byte[]) {
            return deserializationContext.getBase64Variant().encode((byte[]) embeddedObject, false);
        }
        if (embeddedObject == null) {
            return null;
        }
        return embeddedObject.toString();
    }

    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? Object.class : javaType.getRawClass();
        this._valueType = javaType;
    }

    public StdDeserializer(StdDeserializer<?> stdDeserializer) {
        this._valueClass = stdDeserializer._valueClass;
        this._valueType = stdDeserializer._valueType;
    }

    public Date _parseDate(String str, DeserializationContext deserializationContext) throws IOException {
        try {
            if (str.isEmpty()) {
                if (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_checkFromStringCoercion(deserializationContext, str).ordinal()] != 1) {
                    return null;
                }
                return new Date(0);
            } else if (_hasTextualNull(str)) {
                return null;
            } else {
                return deserializationContext.parseDate(str);
            }
        } catch (IllegalArgumentException e3) {
            return (Date) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid representation (error: %s)", ClassUtil.exceptionMessage(e3));
        }
    }

    public final Integer _parseInteger(DeserializationContext deserializationContext, String str) throws IOException {
        Class<Integer> cls = Integer.class;
        try {
            if (str.length() <= 9) {
                return Integer.valueOf(NumberInput.parseInt(str));
            }
            long parseLong = NumberInput.parseLong(str);
            if (_intOverflow(parseLong)) {
                return (Integer) deserializationContext.handleWeirdStringValue(cls, str, "Overflow: numeric value (%s) out of range of `java.lang.Integer` (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            return Integer.valueOf((int) parseLong);
        } catch (IllegalArgumentException unused) {
            return (Integer) deserializationContext.handleWeirdStringValue(cls, str, "not a valid `java.lang.Integer` value", new Object[0]);
        }
    }

    public final Long _parseLong(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return Long.valueOf(NumberInput.parseLong(str));
        } catch (IllegalArgumentException unused) {
            return (Long) deserializationContext.handleWeirdStringValue(Long.class, str, "not a valid `java.lang.Long` value", new Object[0]);
        }
    }

    public final int _parseIntPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            if (str.length() <= 9) {
                return NumberInput.parseInt(str);
            }
            long parseLong = NumberInput.parseLong(str);
            return _intOverflow(parseLong) ? _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE)).intValue() : (int) parseLong;
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "not a valid `int` value", new Object[0])).intValue();
        }
    }

    public final long _parseLongPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseLong(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Long.TYPE, str, "not a valid `long` value", new Object[0])).longValue();
        }
    }

    public final double _parseDoublePrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return _parseDouble(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    public final float _parseFloatPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseFloat(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }

    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return _parseDouble(str, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseFloat(str, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }
}
