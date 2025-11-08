package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements ContextualSerializer {
    protected final DateFormat _customFormat;
    protected final AtomicReference<DateFormat> _reusedCustomFormat;
    protected final Boolean _useTimestamp;

    public DateTimeSerializerBase(Class<T> cls, Boolean bool, DateFormat dateFormat) {
        super(cls);
        this._useTimestamp = bool;
        this._customFormat = dateFormat;
        this._reusedCustomFormat = dateFormat == null ? null : new AtomicReference<>();
    }

    public void _acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, boolean z2) throws JsonMappingException {
        if (z2) {
            visitIntFormat(jsonFormatVisitorWrapper, javaType, JsonParser.NumberType.LONG, JsonValueFormat.UTC_MILLISEC);
        } else {
            visitStringFormat(jsonFormatVisitorWrapper, javaType, JsonValueFormat.DATE_TIME);
        }
    }

    public boolean _asTimestamp(SerializerProvider serializerProvider) {
        Boolean bool = this._useTimestamp;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (this._customFormat != null) {
            return false;
        }
        if (serializerProvider != null) {
            return serializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        throw new IllegalArgumentException("Null SerializerProvider passed for ".concat(handledType().getName()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024 A[LOOP:0: B:8:0x0024->B:11:0x002f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _serializeAsString(java.util.Date r2, com.fasterxml.jackson.core.JsonGenerator r3, com.fasterxml.jackson.databind.SerializerProvider r4) throws java.io.IOException {
        /*
            r1 = this;
            java.text.DateFormat r0 = r1._customFormat
            if (r0 != 0) goto L_0x0008
            r4.defaultSerializeDateValue((java.util.Date) r2, (com.fasterxml.jackson.core.JsonGenerator) r3)
            return
        L_0x0008:
            java.util.concurrent.atomic.AtomicReference<java.text.DateFormat> r4 = r1._reusedCustomFormat
            r0 = 0
            java.lang.Object r4 = r4.getAndSet(r0)
            java.text.DateFormat r4 = (java.text.DateFormat) r4
            if (r4 != 0) goto L_0x001b
            java.text.DateFormat r4 = r1._customFormat
            java.lang.Object r4 = r4.clone()
            java.text.DateFormat r4 = (java.text.DateFormat) r4
        L_0x001b:
            java.lang.String r2 = r4.format(r2)
            r3.writeString((java.lang.String) r2)
            java.util.concurrent.atomic.AtomicReference<java.text.DateFormat> r1 = r1._reusedCustomFormat
        L_0x0024:
            boolean r2 = r1.compareAndSet(r0, r4)
            if (r2 == 0) goto L_0x002b
            goto L_0x0031
        L_0x002b:
            java.lang.Object r2 = r1.get()
            if (r2 == 0) goto L_0x0024
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase._serializeAsString(java.util.Date, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider):void");
    }

    public abstract long _timestamp(T t2);

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        _acceptJsonFormatVisitor(jsonFormatVisitorWrapper, javaType, _asTimestamp(jsonFormatVisitorWrapper.getProvider()));
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType());
        if (findFormatOverrides == null) {
            return this;
        }
        JsonFormat.Shape shape = findFormatOverrides.getShape();
        if (shape.isNumeric()) {
            return withFormat(Boolean.TRUE, (DateFormat) null);
        }
        if (findFormatOverrides.hasPattern()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(findFormatOverrides.getPattern(), findFormatOverrides.hasLocale() ? findFormatOverrides.getLocale() : serializerProvider.getLocale());
            simpleDateFormat.setTimeZone(findFormatOverrides.hasTimeZone() ? findFormatOverrides.getTimeZone() : serializerProvider.getTimeZone());
            return withFormat(Boolean.FALSE, simpleDateFormat);
        }
        boolean hasLocale = findFormatOverrides.hasLocale();
        boolean hasTimeZone = findFormatOverrides.hasTimeZone();
        boolean z2 = shape == JsonFormat.Shape.STRING;
        if (!hasLocale && !hasTimeZone && !z2) {
            return this;
        }
        DateFormat dateFormat = serializerProvider.getConfig().getDateFormat();
        if (dateFormat instanceof StdDateFormat) {
            StdDateFormat stdDateFormat = (StdDateFormat) dateFormat;
            if (findFormatOverrides.hasLocale()) {
                stdDateFormat = stdDateFormat.withLocale(findFormatOverrides.getLocale());
            }
            if (findFormatOverrides.hasTimeZone()) {
                stdDateFormat = stdDateFormat.withTimeZone(findFormatOverrides.getTimeZone());
            }
            return withFormat(Boolean.FALSE, stdDateFormat);
        }
        if (!(dateFormat instanceof SimpleDateFormat)) {
            Class handledType = handledType();
            String name = dateFormat.getClass().getName();
            serializerProvider.reportBadDefinition((Class<?>) handledType, "Configured `DateFormat` (" + name + ") not a `SimpleDateFormat`; cannot configure `Locale` or `TimeZone`");
        }
        SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) dateFormat;
        SimpleDateFormat simpleDateFormat3 = hasLocale ? new SimpleDateFormat(simpleDateFormat2.toPattern(), findFormatOverrides.getLocale()) : (SimpleDateFormat) simpleDateFormat2.clone();
        TimeZone timeZone = findFormatOverrides.getTimeZone();
        if (timeZone != null && !timeZone.equals(simpleDateFormat3.getTimeZone())) {
            simpleDateFormat3.setTimeZone(timeZone);
        }
        return withFormat(Boolean.FALSE, simpleDateFormat3);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(_asTimestamp(serializerProvider) ? "number" : "string", true);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, T t2) {
        return false;
    }

    public abstract void serialize(T t2, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public abstract DateTimeSerializerBase<T> withFormat(Boolean bool, DateFormat dateFormat);
}
