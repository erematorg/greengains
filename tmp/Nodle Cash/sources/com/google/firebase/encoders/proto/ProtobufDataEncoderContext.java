package com.google.firebase.encoders.proto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.runtime.a;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER = new a(0);
    private static final FieldDescriptor MAP_KEY_DESC = a.a(1, FieldDescriptor.builder(JwtUtilsKt.DID_METHOD_KEY));
    private static final FieldDescriptor MAP_VALUE_DESC = a.a(2, FieldDescriptor.builder("value"));
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    /* renamed from: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.firebase.encoders.proto.Protobuf$IntEncoding[] r0 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding = r0
                com.google.firebase.encoders.proto.Protobuf$IntEncoding r1 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.encoders.proto.Protobuf$IntEncoding r1 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.SIGNED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.encoders.proto.Protobuf$IntEncoding r1 = com.google.firebase.encoders.proto.Protobuf.IntEncoding.FIXED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.encoders.proto.ProtobufDataEncoderContext.AnonymousClass1.<clinit>():void");
        }
    }

    public ProtobufDataEncoderContext(OutputStream outputStream, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.output = outputStream;
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    private static ByteBuffer allocateBuffer(int i3) {
        return ByteBuffer.allocate(i3).order(ByteOrder.LITTLE_ENDIAN);
    }

    private <T> long determineSize(ObjectEncoder<T> objectEncoder, T t2) throws IOException {
        OutputStream outputStream;
        LengthCountingOutputStream lengthCountingOutputStream = new LengthCountingOutputStream();
        try {
            outputStream = this.output;
            this.output = lengthCountingOutputStream;
            objectEncoder.encode(t2, this);
            this.output = outputStream;
            long length = lengthCountingOutputStream.getLength();
            lengthCountingOutputStream.close();
            return length;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> objectEncoder, FieldDescriptor fieldDescriptor, T t2, boolean z2) throws IOException {
        long determineSize = determineSize(objectEncoder, t2);
        if (z2 && determineSize == 0) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
        writeVarInt64(determineSize);
        objectEncoder.encode(t2, this);
        return this;
    }

    private static Protobuf getProtobuf(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static int getTag(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(MAP_KEY_DESC, entry.getKey());
        objectEncoderContext.add(MAP_VALUE_DESC, entry.getValue());
    }

    private void writeVarInt32(int i3) throws IOException {
        while (((long) (i3 & -128)) != 0) {
            this.output.write((i3 & 127) | 128);
            i3 >>>= 7;
        }
        this.output.write(i3 & 127);
    }

    private void writeVarInt64(long j2) throws IOException {
        while ((-128 & j2) != 0) {
            this.output.write((((int) j2) & 127) | 128);
            j2 >>>= 7;
        }
        this.output.write(((int) j2) & 127);
    }

    public ProtobufDataEncoderContext encode(@Nullable Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for " + obj.getClass());
    }

    @NonNull
    public ObjectEncoderContext inline(@Nullable Object obj) throws IOException {
        return encode(obj);
    }

    @NonNull
    public ObjectEncoderContext nested(@NonNull String str) throws IOException {
        return nested(FieldDescriptor.of(str));
    }

    @NonNull
    public ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull String str, @Nullable Object obj) throws IOException {
        return add(FieldDescriptor.of(str), obj);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull String str, double d2) throws IOException {
        return add(FieldDescriptor.of(str), d2);
    }

    private <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> valueEncoder, FieldDescriptor fieldDescriptor, T t2, boolean z2) throws IOException {
        this.valueEncoderContext.resetContext(fieldDescriptor, z2);
        valueEncoder.encode(t2, this.valueEncoderContext);
        return this;
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull String str, int i3) throws IOException {
        return add(FieldDescriptor.of(str), i3);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull String str, long j2) throws IOException {
        return add(FieldDescriptor.of(str), j2);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull String str, boolean z2) throws IOException {
        return add(FieldDescriptor.of(str), z2);
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        return add(fieldDescriptor, obj, true);
    }

    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj, boolean z2) throws IOException {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z2 && charSequence.length() == 0) {
                return this;
            }
            writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(UTF_8);
            writeVarInt32(bytes.length);
            this.output.write(bytes);
            return this;
        } else if (obj instanceof Collection) {
            for (Object add : (Collection) obj) {
                add(fieldDescriptor, add, false);
            }
            return this;
        } else if (obj instanceof Map) {
            for (Map.Entry doEncode : ((Map) obj).entrySet()) {
                doEncode(DEFAULT_MAP_ENCODER, fieldDescriptor, doEncode, false);
            }
            return this;
        } else if (obj instanceof Double) {
            return add(fieldDescriptor, ((Double) obj).doubleValue(), z2);
        } else {
            if (obj instanceof Float) {
                return add(fieldDescriptor, ((Float) obj).floatValue(), z2);
            }
            if (obj instanceof Number) {
                return add(fieldDescriptor, ((Number) obj).longValue(), z2);
            }
            if (obj instanceof Boolean) {
                return add(fieldDescriptor, ((Boolean) obj).booleanValue(), z2);
            }
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (z2 && bArr.length == 0) {
                    return this;
                }
                writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                writeVarInt32(bArr.length);
                this.output.write(bArr);
                return this;
            }
            ObjectEncoder objectEncoder = this.objectEncoders.get(obj.getClass());
            if (objectEncoder != null) {
                return doEncode(objectEncoder, fieldDescriptor, obj, z2);
            }
            ValueEncoder valueEncoder = this.valueEncoders.get(obj.getClass());
            if (valueEncoder != null) {
                return doEncode(valueEncoder, fieldDescriptor, obj, z2);
            }
            if (obj instanceof ProtoEnum) {
                return add(fieldDescriptor, ((ProtoEnum) obj).getNumber());
            }
            if (obj instanceof Enum) {
                return add(fieldDescriptor, ((Enum) obj).ordinal());
            }
            return doEncode(this.fallbackEncoder, fieldDescriptor, obj, z2);
        }
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d2) throws IOException {
        return add(fieldDescriptor, d2, true);
    }

    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d2, boolean z2) throws IOException {
        if (z2 && d2 == 0.0d) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(d2).array());
        return this;
    }

    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2) throws IOException {
        return add(fieldDescriptor, f2, true);
    }

    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2, boolean z2) throws IOException {
        if (z2 && f2 == 0.0f) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(f2).array());
        return this;
    }

    @NonNull
    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i3) throws IOException {
        return add(fieldDescriptor, i3, true);
    }

    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i3, boolean z2) throws IOException {
        if (z2 && i3 == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i4 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
        if (i4 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32(i3);
        } else if (i4 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32((i3 << 1) ^ (i3 >> 31));
        } else if (i4 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 5);
            this.output.write(allocateBuffer(4).putInt(i3).array());
        }
        return this;
    }

    @NonNull
    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j2) throws IOException {
        return add(fieldDescriptor, j2, true);
    }

    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j2, boolean z2) throws IOException {
        if (z2 && j2 == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i3 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
        if (i3 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64(j2);
        } else if (i3 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64((j2 >> 63) ^ (j2 << 1));
        } else if (i3 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 1);
            this.output.write(allocateBuffer(8).putLong(j2).array());
        }
        return this;
    }

    @NonNull
    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z2) throws IOException {
        return add(fieldDescriptor, z2, true);
    }

    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z2, boolean z3) throws IOException {
        return add(fieldDescriptor, z2 ? 1 : 0, z3);
    }
}
