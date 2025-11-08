package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private boolean active = true;
    private JsonValueObjectEncoderContext childContext = null;
    private final ObjectEncoder<Object> fallbackEncoder;
    private final boolean ignoreNullValues;
    private final JsonWriter jsonWriter;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    public JsonValueObjectEncoderContext(@NonNull Writer writer, @NonNull Map<Class<?>, ObjectEncoder<?>> map, @NonNull Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder, boolean z2) {
        this.jsonWriter = new JsonWriter(writer);
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
        this.ignoreNullValues = z2;
    }

    private boolean cannotBeInline(Object obj) {
        return obj == null || obj.getClass().isArray() || (obj instanceof Collection) || (obj instanceof Date) || (obj instanceof Enum) || (obj instanceof Number);
    }

    private JsonValueObjectEncoderContext internalAdd(@NonNull String str, @Nullable Object obj) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.name(str);
        if (obj != null) {
            return add(obj, false);
        }
        this.jsonWriter.nullValue();
        return this;
    }

    private JsonValueObjectEncoderContext internalAddIgnoreNullValues(@NonNull String str, @Nullable Object obj) throws IOException, EncodingException {
        if (obj == null) {
            return this;
        }
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(obj, false);
    }

    private void maybeUnNest() throws IOException {
        if (this.active) {
            JsonValueObjectEncoderContext jsonValueObjectEncoderContext = this.childContext;
            if (jsonValueObjectEncoderContext != null) {
                jsonValueObjectEncoderContext.maybeUnNest();
                this.childContext.active = false;
                this.childContext = null;
                this.jsonWriter.endObject();
                return;
            }
            return;
        }
        throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
    }

    public void close() throws IOException {
        maybeUnNest();
        this.jsonWriter.flush();
    }

    public JsonValueObjectEncoderContext doEncode(ObjectEncoder<Object> objectEncoder, Object obj, boolean z2) throws IOException {
        if (!z2) {
            this.jsonWriter.beginObject();
        }
        objectEncoder.encode(obj, this);
        if (!z2) {
            this.jsonWriter.endObject();
        }
        return this;
    }

    @NonNull
    public ObjectEncoderContext inline(@Nullable Object obj) throws IOException {
        return add(obj, true);
    }

    @NonNull
    public ObjectEncoderContext nested(@NonNull String str) throws IOException {
        maybeUnNest();
        this.childContext = new JsonValueObjectEncoderContext(this);
        this.jsonWriter.name(str);
        this.jsonWriter.beginObject();
        return this.childContext;
    }

    @NonNull
    public ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        return nested(fieldDescriptor.getName());
    }

    private JsonValueObjectEncoderContext(JsonValueObjectEncoderContext jsonValueObjectEncoderContext) {
        this.jsonWriter = jsonValueObjectEncoderContext.jsonWriter;
        this.objectEncoders = jsonValueObjectEncoderContext.objectEncoders;
        this.valueEncoders = jsonValueObjectEncoderContext.valueEncoders;
        this.fallbackEncoder = jsonValueObjectEncoderContext.fallbackEncoder;
        this.ignoreNullValues = jsonValueObjectEncoderContext.ignoreNullValues;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@NonNull String str, @Nullable Object obj) throws IOException {
        if (this.ignoreNullValues) {
            return internalAddIgnoreNullValues(str, obj);
        }
        return internalAdd(str, obj);
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@NonNull String str, double d2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(d2);
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@NonNull String str, int i3) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(i3);
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@NonNull String str, long j2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(j2);
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@NonNull String str, boolean z2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(z2);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        return add(fieldDescriptor.getName(), obj);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2) throws IOException {
        return add(fieldDescriptor.getName(), (double) f2);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d2) throws IOException {
        return add(fieldDescriptor.getName(), d2);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i3) throws IOException {
        return add(fieldDescriptor.getName(), i3);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j2) throws IOException {
        return add(fieldDescriptor.getName(), j2);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z2) throws IOException {
        return add(fieldDescriptor.getName(), z2);
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@Nullable String str) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(str);
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(float f2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value((double) f2);
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(double d2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(d2);
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(int i3) throws IOException {
        maybeUnNest();
        this.jsonWriter.value((long) i3);
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(long j2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(j2);
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(boolean z2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(z2);
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@Nullable byte[] bArr) throws IOException {
        maybeUnNest();
        if (bArr == null) {
            this.jsonWriter.nullValue();
        } else {
            this.jsonWriter.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    @NonNull
    public JsonValueObjectEncoderContext add(@Nullable Object obj, boolean z2) throws IOException {
        Class<?> cls;
        if (z2 && cannotBeInline(obj)) {
            if (obj == null) {
                cls = null;
            } else {
                cls = obj.getClass();
            }
            throw new EncodingException(cls + " cannot be encoded inline");
        } else if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        } else if (obj instanceof Number) {
            this.jsonWriter.value((Number) obj);
            return this;
        } else {
            int i3 = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof byte[]) {
                    return add((byte[]) obj);
                }
                this.jsonWriter.beginArray();
                if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    int length = iArr.length;
                    while (i3 < length) {
                        this.jsonWriter.value((long) iArr[i3]);
                        i3++;
                    }
                } else if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    int length2 = jArr.length;
                    while (i3 < length2) {
                        add(jArr[i3]);
                        i3++;
                    }
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    int length3 = dArr.length;
                    while (i3 < length3) {
                        this.jsonWriter.value(dArr[i3]);
                        i3++;
                    }
                } else if (obj instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj;
                    int length4 = zArr.length;
                    while (i3 < length4) {
                        this.jsonWriter.value(zArr[i3]);
                        i3++;
                    }
                } else if (obj instanceof Number[]) {
                    for (Number add : (Number[]) obj) {
                        add((Object) add, false);
                    }
                } else {
                    for (Object add2 : (Object[]) obj) {
                        add(add2, false);
                    }
                }
                this.jsonWriter.endArray();
                return this;
            } else if (obj instanceof Collection) {
                this.jsonWriter.beginArray();
                for (Object add3 : (Collection) obj) {
                    add(add3, false);
                }
                this.jsonWriter.endArray();
                return this;
            } else if (obj instanceof Map) {
                this.jsonWriter.beginObject();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    try {
                        add((String) key, entry.getValue());
                    } catch (ClassCastException e3) {
                        throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", new Object[]{key, key.getClass()}), e3);
                    }
                }
                this.jsonWriter.endObject();
                return this;
            } else {
                ObjectEncoder objectEncoder = this.objectEncoders.get(obj.getClass());
                if (objectEncoder != null) {
                    return doEncode(objectEncoder, obj, z2);
                }
                ValueEncoder valueEncoder = this.valueEncoders.get(obj.getClass());
                if (valueEncoder != null) {
                    valueEncoder.encode(obj, this);
                    return this;
                } else if (!(obj instanceof Enum)) {
                    return doEncode(this.fallbackEncoder, obj, z2);
                } else {
                    if (obj instanceof NumberedEnum) {
                        add(((NumberedEnum) obj).getNumber());
                    } else {
                        add(((Enum) obj).name());
                    }
                    return this;
                }
            }
        }
    }
}
