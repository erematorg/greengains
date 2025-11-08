package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {

    public static class Std extends FromStringDeserializer<Object> {
        protected static final String LOCALE_EXT_MARKER = "_#";
        public static final int STD_CHARSET = 9;
        public static final int STD_CLASS = 4;
        public static final int STD_CURRENCY = 6;
        public static final int STD_FILE = 1;
        public static final int STD_INET_ADDRESS = 11;
        public static final int STD_INET_SOCKET_ADDRESS = 12;
        public static final int STD_JAVA_TYPE = 5;
        public static final int STD_LOCALE = 8;
        public static final int STD_PATTERN = 7;
        public static final int STD_TIME_ZONE = 10;
        public static final int STD_URI = 3;
        public static final int STD_URL = 2;
        private static final long serialVersionUID = 1;
        protected final int _kind;

        public Std(Class<?> cls, int i3) {
            super(cls);
            this._kind = i3;
        }

        private Locale _deSerializeBCP47Locale(String str, int i3, String str2, String str3, int i4) {
            String str4 = "";
            if (i4 > 0 && i4 > i3) {
                try {
                    str4 = str.substring(i3 + 1, i4);
                } catch (IllformedLocaleException unused) {
                    return new Locale(str2, str3, str4);
                }
            }
            String substring = str.substring(i4 + 2);
            int indexOf = substring.indexOf(95);
            if (indexOf < 0) {
                int indexOf2 = substring.indexOf(45);
                return indexOf2 < 0 ? new Locale.Builder().setLanguage(str2).setRegion(str3).setVariant(str4).setScript(substring).build() : new Locale.Builder().setLanguage(str2).setRegion(str3).setVariant(str4).setExtension(substring.charAt(0), substring.substring(indexOf2 + 1)).build();
            }
            int length = substring.length();
            Locale.Builder script = new Locale.Builder().setLanguage(str2).setRegion(str3).setVariant(str4).setScript(substring.substring(0, indexOf));
            int i5 = indexOf + 1;
            if (i5 < length) {
                script = script.setExtension(substring.charAt(i5), substring.substring(Math.min(length, indexOf + 3)));
            }
            return script.build();
        }

        private Locale _deserializeLocale(String str, DeserializationContext deserializationContext) throws IOException {
            int _firstHyphenOrUnderscore = _firstHyphenOrUnderscore(str);
            if (_firstHyphenOrUnderscore < 0) {
                return new Locale(str);
            }
            String substring = str.substring(0, _firstHyphenOrUnderscore);
            String substring2 = str.substring(_firstHyphenOrUnderscore + 1);
            int _firstHyphenOrUnderscore2 = _firstHyphenOrUnderscore(substring2);
            if (_firstHyphenOrUnderscore2 < 0) {
                return new Locale(substring, substring2);
            }
            String substring3 = substring2.substring(0, _firstHyphenOrUnderscore2);
            int indexOf = substring2.indexOf(LOCALE_EXT_MARKER);
            return indexOf < 0 ? new Locale(substring, substring3, substring2.substring(_firstHyphenOrUnderscore2 + 1)) : _deSerializeBCP47Locale(substring2, _firstHyphenOrUnderscore2, substring, substring3, indexOf);
        }

        public Object _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            switch (this._kind) {
                case 1:
                    return new File(str);
                case 2:
                    return new URL(str);
                case 3:
                    return URI.create(str);
                case 4:
                    try {
                        return deserializationContext.findClass(str);
                    } catch (Exception e3) {
                        return deserializationContext.handleInstantiationProblem(this._valueClass, str, ClassUtil.getRootCause(e3));
                    }
                case 5:
                    return deserializationContext.getTypeFactory().constructFromCanonical(str);
                case 6:
                    return Currency.getInstance(str);
                case 7:
                    return Pattern.compile(str);
                case 8:
                    return _deserializeLocale(str, deserializationContext);
                case 9:
                    return Charset.forName(str);
                case 10:
                    return TimeZone.getTimeZone(str);
                case 11:
                    return InetAddress.getByName(str);
                case 12:
                    if (str.startsWith("[")) {
                        int lastIndexOf = str.lastIndexOf(93);
                        if (lastIndexOf != -1) {
                            int indexOf = str.indexOf(58, lastIndexOf);
                            return new InetSocketAddress(str.substring(0, lastIndexOf + 1), indexOf > -1 ? Integer.parseInt(str.substring(indexOf + 1)) : 0);
                        }
                        throw new InvalidFormatException(deserializationContext.getParser(), "Bracketed IPv6 address must contain closing bracket", (Object) str, (Class<?>) InetSocketAddress.class);
                    }
                    int indexOf2 = str.indexOf(58);
                    if (indexOf2 >= 0) {
                        int i3 = indexOf2 + 1;
                        if (str.indexOf(58, i3) < 0) {
                            return new InetSocketAddress(str.substring(0, indexOf2), Integer.parseInt(str.substring(i3)));
                        }
                    }
                    return new InetSocketAddress(str, 0);
                default:
                    VersionUtil.throwInternal();
                    return null;
            }
        }

        public Object _deserializeFromEmptyStringDefault(DeserializationContext deserializationContext) throws IOException {
            return getEmptyValue(deserializationContext);
        }

        public int _firstHyphenOrUnderscore(String str) {
            int length = str.length();
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                if (charAt == '_' || charAt == '-') {
                    return i3;
                }
            }
            return -1;
        }

        public boolean _shouldTrim() {
            return this._kind != 7;
        }

        public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            int i3 = this._kind;
            return i3 != 3 ? i3 != 8 ? super.getEmptyValue(deserializationContext) : Locale.ROOT : URI.create("");
        }
    }

    public static class StringBufferDeserializer extends FromStringDeserializer<Object> {
        public StringBufferDeserializer() {
            super(StringBuffer.class);
        }

        public Object _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return new StringBuffer(str);
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String valueAsString = jsonParser.getValueAsString();
            return valueAsString != null ? _deserialize(valueAsString, deserializationContext) : FromStringDeserializer.super.deserialize(jsonParser, deserializationContext);
        }

        public Object getEmptyValue(DeserializationContext deserializationContext) {
            return new StringBuffer();
        }

        public LogicalType logicalType() {
            return LogicalType.Textual;
        }
    }

    public static class StringBuilderDeserializer extends FromStringDeserializer<Object> {
        public StringBuilderDeserializer() {
            super(StringBuilder.class);
        }

        public Object _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return new StringBuilder(str);
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String valueAsString = jsonParser.getValueAsString();
            return valueAsString != null ? _deserialize(valueAsString, deserializationContext) : FromStringDeserializer.super.deserialize(jsonParser, deserializationContext);
        }

        public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            return new StringBuilder();
        }

        public LogicalType logicalType() {
            return LogicalType.Textual;
        }
    }

    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public static FromStringDeserializer<?> findDeserializer(Class<?> cls) {
        int i3;
        if (cls == File.class) {
            i3 = 1;
        } else if (cls == URL.class) {
            i3 = 2;
        } else if (cls == URI.class) {
            i3 = 3;
        } else if (cls == Class.class) {
            i3 = 4;
        } else if (cls == JavaType.class) {
            i3 = 5;
        } else if (cls == Currency.class) {
            i3 = 6;
        } else if (cls == Pattern.class) {
            i3 = 7;
        } else if (cls == Locale.class) {
            i3 = 8;
        } else if (cls == Charset.class) {
            i3 = 9;
        } else if (cls == TimeZone.class) {
            i3 = 10;
        } else if (cls == InetAddress.class) {
            i3 = 11;
        } else if (cls == InetSocketAddress.class) {
            i3 = 12;
        } else if (cls == StringBuilder.class) {
            return new StringBuilderDeserializer();
        } else {
            if (cls == StringBuffer.class) {
                return new StringBufferDeserializer();
            }
            return null;
        }
        return new Std(cls, i3);
    }

    public static Class<?>[] types() {
        return new Class[]{File.class, URL.class, URI.class, Class.class, JavaType.class, Currency.class, Pattern.class, Locale.class, Charset.class, TimeZone.class, InetAddress.class, InetSocketAddress.class, StringBuilder.class, StringBuffer.class};
    }

    public abstract T _deserialize(String str, DeserializationContext deserializationContext) throws IOException;

    public T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException {
        deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Don't know how to convert embedded Object of type %s into %s", obj.getClass().getName(), this._valueClass.getName());
        return null;
    }

    @Deprecated
    public final T _deserializeFromEmptyString() throws IOException {
        return null;
    }

    public Object _deserializeFromEmptyStringDefault(DeserializationContext deserializationContext) throws IOException {
        return getNullValue(deserializationContext);
    }

    public Object _deserializeFromOther(JsonParser jsonParser, DeserializationContext deserializationContext, JsonToken jsonToken) throws IOException {
        if (jsonToken == JsonToken.START_ARRAY) {
            return _deserializeFromArray(jsonParser, deserializationContext);
        }
        if (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
            return deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject == null) {
            return null;
        }
        return this._valueClass.isAssignableFrom(embeddedObject.getClass()) ? embeddedObject : _deserializeEmbedded(embeddedObject, deserializationContext);
    }

    public boolean _shouldTrim() {
        return true;
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String valueAsString = jsonParser.getValueAsString();
        if (valueAsString == null) {
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken != JsonToken.START_OBJECT) {
                return _deserializeFromOther(jsonParser, deserializationContext, currentToken);
            }
            valueAsString = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
        }
        if (valueAsString.isEmpty()) {
            return _deserializeFromEmptyString(deserializationContext);
        }
        if (_shouldTrim()) {
            String trim = valueAsString.trim();
            if (trim != valueAsString && trim.isEmpty()) {
                return _deserializeFromEmptyString(deserializationContext);
            }
            valueAsString = trim;
        }
        try {
            return _deserialize(valueAsString, deserializationContext);
        } catch (IllegalArgumentException | MalformedURLException e3) {
            String message = e3.getMessage();
            throw deserializationContext.weirdStringException(valueAsString, this._valueClass, message != null ? "not a valid textual representation, problem: ".concat(message) : "not a valid textual representation").withCause(e3);
        }
    }

    public LogicalType logicalType() {
        return LogicalType.OtherScalar;
    }

    public Object _deserializeFromEmptyString(DeserializationContext deserializationContext) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType(), this._valueClass, CoercionInputShape.EmptyString);
        if (findCoercionAction == CoercionAction.Fail) {
            deserializationContext.reportInputMismatch((JsonDeserializer<?>) this, "Cannot coerce empty String (\"\") to %s (but could if enabling coercion using `CoercionConfig`)", _coercedTypeDesc());
        }
        if (findCoercionAction == CoercionAction.AsNull) {
            return getNullValue(deserializationContext);
        }
        if (findCoercionAction == CoercionAction.AsEmpty) {
            return getEmptyValue(deserializationContext);
        }
        return _deserializeFromEmptyStringDefault(deserializationContext);
    }
}
